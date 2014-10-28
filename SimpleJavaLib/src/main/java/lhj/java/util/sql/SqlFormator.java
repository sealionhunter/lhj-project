package lhj.java.util.sql;

import java.util.StringTokenizer;
import lhj.java.util.IntStack;
import lhj.java.util.SP;

public class SqlFormator {
	public static String SPACE = "                ";
	public static final String[] KEY_WORDS = { "ACCESS", "ADD", "ALL", "ALTER",
			"AND", "ANY", "APPEND", "AS", "ASC", "AUDIT", "BEGIN", "BETWEEN",
			"BFILE", "BLOB", "BODY", "BY", "CHAR", "CHECK", "CLOB", "CLUSTER",
			"COLUMN", "COMMENT", "COMPRESS", "CONNECT", "CONSTRAINT", "CREATE",
			"CURRENT", "DATA", "DATABASE", "DATE", "DECIMAL", "DECODE",
			"DEFAULT", "DELETE", "DEREF", "DESC", "DIRECTORY", "DISTINCT",
			"DOUBLE", "DROP", "ELSE", "ELSEIF", "END", "EXCLUSIVE", "EXISTS",
			"FALSE", "FILE", "FLOAT", "FOR", "FOREIGN", "FROM", "FUNCTION",
			"GRANT", "GROUP", "HAVING", "IDENTIFIED", "IF", "IMMEDIATE", "IN",
			"INCREMENT", "INDEX", "INITIAL", "INITRANS   ", "INSERT",
			"INSTEAD", "INTEGER", "INTERSECT", "INTO", "IS", "KEY", "LEVEL",
			"LIKE", "LOCK", "LONG", "MAKE_REF", "MAP", "MAXEXTENTS",
			"MAXTRANS", "MEMBER", "MINUS", "MLSLABEL", "MODE", "MODIFY",
			"NCLOB", "NESTED", "NESTED_TABLE_ID", "NOAUDIT", "NOCOMPRESS",
			"NOT", "NOWAIT", "NULL", "NUMBER", "OBJECT", "OF", "OFFLINE", "ON",
			"ONLINE", "OPTION", "OR", "ORDER", "PCTFREE", "PCTUSED", "PRIMARY",
			"PRIOR", "PRIVILEGES", "PUBLIC", "RAW", "REAL", "REF",
			"REFERENCES", "RENAME", "REPLACE", "RESOURCE", "RESTRINC",
			"RETURN", "REVOKE", "ROW", "ROWID", "ROWNUM", "ROWS", "SCHEMA",
			"SCOPE", "SELECT", "SELF", "SEQUENCE", "SESSION", "SET", "SHARE",
			"SIZE", "SMALLINT", "START", "STORE", "SUCCESSFUL", "SYNONYM",
			"SYSDATE", "TABLE", "TABLESPACE", "THEN", "TO", "TRIGGER", "TRUE",
			"TRUNCATE", "TYPE", "UID", "UNION", "UNIQUE", "UPDATE", "USER",
			"USING", "VALIDATE", "VALUE", "VALUES", "VARCHAR", "VARCHAR2",
			"VARRAY", "VIEW", "WHENEVER", "WHERE", "WITH" };
	private static String[] sqlLeader = { "ALERT", "CREATE", "DELETE", "DROP",
			"INSERT", "SELECT", "UPDATE" };
	private static String[] keyNeedBack = { "FROM", "GROUP", "HAVING", "ORDER",
			"SELECT", "WHERE" };
	private static String[] preNewLine = { ",", "AND", "BY", "CASE",
			"DISTINCT", "ELSE", "END", "FROM", "GROUP", "HAVING", "OR",
			"ORDER", "SELECT", "THEN", "WHEN", "WHERE" };

	boolean isKeyToUpper = true;
	int indentLength = 4;
	IntStack indentSaver = new IntStack();
	IntStack bracketNewLine = new IntStack();
	IntStack indentCase = new IntStack();

