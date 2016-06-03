package lhj.java.test;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.StringTokenizer;

import lhj.java.util.PassWordCrypter;

/**
 * @author Sealion Hunter
 * @version $Revision: 1.1 $ $Date: 2007/08/25 18:19:10 $
 */
public class DBToJava {

	Connection con = null;

	public DBToJava() throws Exception {
		try {
			getTypeString(0);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:edi", "DEV", "DEV");
			createAdminUser(con);
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs;
			rs = dbmd.getTypeInfo();
			rs = dbmd.getIndexInfo(null, null, "B02_TRN_SND_RCV", true, true);
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select PRICE from B17_BILL_DATA ");
			while (rs.next()) {
				// Object o = rs.getObject("PRICE");
				double price = rs.getDouble("PRICE");
				System.out.println("Price = " + price);
			}
			rs.close();
			stmt.close();
			PreparedStatement pstmt = con
					.prepareStatement("UPDATE UM3_USR_MANAGE SET LOGIN_PASS = ? where LOGIN_ID = ?");
			pstmt.setString(1, PassWordCrypter.crypt("hjliang1"));
			pstmt.setString(2, "hjliangC1");
			pstmt.setNull(0, 4);
			int update = pstmt.executeUpdate();
			pstmt.close();
			System.out.println(update);
			// rs = stmt.executeQuery("select INDEX_NAME from user_indexes ");
			rebuildIndex(con, rs);
			printRs(rs);
			rs = dbmd.getTables(null, "DEV", null, new String[] { "TABLE" });
			int c = 0;
			while (rs.next()) {
				// tableToJava(rs.getString("TABLE_NAME"));
				ResultSet rss = dbmd.getPrimaryKeys(null, null, rs
						.getString("TABLE_NAME"));
				printRs(rss);
				c++;
				if (c >= 5)
					break;
			}
			String tableName = printRs(rs);
			rs = dbmd.getColumns(null, null, tableName, null);
			printRs(rs);
			tableToJava(tableName);
			rs = dbmd.getColumns(null, null, "UM9_IND_ACC", null);
			printRs(rs);
			tableToJava("UM9_IND_ACC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void rebuildIndex(Connection con, ResultSet rs) throws Exception {
		Statement stmt = con.createStatement();
		System.out.println("INDEX_NAME");
		while (rs.next()) {
			String indexName = rs.getString("INDEX_NAME");
			System.out.println(indexName);
			stmt.addBatch("ALTER INDEX " + indexName + " REBUILD");
		}
		int[] rscnt = stmt.executeBatch();
		System.out.println(rscnt.length);
	}

	public String printRs(ResultSet rs) throws Exception {
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCnt = rsmd.getColumnCount();
		for (int i = 1; i <= colCnt; i++) {
			System.out.print(rsmd.getColumnLabel(i) + "\t");
		}
		String name = "";
		System.out.println();
		while (rs.next()) {
			for (int i = 1; i <= colCnt; i++) {
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println();
			name = rs.getString(3);
		}
		System.out.println();
		return name;

	}

	public void tableToJava(String tableName) throws Exception {
		if (con == null)
			return;
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rs = dbmd.getTables(null, null, tableName,
				new String[] { "TABLE" });

		rs = dbmd.getColumns(null, null, tableName, null);

		// File dtsFile = new File(tableName + ".java");
		FileWriter out = new FileWriter(convertColumn(tableName) + ".java");
		out.write("public class " + convertColumn(tableName) + " { \n");
		while (rs.next()) {
			String type = rs.getString("TYPE_NAME");
			String column = convertColumn(rs.getString("COLUMN_NAME"));
			StringBuffer sdd = new StringBuffer(column);
			sdd.setCharAt(0, Character.toLowerCase(sdd.charAt(0)));
			String field = sdd.toString();
			out.write("    private " + getTypeString(type) + " " + field
					+ ";\n");
			out.write("\n");
			out.write("    public " + getTypeString(type) + " get" + column
					+ " () { \n");
			out.write("        return " + field + "; \n    }\n");
			out.write("\n");
			out.write("    public void set" + column + " ("
					+ getTypeString(type) + " " + field + ") { \n");
			out.write("        this." + field + " = " + field + "; \n    }\n");
			out.write("\n");
		}
		out.write("}");
		out.flush();
		out.close();
	}

	public String getTypeString(String type) {
		String typeString = "";
		if (type == null) {
			return null;
		}
		if (type.indexOf("CHAR") != -1) {
			typeString = "String";
		} else if (type.indexOf("TIME") != -1) {
			typeString = "java.sql.Timestamp";
		} else if (type.indexOf("DATE") != -1) {
			typeString = "java.sql.Date";
		} else if (type.indexOf("NUMBER") != -1
				|| type.indexOf("Decimal") != -1) {
			typeString = "java.util.BigDecimal";
		} else {
			typeString = "Object";
		}
		return typeString;
	}

	private String getTypeString(int type) {
		switch (type) {
		case Types.ARRAY:
		case Types.BINARY:
		case Types.BLOB:
		case Types.CLOB:
		case Types.DATALINK:
			return "Object";
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGVARCHAR:
			return "String";
		case Types.NUMERIC:
		case Types.DOUBLE:
		case Types.FLOAT:
		case Types.INTEGER:
		case Types.REAL:
		case Types.DECIMAL:
		case Types.BIGINT:
			return "java.util.BegDecimal";
		case Types.DATE:
			return "java.sql.Date";
		case Types.TIME:
//		case OracleTypes.TIMESTAMPLTZ:
//		case OracleTypes.TIMESTAMPTZ:
		case Types.TIMESTAMP:
			return "java.sql.Timestamp";
		default:
			return "Object";
		}
	}

	private String convertColumn(String column) {
		String newCol = "";
		StringTokenizer st = new StringTokenizer(column, "_");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			newCol += token.charAt(0) + token.substring(1).toLowerCase();
		}
		return newCol;
	}

	private void createAdminUser(Connection con) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO UM3_USR_MANAGE (");
		sql.append(" UM3_USR_SEQ ");
		sql.append(" ,LOGIN_ID ");
		sql.append(" ,SVC_CD ");
		sql.append(" ,E_MAIL ");
		sql.append(" ,LOGIN_PASS ");
		sql.append(" ,NAME ");
		sql.append(" ,ACC_LOCK_STS ");
		sql.append(" ,NAME_YOMI ");
		sql.append(" ,UPD_PASS_DATETIME ");
		sql.append(" ,PASS_FAIL_NUM ");
		sql.append(" ,LAST_LOGIN_DATETIME ");
		sql.append(" ,MANAGER_FLG ");
		sql.append(" ,REG_DATETIME ");
		sql.append(" ,REG_USR ");
		sql.append(" ,UPD_DATETIME ");
		sql.append(" ,UPD_USR ");
		sql.append(" ,DEL_FLG ");
		sql.append(" ) VALUES ( ");
		sql.append(" SEQ_UM3.nextval ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ");
		sql.append(" ,? ) ");
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		String[][] users = { { "C", "adminc002" }, { "S", "admins002" },
				{ "J", "adminj002" }, { "Z", "adminz002" } };
		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < users.length; i++) {
				String pass = PassWordCrypter.crypt(users[i][1]);
				pstmt.setString(1, users[i][1]);
				pstmt.setString(2, users[i][0]);
				pstmt.setString(3, users[i][1] + "@mail.com");
				pstmt.setString(4, pass);
				pstmt.setString(5, "managers" + users[i][1]);
				pstmt.setString(6, "0");
				pstmt.setString(7, "managers" + users[i][1]);
				pstmt.setDate(8, new Date(cal.getTimeInMillis()));
				pstmt.setInt(9, 0);
				pstmt.setDate(10, new Date(cal.getTimeInMillis()));
				pstmt.setString(11, "1");
				pstmt.setTimestamp(12, timestamp);
				pstmt.setString(13, users[i][1]);
				pstmt.setTimestamp(14, timestamp);
				pstmt.setString(15, users[i][1]);
				pstmt.setString(16, "0");
				pstmt.addBatch();
			}
			int[] result = pstmt.executeBatch();
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		new DBToJava();
	}
}