	public String format(String sql) {
		StringBuffer sb = new StringBuffer(sql.length());
		StringTokenizer st = new StringTokenizer(sql,
				"\t\r\n !=<>()+-*/&?'&|,;", true);
		int indentLen = 0;
		String preNode = "";
		while (st.hasMoreTokens()) {
			String node = st.nextToken();
			if (node.trim().length() == 0) {
				sb.append(" ");
				while (st.hasMoreTokens()
						&& (node = st.nextToken()).trim().length() == 0)
					;
			}
			if (searchWord(sqlLeader, node)) {
				indentLen += indentLength;
				rtrim(sb);
				appendNode(sb, node);
			} else if (searchWord(keyNeedBack, node)) {
				indentNewLine(sb, indentLen - indentLength);
				appendNode(sb, node);
			} else if (node.equalsIgnoreCase("CASE")) {
				indentCase.push(indentLen);
				if (!preNode.equalsIgnoreCase("ELSE")) {
					indentNewLine(sb, indentLen);
					indentLen += indentLength;
				}
				appendNode(sb, node);
			} else if (node.equalsIgnoreCase("WHEN")) {
				if (!indentCase.isEmpty()) {
					indentLen = indentCase.top() + indentLength;
				}
				if (!preNode.equalsIgnoreCase("CASE")) {
					indentNewLine(sb, indentLen);
				}
				indentLen += indentLength;
				appendNode(sb, node);
			} else if (node.equalsIgnoreCase("THEN")) {
				if (!indentCase.isEmpty()) {
					indentLen = indentCase.top() + indentLength;
				}
				indentNewLine(sb, indentLen);
				indentLen += indentLength;
				appendNode(sb, node);
			} else if (node.equalsIgnoreCase("ELSE")) {
				if (!indentCase.isEmpty()) {
					indentLen = indentCase.top() + indentLength;
				}
				indentNewLine(sb, indentLen);
				indentLen += indentLength;
				appendNode(sb, node);
			} else if (node.equalsIgnoreCase("END")) {
				if (!indentCase.isEmpty()) {
					indentLen = indentCase.pop();
				}
				indentNewLine(sb, indentLen);
				appendNode(sb, node);
			} else if (node.equals(")")) {
				boolean newLine = false;
				int pos = indentLen;
				if (!indentSaver.isEmpty()) {
					pos = indentSaver.pop();
					indentLen = indentSaver.pop();
				}
				if (!bracketNewLine.isEmpty()) {
					newLine = bracketNewLine.top() == 1 ? true : false;
				}
				if (newLine) {
					indentNewLine(sb, pos);
				}
				appendNode(sb, node);
			} else if (node.equals(";")) {
				indentLen = 0;
				sb.append('\n').append(node).append('\n');

			} else if (searchWord(preNewLine, preNode)) {
				boolean newLine = true;
				if (preNode.equals(",")) {
					if (!bracketNewLine.isEmpty()) {
						newLine = bracketNewLine.top() == 1 ? true : false;
					}
				}
				if (newLine) {
					indentNewLine(sb, indentLen);
				}
				if (node.equals("(")) {
					int pos = sb.length()
							- (((pos = sb.lastIndexOf("\n")) == -1) ? 0
									: (pos + 1));
					indentSaver.push(indentLen);
					indentSaver.push(pos);
					bracketNewLine.push(0);
					indentLen = pos + 1;
				} else if (node.equalsIgnoreCase("CASE")) {
					indentLen += indentLength;
				}
				appendNode(sb, node);
			} else if (node.equals("(")) {
				int pos = sb.length()
						- (((pos = sb.lastIndexOf("\n")) == -1) ? 0 : (pos + 1));
				indentSaver.push(indentLen);
				indentSaver.push(pos);
				bracketNewLine.push(0);
				indentLen = pos + 1;
				appendNode(sb, node);
			} else {
				appendNode(sb, node);
			}
			preNode = node;
		}
		return sb.toString();
	}

	public String formatSql(String sql) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(sql, "", true);
		while (st.hasMoreTokens()) {
			String node = st.nextToken();
			node = filteSpace(st, sb, node);
			if (node.equalsIgnoreCase("SELECT")) {
				sb.append(node.toUpperCase());
				node = formatSelect(st, sb, 0);
			} else if (node.equalsIgnoreCase("INSERT")) {
				sb.append(node.toUpperCase());
				node = formatInsert(st, sb, 0);
			} else if (node.equalsIgnoreCase("DELETE")) {
				sb.append(node.toUpperCase());
				node = formatDelete(st, sb, 0);
			} else if (node.equalsIgnoreCase("UPDATE")) {
				sb.append(node.toUpperCase());
				node = formatUpdate(st, sb, 0);
			}
			if (!node.equals(";")) {
				break;
			} else {
				indentNewLine(sb, 0);
				sb.append(node);
				indentNewLine(sb, 0);
			}
		}
		return sb.toString();
	}

	public String formatUpdate(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		return "";
	}

	public String formatDelete(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		return "";
	}

	public String formatInsert(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		return "";
	}

	String[] select = { "ALL", "DISTINCT", "UNIQUE" };

	public String formatSelect(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		String preNode = "SELECT";
		String node = preNode;
		if (st.hasMoreTokens()) {
			node = filteSpace(st, sb, st.nextToken());
			if (searchWord(select, node)) {
				appendNode(sb, node);
				indentNewLine(sb, indentLen);
				node = st.nextToken();
			}
			node = formatSelectList(st, sb, node, indentLen);
			if (node.equalsIgnoreCase("FROM")) {
				indentNewLine(sb, indentLen);
				appendNode(sb, node);
				node = formatForm(st, sb, indentLen);
			}
			if (node.equalsIgnoreCase("WHERE")) {
				indentNewLine(sb, indentLen);
				appendNode(sb, node);
				node = formatWhere(st, sb, indentLen);
				if (node.equalsIgnoreCase("Group")) {
					indentNewLine(sb, indentLen);
					appendNode(sb, node);
					node = formatGroup(st, sb, indentLen);
					if (node.equalsIgnoreCase("Having")) {
						indentNewLine(sb, indentLen);
						appendNode(sb, node);
						node = formatHaving(st, sb, indentLen);
					}
				}
			}
			if (node.equalsIgnoreCase("Order")) {
				indentNewLine(sb, indentLen);
				appendNode(sb, node);
				node = formatOrderBy(st, sb, indentLen);
			}
		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatSelectList(StringTokenizer st, StringBuffer sb,
			String node, int indentLen) {
		// String preNode = "SELECT";
		indentLen += indentLength;
		indentNewLine(sb, indentLen);
		node = filteSpace(st, sb, node);
		do {
			if (node.equalsIgnoreCase("CASE")) {
				formatCase(st, sb, indentLen);
				node = filteSpace(st, sb, st.nextToken());
			}
			if (st.hasMoreTokens()) {
				node = st.nextToken();
			} else {
				break;
			}
		} while (true);
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatForm(StringTokenizer st, StringBuffer sb, int indentLen) {
		String preNode = "FROM";
		String node = preNode;
		while (st.hasMoreTokens()) {

		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatWhere(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		String preNode = "Where";
		String node = preNode;
		while (st.hasMoreTokens()) {

		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatGroup(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		String preNode = "Where";
		String node = preNode;
		while (st.hasMoreTokens()) {

		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatHaving(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		String preNode = "HAVING";
		String node = preNode;
		while (st.hasMoreTokens()) {

		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String formatOrderBy(StringTokenizer st, StringBuffer sb,
			int indentLen) {
		String preNode = "Where";
		String node = preNode;
		while (st.hasMoreTokens()) {

		}
		if (!st.hasMoreTokens())
			return "";
		return node;
	}

	private String filteSpace(StringTokenizer st, StringBuffer sb, String node) {
		if (node.trim().length() == 0) {
			sb.append(" ");
			while (st.hasMoreTokens()
					&& (node = st.nextToken()).trim().length() == 0)
				;
		}
		return node;
	}

	public void formatCase(StringTokenizer st, StringBuffer sb, int indentLen) {
		String preNode = "";
		do {
			String node = st.nextToken();
			if (node.equalsIgnoreCase("CASE")) {
				indentCase.push(indentLen);
				if (!preNode.equalsIgnoreCase("ELSE")) {
					indentNewLine(sb, indentLen);
					indentLen += indentLength;
				}
				appendNode(sb, node);
			}
		} while (st.hasMoreTokens());
	}

	public boolean searchWord(String[] from, String key) {
		key = key.toUpperCase();
		int i = 0, j = from.length - 1;
		while (i <= j) {
			int k = (i + j) / 2;
			if (key.compareTo(from[k]) > 0) {
				i = k + 1;
			} else if (key.compareTo(from[k]) < 0) {
				j = k - 1;
			} else {
				return true;
			}
		}
		return false;
	}

	private void rtrim(StringBuffer sb) {
		if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ')
			sb.deleteCharAt(sb.length() - 1);
	}

	private void indentNewLine(StringBuffer sb, int indentLen) {
		if (indentLen < 0) {
			indentLen = 0;
		} else {
			while (indentLen > SPACE.length()) {
				SPACE += SPACE;
			}
		}
		rtrim(sb);
		sb.append('\n').append(SPACE.substring(0, indentLen));
		bracketNewLine.resetTop(1);
	}

	private void appendNode(StringBuffer sb, String node) {
		sb.append(isKeyToUpper && searchWord(KEY_WORDS, node) ? node
				.toUpperCase() : node);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SqlFormator formator = new SqlFormator();
		SP.println(formator.format(formator.sql));

	}

	String sql = "SELECT cust_last_name, CASE credit_limit WHEN 100 THEN 'Low' WHEN 5000 THEN 'High' ELSE 'Medium' END FROM customers;";
}
