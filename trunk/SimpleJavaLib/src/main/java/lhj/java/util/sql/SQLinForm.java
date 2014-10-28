package lhj.java.util.sql;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SQLinForm extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 269573874402819848L;

	protected class EditorPanel extends JTextPane {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1737217920381104214L;

		public final boolean getScrollableTracksViewportWidth() {
			return false;
		}

		public final void setSize(Dimension dimension) {
			int i1 = getParent().getSize().width;
			int j1;
			if ((j1 = dimension.width) < 4000)
				j1 = 4000;
			if (j1 < i1)
				j1 = i1;
			super.setSize(new Dimension(j1, dimension.height));
		}

		protected EditorPanel() {
		}
	}

	private JCheckBox optionShowLevel = new JCheckBox("Show Level");
	private JCheckBox optionColor = new JCheckBox("Color");
	private JCheckBox optionEmptyLine = new JCheckBox("No Empty Lines");
	private JCheckBox optionShowLineNumber = new JCheckBox("Line Number");
	private JCheckBox breakAfterAnd = new JCheckBox("After AND/OR");
	private JCheckBox breakAfterComma = new JCheckBox("After Comma");
	private JCheckBox breakBeforeAnd = new JCheckBox("Before  AND/OR");
	private JCheckBox breakBeforeComma = new JCheckBox("Before Comma");
	private JCheckBox breakCase = new JCheckBox("For CASE Keywords");
	private JCheckBox breakBracket = new JCheckBox(
			"For brackets in AND, OR, WHEN, ...");
	private JCheckBox breakSubSelect = new JCheckBox("For subselect brackets");
	private JCheckBox breakAfterSelect = new JCheckBox(
			"After SELECT, FROM, WHERE, ...");
	private JCheckBox optionUseTab = new JCheckBox("Use Tabs");
	private JComboBox<String> optionLineBreaks = new JComboBox<String>();
	private JComboBox<String> optionIndent = new JComboBox<String>();
	private JComboBox<String> optionInput = new JComboBox<String>();
	private JComboBox<String> optionOutput = new JComboBox<String>();
	private JComboBox<String> caseFormat = new JComboBox<String>();
	public JTextPane sqlEditor = new EditorPanel();
	private static Color backColor = Color.WHITE;
	private static Color color240 = new Color(240, 240, 240);

	public SQLinForm() {
	}

	public final void init() {
		initComponent();
		optionInput.setBackground(backColor);
		optionOutput.setBackground(backColor);
		optionIndent.setBackground(backColor);
		optionOutput.setBackground(backColor);
		optionLineBreaks.setBackground(backColor);
		optionUseTab.setBackground(backColor);
		caseFormat.setBackground(backColor);
		sqlEditor.requestFocus();
	}

	private void initComponent() {
		Color color1 = new Color(255, 255, 204);
		JPanel pnlFormator = new JPanel();
		JPanel aN = new JPanel();
		JScrollPane sqlEditorScrollor = new JScrollPane();
		JPanel aG = new JPanel();
		JPanel w = new JPanel();
		JButton btnFormat = new JButton("Format SQL");
		JButton btnClearScreen = new JButton("Clear Screen");
		JPanel pnlOptions = new JPanel();
		JLabel lblCase = new JLabel("Case");
		JLabel lblOutputSql = new JLabel("Output SQL");
		JLabel lblInputSql = new JLabel("Input SQL");
		JLabel lblIndention = new JLabel("Indention");
		JLabel lblSpFormat = new JLabel("Special formatting");
		JLabel lblLineBreak = new JLabel("Linebreaks");
		getContentPane().setLayout(new CardLayout());
		setFont(new Font("Courier New", 0, 12));
		pnlFormator.setLayout(new BorderLayout());
		pnlFormator.setBackground(backColor);
		aN.setLayout(new BorderLayout());
		sqlEditorScrollor.setBorder(BorderFactory.createCompoundBorder());
		sqlEditorScrollor.setViewportBorder(BorderFactory.createBevelBorder(0));
		sqlEditor.setBackground(color240);
		sqlEditor.setBorder(BorderFactory.createTitledBorder(""));
		sqlEditor.setFont(new Font("Courier New", 0, 11));
		sqlEditor.setDragEnabled(true);
		sqlEditor.setMargin(new Insets(0, 0, 0, 0));
		sqlEditorScrollor.setViewportView(sqlEditor);
		aN.add(sqlEditorScrollor, "Center");
		pnlFormator.add(aN, "Center");
		aG.setLayout(new BorderLayout());
		w.setLayout(new GridLayout(1, 0));
		w.setBackground(backColor);
		btnFormat.setBackground(color240);
		btnFormat.setMargin(new Insets(2, 4, 2, 4));
		btnFormat.addActionListener(new Action_SqlFormat(this));
		w.add(btnFormat);
		btnClearScreen.setBackground(color240);
		btnClearScreen.setMargin(new Insets(2, 2, 2, 2));
		btnClearScreen.addActionListener(new Action_ClearScreen(this));
		w.add(btnClearScreen);
		aG.add(w, "North");
		pnlOptions.setLayout(null);
		pnlOptions.setBackground(color1);
		pnlOptions.setAlignmentY(0.0F);
		breakAfterComma.setBackground(color1);
		breakAfterComma.setFont(new Font("Dialog", 0, 10));
		breakAfterComma.setAlignmentY(0.0F);
		breakAfterComma.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakAfterComma);
		breakAfterComma.setBounds(20, 140, 83, 15);
		breakBeforeComma.setBackground(color1);
		breakBeforeComma.setFont(new Font("Dialog", 0, 10));
		breakBeforeComma.setSelected(true);
		breakBeforeComma.setAlignmentY(0.0F);
		breakBeforeComma.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakBeforeComma);
		breakBeforeComma.setBounds(110, 140, 100, 15);
		lblCase.setBackground(backColor);
		lblCase.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblCase);
		lblCase.setBounds(10, 245, 65, 15);
		lblOutputSql.setBackground(backColor);
		lblOutputSql.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblOutputSql);
		lblOutputSql.setBounds(10, 55, 70, 16);
		lblInputSql.setBackground(backColor);
		lblInputSql.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblInputSql);
		lblInputSql.setBounds(10, 10, 60, 15);
		lblIndention.setBackground(backColor);
		lblIndention.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblIndention);
		lblIndention.setBounds(10, 320, 70, 16);
		lblSpFormat.setBackground(backColor);
		lblSpFormat.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblSpFormat);
		lblSpFormat.setBounds(10, 360, 130, 16);
		breakAfterAnd.setBackground(color1);
		breakAfterAnd.setFont(new Font("Dialog", 0, 10));
		breakAfterAnd.setAlignmentY(0.0F);
		breakAfterAnd.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakAfterAnd);
		breakAfterAnd.setBounds(20, 155, 90, 15);
		breakBeforeAnd.setBackground(color1);
		breakBeforeAnd.setFont(new Font("Dialog", 0, 10));
		breakBeforeAnd.setSelected(true);
		breakBeforeAnd.setAlignmentY(0.0F);
		breakBeforeAnd.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakBeforeAnd);
		breakBeforeAnd.setBounds(110, 155, 100, 15);
		breakBracket.setBackground(color1);
		breakBracket.setFont(new Font("Dialog", 0, 10));
		breakBracket.setAlignmentY(0.0F);
		breakBracket.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakBracket);
		breakBracket.setBounds(20, 200, 190, 15);
		breakAfterSelect.setBackground(color1);
		breakAfterSelect.setFont(new Font("Dialog", 0, 10));
		breakAfterSelect.setAlignmentY(0.0F);
		breakAfterSelect.setSelected(true);
		breakAfterSelect.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakAfterSelect);
		breakAfterSelect.setBounds(20, 170, 190, 15);
		optionShowLevel.setBackground(color1);
		optionShowLevel.setFont(new Font("Dialog", 0, 10));
		optionShowLevel.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(optionShowLevel);
		optionShowLevel.setBounds(15, 380, 77, 15);
		optionColor.setBackground(color1);
		optionColor.setFont(new Font("Dialog", 0, 10));
		optionColor.setSelected(true);
		optionColor.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(optionColor);
		optionColor.setBounds(110, 380, 47, 15);
		optionEmptyLine.setBackground(backColor);
		optionEmptyLine.setFont(new Font("Dialog", 0, 10));
		optionEmptyLine.setSelected(true);
		optionEmptyLine.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(optionEmptyLine);
		optionEmptyLine.setBounds(15, 470, 95, 19);
		optionShowLineNumber.setBackground(color1);
		optionShowLineNumber.setFont(new Font("Dialog", 0, 10));
		optionShowLineNumber.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(optionShowLineNumber);
		optionShowLineNumber.setBounds(15, 395, 90, 15);
		breakSubSelect.setBackground(color1);
		breakSubSelect.setFont(new Font("Dialog", 0, 10));
		breakSubSelect.setMargin(new Insets(0, 0, 0, 0));
		breakSubSelect.setSelected(true);
		pnlOptions.add(breakSubSelect);
		breakSubSelect.setBounds(20, 215, 130, 15);
		breakCase.setBackground(color1);
		breakCase.setFont(new Font("Dialog", 0, 10));
		breakCase.setSelected(true);
		breakCase.setAlignmentY(0.0F);
		breakCase.setMargin(new Insets(0, 0, 0, 0));
		pnlOptions.add(breakCase);
		breakCase.setBounds(20, 185, 190, 15);
		lblLineBreak.setBackground(backColor);
		lblLineBreak.setFont(new Font("Dialog", 1, 12));
		pnlOptions.add(lblLineBreak);
		lblLineBreak.setBounds(10, 95, 65, 15);
		optionOutput.addItem("SQL");
		optionOutput.addItem("Java append");
		optionOutput.addItem("Java");
		optionOutput.addItem("C#");
		optionOutput.addItem("PHP");
		optionOutput.addItem("VB");
		optionOutput.addItem("VB append");
		optionOutput.addItem("VB3");
		optionOutput.addItem("Concatenated SQL");
		optionOutput.setSelectedIndex(0);
		optionOutput.setFont(new Font("Dialog", 0, 10));
		pnlOptions.add(optionOutput);
		optionUseTab.setBounds(15, 295, 95, 20);
		pnlOptions.add(optionUseTab);
		optionOutput.setBounds(15, 70, 95, 20);
		optionIndent.addItem("2");
		optionIndent.addItem("4");
		optionIndent.addItem("8");
		optionIndent.setSelectedIndex(1);
		optionIndent.setFont(new Font("Dialog", 0, 10));
		optionIndent.setMaximumRowCount(10);
		pnlOptions.add(optionIndent);
		optionIndent.setBounds(15, 335, 55, 20);
		caseFormat.addItem("No Change");
		caseFormat.addItem("Keywords Uppercase");
		caseFormat.addItem("Whole SQL Uppercase");
		caseFormat.addItem("Whole SQL Lowercase");
		caseFormat.setSelectedIndex(1);
		caseFormat.setFont(new Font("Dialog", 0, 10));
		pnlOptions.add(caseFormat);
		caseFormat.setBounds(15, 260, 115, 20);
		optionInput.addItem("Any SQL");
		optionInput.addItem("DB2/UDB");
		optionInput.addItem("PL/SQL");
		optionInput.addItem("T-SQL");
		optionInput.addItem("Transact-SQL");
		optionInput.addItem("Sybase");
		optionInput.addItem("MYSQL");
		optionInput.addItem("PostgreSQL");
		optionInput.addItem("Informix");
		optionInput.setSelectedIndex(0);
		optionInput.setFont(new Font("Dialog", 0, 10));
		pnlOptions.add(optionInput);
		optionInput.setBounds(15, 25, 95, 20);
		optionLineBreaks.addItem("customized SQL");
		optionLineBreaks.addItem("1-line SQL");
		optionLineBreaks.setSelectedIndex(0);
		optionLineBreaks.setFont(new Font("Dialog", 0, 10));
		pnlOptions.add(optionLineBreaks);
		optionLineBreaks.setBounds(15, 110, 95, 20);
		aG.add(pnlOptions, "Center");
		pnlFormator.add(aG, "East");
		getContentPane().add(pnlFormator, "card4");

		JMenuBar mainMenuBar = new JMenuBar();
		JMenu menuEdit = new JMenu("Edit");
		JMenuItem menuFormat = new JMenuItem("Format SQL");
		JSeparator ad = new JSeparator();
		JMenuItem menuClearScreen = new JMenuItem("Clear Screen");
		JMenu menuHelp = new JMenu("Help");
		JMenuItem menuExample = new JMenuItem("Example SQL");
		JMenuItem menuExpPlSql = new JMenuItem("Example PL/SQL");
		mainMenuBar.setBackground(color1);
		menuFormat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_MASK));
		menuFormat.addActionListener(new Action_SqlFormat(this));
		menuEdit.add(menuFormat);
		menuEdit.add(ad);
		menuClearScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				KeyEvent.CTRL_MASK));
		menuClearScreen.addActionListener(new Action_ClearScreen(this));
		menuEdit.add(menuClearScreen);
		mainMenuBar.add(menuEdit);
		menuExample.addActionListener(new Action_ShowExample(this));
		menuHelp.add(menuExample);
		menuExpPlSql.addActionListener(new Action_ShowPlSqlExample(this));
		menuHelp.add(menuExpPlSql);
		mainMenuBar.add(menuHelp);
		setJMenuBar(mainMenuBar);
	}

	private void action_ShowPlSqlExample(ActionEvent actionevent) {
		String sqlExample = "CREATE OR REPLACE test_pkg AS   \n     _/************************************************************************  "
				+ "\n  _/* this is a test pl/Sql statement  \n   _/************************************************************************/  "
				+ "\n     _/* DESCRIPTION: ...  \n     \n     _/***********************************************************************/   "
				+ "\n CREATE OR REPLACE PACKAGE BODY test1 IS    g_column1 VARCHAR2(17)  := NULL;  g_column2 VARCHAR2(52)  := NULL;   "
				+ "g_column3_from_column22 VARCHAR2(25) := NULL;        g_column_4711 VARCHAR2(11)           := NULL;   "
				+ "\n     --      \n     -- ************************************************************************/      \n"
				+ "     -- ** FUNCTION - \n     -- **      \n     -- ************************************************************************/      \n"
				+ "     --      \n FUNCTION testfunction (p_column12 IN VARCHAR2) RETURN VARCHAR2       IS        BEGIN            "
				+ "IF NVL(g_emplid1,'X') <> p_emplid THEN                BEGIN                    FOR emp_rec IN c_empl   \n"
				+ "                 LOOP   \n                     IF emp_rec.empl_rcd# > 0 THEN   \n  insert into table1 \n"
				+ "_/*--------- \n Example \n ---------*/(col1,col2,col3,col4,col5,col6,col7)SELECT price.col1 AS col1,  price.col2 AS col2 ,"
				+ " price.col3 AS col3, \n max(price.col4) AS col4, max(price.col5) AS col5, max(price.col6) AS col6, -- comment1\n"
				+ "  max(price.col7) AS col7  _/*  comment2 */ FROM \n ( SELECT store.column1, \n -- =========================================   \n"
				+ " -- Thank you for trying SQLInform      \n  -- =========================================  \n  cast (store.column2 AS integer) AS column2,"
				+ " store.column3, store.column4, store.column5,  substr(store.column6,11,1) AS column6,  store.column7 AS column7  FROM \n"
				+ " ( SELECT library.column1, library.column2, library.column3, CASE library.column4 WHEN cheap THEN digits(library.column27)"
				+ " concat library.column28 ELSE 123456 END AS column4, CASE library.column5 WHEN expensive THEN digits(library.column27) concat "
				+ "library.column28 ELSE 123456 END AS library.column6, CASE column7 WHEN free THEN digits(library.column27) concat library.column28 \n"
				+ " ELSE 123456 END AS column7  FROM ( SELECT integer(substr(onelibrarysales.column1,11,10)) AS column1, substr(onelibrarysales.column2,21,10)"
				+ " AS column2 \n , onelibrarysales.column3, onelibrarysales.column4, substr(onelibrarysales.column5,31,6) AS column5, "
				+ "substr(onelibrarysales.column6,37,2) AS column6, substr(onelibrarysales.column7,39,6) AS column7 FROM ( SELECT alllibrarysales.column1,"
				+ " alllibrarysales.column2, max(alllibrarysales.column3) AS alllibrarysales.column3 \n , max(char(alllibrarysales.column4,iso) concat"
				+ " char(alllibrarysales.column5,iso) concat digits(alllibrarysales.column6) concat (alllibrarysales.column7)) \n AS column5 FROM "
				+ "( SELECT libraryprod.column1, libraryprod.column2, libraryprod.column3, libraryprod.column4, libraryprod.column5, libraryprod.column6,"
				+ " libraryprod.column7 FROM ( SELECT tv.column1, tv.column2, max(digits(tv.column3) concat digits(tv.column4) ) AS librarymax FROM \n "
				+ "db1.v_table1 tv WHERE tv.column1 <> 'Y' AND tv.column1 in ( 'a' , '1' , '12' , '123' , ' 1234' , '12345' , '123456' , '1234567' , "
				+ "'12345678' , '123456789' , '1234567890' , '1 12 123 1234 12345 123456 1234567 12345678' , 'b' , 'c' )  AND tv.column2 >= date(tv.column4) \n"
				+ " AND tv.column3 < date(tv.column15) GROUP BY tv.column1, tv.column2 ) AS libraryprod, db1.table2 th WHERE th.column1 =libraryprod.column1 AND"
				+ " th.column2 =libraryprod.column2 ) AS alllibrarysales GROUP BY alllibrarysales.column1, alllibrarysales.column2 ) AS onelibrarysales ) "
				+ "AS library \n LEFT OUTER JOIN db1.v_table3 librarystat ON librarystat.column1 = library.column1 AND librarystat.column2 = library.column2"
				+ " OR ( librarystat.column4 = library.column4 AND \n librarystat.column5 = library.column5 ) AND ( librarystat.column5 = 'I' OR "
				+ "librarystat.column4 = 'Gold' OR librarystat.column5 = 'Bold' ) AND librarystat.column6 <= 'Z74' ) AS x ) AS price WHERE \n "
				+ "price.column1 < 'R45' OR ( price.column2= 'R46' AND price.column3 = 6 ) GROUP BY price.column1, price.column2, price.column3, "
				+ "price.column4, price.column5, price.column6, price.column7 ;END IF;  END LOOP;end;end if;     END Funcion;  END Package;";
		sqlEditor.setText(sqlExample);
		sqlEditor.setCaretPosition(1);
	}

	private void action_ShowExample(ActionEvent actionevent) {
		StringBuffer stringbuffer;
		(stringbuffer = new StringBuffer())
				.append("------------------------------------------\n-- This is an example SQL\n-- Please click the button <Format SQL>\n"
						+ "-- or the smart key \"ctrl+F\"\n------------------------------------------\n SELECT  price.col1 AS col1, price.col2 AS col2 ,"
						+ " price.col3 AS col3, max(price.col4) AS col4, max(price.col5) AS col5, max(price.col6) AS col6, max(price.col7) AS col7 ");
		stringbuffer.append("FROM    table_1 t1, table_2 t2 ");
		stringbuffer
				.append("WHERE   col1 = col2 AND column_1 = small_column AND column_3411 <= column_12_sup and col1 = 'Test Run' AND column_4532 = c1.dert ");
		stringbuffer.append("UNION ");
		stringbuffer
				.append("\nSELECT  price.col1 AS col1, price.col2 AS col2 , price.col3 AS col3, max(price.col4) AS col4, max(price.col5) AS col5, "
						+ "max(price.col6) AS col6, ");
		stringbuffer
				.append("\n        _/*******************                       ");
		stringbuffer
				.append("\n    * This is a block  *                           ");
		stringbuffer
				.append("\n        * comment within a *                        ");
		stringbuffer.append("\n * SQL statement    *                      ");
		stringbuffer.append("\n        *******************/ ");
		stringbuffer.append("\n     max(price.col7) AS col7 ");
		stringbuffer.append("\nFROM    ");
		stringbuffer
				.append("\n        (SELECT store.column1, cast (store.column2 AS integer) AS column2, -- inline comment     ");
		stringbuffer
				.append("\n                store.columnwe34r3 AS column3, -- inline comment     ");
		stringbuffer
				.append("\n                store.column4_prod AS column4, -- inline comment     ");
		stringbuffer
				.append("\n    store.column5_pre_prod_first AS column5 , -- inline comment     ");
		stringbuffer
				.append("\n        substr(store.column6,11,1) AS column6, -- inline comment     ");
		stringbuffer
				.append("\n store.column7 AS column7 -- inline comment     ");
		stringbuffer.append("\n   FROM    ");
		stringbuffer.append("\n        (SELECT library.column1, ");
		stringbuffer
				.append("\n                        ---------------------    ");
		stringbuffer
				.append("\n                        -- This is a line  --     ");
		stringbuffer
				.append("\n                        -- comment in a    --     ");
		stringbuffer
				.append("\n                        -- SQL statement   --    ");
		stringbuffer
				.append("\n                        ---------------------     ");
		stringbuffer
				.append("\n                        library.column2, library.column3 -- inline comment     ");
		stringbuffer
				.append("\n   , CASE library.column4 WHEN cheap THEN digits(library.column27) concat library.column28 ELSE 123456 END AS column4,"
						+ " CASE library.column5 WHEN expensive THEN digits(library.column27) concat library.column28 ELSE 123456 END AS library.column6,"
						+ " CASE column7 WHEN free THEN digits(library.column27) concat library.column28 ELSE 123456 END AS column7, ");
		stringbuffer.append("\n FROM    ");
		stringbuffer
				.append("\n       (SELECT integer(substr(onelibrarysales.column1,11,10)) AS column1, substr(onelibrarysales.column2,21,10) AS column2 ,"
						+ " onelibrarysales.column3, onelibrarysales.column4, substr(onelibrarysales.column5,31,6) AS column5, "
						+ "substr(onelibrarysales.column6,37,2) AS column6, substr(onelibrarysales.column7,39,6) AS column7, ");
		stringbuffer.append("\n    FROM    ");
		stringbuffer
				.append("\n               (SELECT alllibrarysales.column1, alllibrarysales.column2, max(alllibrarysales.column3) AS alllibrarysales.column3 ,"
						+ " max(char(alllibrarysales.column4,iso) concat char(alllibrarysales.column5,iso) concat digits(alllibrarysales.column6) "
						+ "concat (alllibrarysales.column7)) AS column5 ");
		stringbuffer.append("\n     FROM ");
		stringbuffer
				.append("\n                                  _/*******************                                                              ");
		stringbuffer
				.append("\n                                   * This is a block  *                                                             ");
		stringbuffer
				.append("\n                                        * comment within a *                          ");
		stringbuffer
				.append("\n                                        * SQL statement    *                                                           ");
		stringbuffer
				.append("\n                                        *******************/ ");
		stringbuffer
				.append("\n                                        (SELECT libraryprod.column1, libraryprod.column2, libraryprod.column3, libraryprod.column4, ");
		stringbuffer
				.append("\n                            _/*******************                                      ");
		stringbuffer
				.append("\n                                                * This is a block  *                                 ");
		stringbuffer
				.append("\n                     * comment within a *                                                                      ");
		stringbuffer
				.append("\n                                                * SQL statement    *                                           ");
		stringbuffer.append("\n  *******************/ ");
		stringbuffer
				.append("\n           libraryprod.column5, libraryprod.column6, libraryprod.column7 ");
		stringbuffer.append("\n   FROM    ");
		stringbuffer
				.append("\n      (SELECT tv.column1, tv.column2, max(digits(tv.column3) concat digits(tv.column4) ) AS librarymax ");
		stringbuffer
				.append("\n                                       FROM    db1.v_table1 tv ");
		stringbuffer
				.append("\nWHERE   tv.column1 <> 'Y' AND tv.column1 in ( 'a' , '1' , '12' , '123' , ' 1234' , '12345' , '123456' , '1234567' , "
						+ "'12345678' , '123456789' , '1234567890' , '1 12 123 1234 12345 123456 1234567 12345678' , 'b' , 'c' ) AND "
						+ "tv.column2 >= date(tv.column4) AND tv.column3 < date(tv.column15) ");
		stringbuffer
				.append("\n                                                GROUP BY tv.column1, tv.column2 ");
		stringbuffer.append("\n       ) AS libraryprod, db1.table2 th ");
		stringbuffer
				.append("\n                                        WHERE   th.column1 = libraryprod.column1 AND th.column2 = libraryprod.column2 ");
		stringbuffer.append("\n            ) AS alllibrarysales ");
		stringbuffer
				.append("\n                                GROUP BY alllibrarysales.column1, alllibrarysales.column2 ");
		stringbuffer
				.append("\n                                ) AS onelibrarysales ");
		stringbuffer.append("\n                        ) AS library ");
		stringbuffer
				.append("\n                LEFT OUTER JOIN db1.v_table3 librarystat ");
		stringbuffer
				.append("\n                        ON librarystat.column1 = library.column1 AND librarystat.column2 = library.column2 OR "
						+ "( librarystat.column4 = library.column4 AND librarystat.column5 = library.column5 ) ");
		stringbuffer
				.append("\n                        _/*******************                                              ");
		stringbuffer
				.append("\n                        * This is a block  *                                             ");
		stringbuffer
				.append("\n                        * comment within a *                                              ");
		stringbuffer
				.append("\n                        * SQL statement    *                                           ");
		stringbuffer.append("\n                        *******************/ ");
		stringbuffer
				.append("\n            AND ( librarystat.column5 = 'I' OR librarystat.column4 = 'Gold' OR librarystat.column5 = 'Bold' ) AND "
						+ "librarystat.column6 <= 'Z74' ");
		stringbuffer.append("\n                ) AS x ");
		stringbuffer.append("\n        ) AS price ");
		stringbuffer
				.append("\nWHERE   price.column1 < 'R45' OR ( price.column2= 'R46' ");
		stringbuffer
				.append("\n        _/*******************                              ");
		stringbuffer
				.append("\n        * This is a block  *                             ");
		stringbuffer
				.append("\n        * comment within a *                              ");
		stringbuffer
				.append("\n        * SQL statement    *                           ");
		stringbuffer.append("\n        *******************/ ");
		stringbuffer.append("\n        AND price.column3 = 6 ) ");
		stringbuffer.append("\nGROUP BY price.column1, price.column2, ");
		stringbuffer
				.append("\n        _/*******************                              ");
		stringbuffer
				.append("\n        * This is a block  *                             ");
		stringbuffer
				.append("\n        * comment within a *                              ");
		stringbuffer
				.append("\n        * SQL statement    *                           ");
		stringbuffer.append("\n        *******************/ ");
		stringbuffer
				.append("\n        price.column3, price.column4, price.column5, price.column6, price.column7 ");
		sqlEditor.setText(stringbuffer.toString());
		sqlEditor.setCaretPosition(1);
	}

	private void action_ClearScreen(ActionEvent actionevent) {
		Document f = sqlEditor.getStyledDocument();
		try {
			f.remove(0, f.getLength());
		} catch (Exception _ex) {
		}
		sqlEditor.setCaretPosition(0);
		sqlEditor.requestFocus();
	}

	private void action_SqlFormat(ActionEvent actionevent) {
		String strSqlInput = sqlEditor.getText();
		format(strSqlInput);
	}

	protected final void format(String strSqlInput) {
		if (strSqlInput == null || strSqlInput.trim().length() == 0) {
			sqlEditor.setText("");
			return;
		}
		String outType = (String) optionOutput.getSelectedItem();
		int indentLen = 0;
		if (optionIndent.getSelectedItem().toString().equalsIgnoreCase("Tab")) {
			indentLen = 1;
		} else {
			indentLen = Integer.parseInt(optionIndent.getSelectedItem()
					.toString());
		}
		Document f = sqlEditor.getDocument();
		Formator formator = new Formator(f);
		formator.setToUpper(caseFormat.getSelectedItem().equals(
				"Keywords Uppercase"), caseFormat.getSelectedItem().equals(
				"Whole SQL Uppercase"));
		formator.setToLower(caseFormat.getSelectedItem().equals(
				"Whole SQL Lowercase"));
		formator.setShowLevel(optionShowLevel.isSelected());
		formator.setIndentInfo(indentLen, optionUseTab.isSelected());
		formator.setEmptyLine(optionEmptyLine.isSelected());
		formator.setLineBreakInfo(breakBeforeComma.isSelected(),
				breakAfterComma.isSelected(), breakBeforeAnd.isSelected(),
				breakAfterAnd.isSelected(), breakSubSelect.isSelected(),
				breakBracket.isSelected());
		formator.setOutType(outType);
		formator.setFormatToOneLine(optionLineBreaks.getSelectedItem().equals(
				"1-line SQL"));
		formator.setInputType(optionInput.getSelectedItem().toString());
		formator.setMaxLineLen(120);
		formator.setOutUseColor(optionColor.isSelected());
		formator.setOutShowLineNumber(optionShowLineNumber.isSelected());
		formator.setBreakAfterSelect(breakAfterSelect.isSelected());
		formator.setCaseBreak(breakCase.isSelected());
		sqlEditor.setText("");
		formator.formatToDocument(strSqlInput);
		sqlEditor.setCaretPosition(0);
		sqlEditor.requestFocus();
	}

	static final void action_SqlFormat(SQLinForm sqlinform,
			ActionEvent actionevent) {
		sqlinform.action_SqlFormat(actionevent);
	}

	static final void action_ClearScreen(SQLinForm sqlinform,
			ActionEvent actionevent) {
		sqlinform.action_ClearScreen(actionevent);
	}

	static final void action_ShowExample(SQLinForm sqlinform,
			ActionEvent actionevent) {
		sqlinform.action_ShowExample(actionevent);
	}

	static final void action_ShowPlSqlExample(SQLinForm sqlinform,
			ActionEvent actionevent) {
		sqlinform.action_ShowPlSqlExample(actionevent);
	}
}

// Unreferenced inner class SQLinForm_200/U
class Action_ShowPlSqlExample implements ActionListener {
	private final SQLinForm a;

	public final void actionPerformed(ActionEvent actionevent) {
		SQLinForm.action_ShowPlSqlExample(a, actionevent);
	}

	Action_ShowPlSqlExample(SQLinForm sqlinform) {
		a = sqlinform;
	}
}

// Unreferenced inner class SQLinForm_200/Q
final class Action_SqlFormat implements ActionListener {
	Action_SqlFormat(SQLinForm sqlinform) {
		a = sqlinform;
	}

	public final void actionPerformed(ActionEvent actionevent) {
		SQLinForm.action_SqlFormat(a, actionevent);
	}

	private final SQLinForm a;
}

// Unreferenced inner class SQLinForm_200/D
final class Action_ClearScreen implements ActionListener {
	private final SQLinForm a;

	Action_ClearScreen(SQLinForm sqlinform) {
		a = sqlinform;
	}

	public final void actionPerformed(ActionEvent actionevent) {
		SQLinForm.action_ClearScreen(a, actionevent);
	}
}

// Unreferenced inner class SQLinForm_200/i
class Action_ShowExample implements ActionListener {
	private final SQLinForm a;

	public final void actionPerformed(ActionEvent actionevent) {
		SQLinForm.action_ShowExample(a, actionevent);
	}

	Action_ShowExample(SQLinForm sqlinform) {
		a = sqlinform;
	}
}

final class Formator {
	public Formator(Document doc) {
		aJ = new boolean[50];
		aK = new boolean[50];
		aL = new boolean[50];
		aM = new boolean[50];
		colorAttrPink = new SimpleAttributeSet();
		colorAttrDarkGray = new SimpleAttributeSet();
		colorAttrOther = new SimpleAttributeSet();
		colorAttrRed = new SimpleAttributeSet();
		colorAttrBlue = new SimpleAttributeSet();
		colorAttrBlack = new SimpleAttributeSet();
		colorAttrGreen = new SimpleAttributeSet();
		colorAttrGray = new SimpleAttributeSet();
		boldFont = new SimpleAttributeSet();
		notBoldFont = new SimpleAttributeSet();
		init();
		document = doc;
	}

	protected final void init() {
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrPink)),
				Color.PINK);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrRed)),
				Color.RED);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrBlack)),
				Color.BLACK);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrGray)),
				Color.GRAY);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrDarkGray)),
				Color.DARK_GRAY);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrOther)),
				new Color(100, 140, 140));
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrGreen)),
				Color.GREEN);
		StyleConstants.setForeground(
				((javax.swing.text.MutableAttributeSet) (colorAttrBlue)),
				Color.BLUE);
		StyleConstants.setBold(
				((javax.swing.text.MutableAttributeSet) (boldFont)), true);
		StyleConstants.setBold(
				((javax.swing.text.MutableAttributeSet) (notBoldFont)), false);
		isKeyToUpper = true;
		isAllToUpper = false;
		isToLower = false;
		isShowLevel = false;
		outUseColor = true;
		outUseLineNumber = false;
		isBreakAfterSelect = true;
		isCaseBreak = true;
		quoteChar = "'";
		indentLen = 4;
		isEmptyLine = true;
		isBreakBeforeComma = false;
		isBreakAfterComma = true;
		isBreakBeforeAnd = false;
		isBreakAfterAnd = true;
		isUseTab = false;
		isFormatToOneLine = false;
		outType = "SQL";
		inputType = "Any SQL";
		isBreakSubSelect = true;
		isBreakBracket = true;
		curIndentLen = 0;
		lineNumber = 0;
	}

	public final void setToUpper(boolean flag, boolean flag1) {
		isKeyToUpper = flag;
		isAllToUpper = flag1;
	}

	public final void setToLower(boolean flag) {
		isToLower = flag;
	}

	public final void setShowLevel(boolean flag) {
		isShowLevel = flag;
	}

	public final void setIndentInfo(int len, boolean useTab) {
		indentLen = len;
		isUseTab = useTab;
		if (isUseTab)
			indentLen = 1;
	}

	public final void setEmptyLine(boolean flag) {
		isEmptyLine = flag;
	}

	public final void setLineBreakInfo(boolean flag, boolean flag1,
			boolean flag2, boolean flag3, boolean flag4, boolean flag5) {
		isBreakBeforeComma = flag;
		isBreakAfterComma = flag1;
		isBreakBeforeAnd = flag2;
		isBreakAfterAnd = flag3;
		isBreakSubSelect = flag4;
		isBreakBracket = flag5;
	}

	public final void setInputType(String s1) {
		inputType = s1;
	}

	public final void setOutType(String s1) {
		outType = s1;
		if (outType.equalsIgnoreCase("SQL")) {
			sqlStart = "";
			linePrefix = "";
			newLineEnd = "\n";
			lineEnd = "";
		}
		if (outType.equalsIgnoreCase("Java append")) {
			sqlStart = "StringBuffer SQL = new StringBuffer();\n";
			linePrefix = "SQL.append(\"";
			newLineEnd = "\");\n";
			lineEnd = "\");";
		}
		if (outType.equalsIgnoreCase("Java")) {
			sqlStart = "SQL = ";
			linePrefix = "\"";
			newLineEnd = "\" +\n";
			lineEnd = "\"";
		}
		if (outType.equalsIgnoreCase("C#")) {
			sqlStart = "StringBuilder SQL = new StringBuilder();\n";
			linePrefix = "SQL.Append(\"";
			newLineEnd = "\");\n";
			lineEnd = "\");";
		}
		if (outType.equalsIgnoreCase("VB") || outType.equalsIgnoreCase("ASP")) {
			sqlStart = "SQL = \"\"\n";
			linePrefix = "SQL = SQL & \"";
			newLineEnd = "\"\n";
			lineEnd = "\"";
		}
		if (outType.equalsIgnoreCase("VB append")) {
			sqlStart = "Dim SQL As New StringBuilder\n";
			linePrefix = "SQL.Append(\"";
			newLineEnd = "\")\n";
			lineEnd = "\")";
		}
		if (outType.equalsIgnoreCase("VB3")) {
			sqlStart = "SQL= ";
			linePrefix = "\"";
			newLineEnd = "\" & _\n";
			lineEnd = "\"";
		}
		if (outType.equalsIgnoreCase("PHP")) {
			sqlStart = "$SQL  = \"\";\n";
			linePrefix = "$SQL .= \"";
			newLineEnd = "\";\n";
			lineEnd = "\";";
		}
		if (outType.equalsIgnoreCase("Concatenated SQL")) {
			sqlStart = "SQL=";
			linePrefix = "\"";
			newLineEnd = "\" ||\n";
			lineEnd = "\"";
		}
	}

	public final void setFormatToOneLine(boolean flag) {
		isFormatToOneLine = flag;
	}

	public final void setMaxLineLen(int i1) {
		maxLineLen = i1;
		if (maxLineLen < 80)
			maxLineLen = 80;
	}

	public final void setOutUseColor(boolean flag) {
		outUseColor = flag;
	}

	public final void setOutShowLineNumber(boolean flag) {
		outUseLineNumber = flag;
	}

	public final void setBreakAfterSelect(boolean flag) {
		isBreakAfterSelect = flag;
	}

	public final void setCaseBreak(boolean flag) {
		isCaseBreak = flag;
	}

	public final Document formatToDocument(String sqlInput) {
		int count = sqlInput.length() / 2;
		if (count <= 18) {
			count = 18;
		}
		if (count > maxWordCount) {
			count = maxWordCount;
		}
		String[] sqlTokens = new String[count];
		sqlNodes = new String[count];
		count = count / 3;
		lineIndent = new int[count];
		keyWords = new String[count];
		MAXBRACKETCOUNT = 50;
		G = new String[MAXBRACKETCOUNT];
		H = new String[MAXBRACKETCOUNT];
		sqlTokens[0] = "";
		String filterChars = "[]|-_/*:;,()<>= \\ \n \t \f \r \b" + quoteChar;
		String filterChars2 = "[]|_/*:;,()<>= \\ \n \t \f \r \b" + quoteChar;
		StringTokenizer stringTokenizer = new StringTokenizer(sqlInput,
				filterChars, true);
		currentNodeIndex = 1;
		sqlInLngStarted = false;
		count = sqlInput.length() / 2;
		do {
			if (!stringTokenizer.hasMoreTokens() || currentNodeIndex >= count)
				break;
			String word = stringTokenizer.nextToken();
			if (" ".equals(word)) {
				sqlTokens[currentNodeIndex++] = word;
				while (stringTokenizer.hasMoreTokens()
						&& (word = stringTokenizer.nextToken()).equals(" "))
					;
			}
			// if (false) {
			// if (sqlInLngStarted) {
			// if (word.equalsIgnoreCase(sqlChInLng)) {
			// sqlInLngStarted = false;
			// sqlTokens[currentNodeIndex] = "\n";
			// } else {
			// sqlTokens[currentNodeIndex] = word;
			// }
			// currentNodeIndex++;
			// } else if (word.equalsIgnoreCase(sqlChInLng))
			// sqlInLngStarted = true;
			// } else {
			sqlTokens[currentNodeIndex] = word;
			currentNodeIndex++;
			// }
		} while (true);
		currentNodeIndex--;
		sqlNodeCount = currentNodeIndex;
		currentNodeIndex = 0;
		int i1;
		for (i1 = 1; i1 < sqlNodeCount; i1++) {
			sqlNodes[currentNodeIndex] = sqlTokens[i1];
			if (sqlTokens[i1].length() == 1) {
				char cur = sqlTokens[i1].charAt(0);
				char next = sqlTokens[i1 + 1].length() == 0 ? 0
						: sqlTokens[i1 + 1].charAt(0);
				if (cur == '\\'
						|| sqlTokens[i1].equalsIgnoreCase(quoteChar)
						&& sqlTokens[i1 + 1].equalsIgnoreCase(quoteChar) // ''
						|| cur == '/'
						&& next == '/'
						|| cur == '/'
						&& next == '*' // // /*
						|| cur == '*'
						&& next == '/'
						|| cur == ':'
						&& next == '=' // */ :=
						|| cur == '='
						&& next == ':'
						|| cur == '*'
						&& next == '=' // =: *=
						|| cur == '!'
						&& next == '='
						|| cur == '<'
						&& next == '>' // != <>
						|| cur == '=' && next == '*'
						|| cur == '|'
						&& next == '|' // =* ||
						|| cur == '>' && next == '=' || cur == '<'
						&& next == '=') { // >= <=
					sqlNodes[currentNodeIndex] = sqlNodes[currentNodeIndex]
							+ sqlTokens[i1 + 1];
					i1++;
				} else if (cur == '-') {
					if (currentNodeIndex > 0
							&& !isFirstInSec(sqlNodes[currentNodeIndex - 1]
									.substring(sqlNodes[currentNodeIndex - 1]
											.length() - 1), filterChars2)
							&& !sqlTokens[i1 + 1].equalsIgnoreCase("-")) {
						currentNodeIndex--;
						sqlNodes[currentNodeIndex] = sqlNodes[currentNodeIndex]
								+ sqlTokens[i1];
					}
					if (!isFirstInSec(sqlTokens[i1 + 1], filterChars2)) {
						sqlNodes[currentNodeIndex] = sqlNodes[currentNodeIndex]
								+ sqlTokens[i1 + 1];
						i1++;
					}
				}
			}
			currentNodeIndex++;
		}
		if (i1 == sqlNodeCount)
			sqlNodes[currentNodeIndex] = sqlTokens[i1];
		else
			currentNodeIndex--;
		sqlNodeCount = currentNodeIndex;
		bracketOpenInit();
		preWordNonBracket = " ";
		preWordNonSpace = " ";
		L = " ";
		quoteStrings = "";
		try {
			document.insertString(document.getLength(), sqlStart,
					((javax.swing.text.AttributeSet) (colorAttrBlack)));
			curIndentLen = sqlStart.length();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (currentNodeIndex = 0; currentNodeIndex <= sqlNodeCount; currentNodeIndex++) {
			P = 0;
			newLineCount = Q;
			Q = 0;
			ao = false;
			temp = 0;
			S = 0;
			breakMainWordCount = 0;
			U = 0;
			isKeyWord = false;
			z = false;
			B = false;
			aP = false;
			aO = false;
			breakMainWord = false;
			A = false;
			boolean needInsertNode = true;
			filterTabChar();
			if (!commentStart && !isQuoteStart) {
				preWordNonBracket = preNonBracketWord(currentNodeIndex);
				preWordNonSpace = preNonSpaceWord(currentNodeIndex);
				L = preNonSpaceWord(x);
			}
			if (!commentStart
					&& !isQuoteStart
					&& !sqlInLngStarted
					&& isBreakMainWord(sqlNodes[currentNodeIndex],
							currentNodeIndex)) {
				breakMainWord = true;
				w = openBracketCount;
				sqlInLngStarted = true;
			}
			if (!isQuoteStart)
				checkComment();
			if (!commentStart)
				checkQuoteStrings();
			if (isQuoteStart && !isQuoteEnd)
				needInsertNode = false;
			if (sqlNodes[currentNodeIndex].equals("\n")
					&& (inputType.equalsIgnoreCase("Any SQL")
							|| inputType.equalsIgnoreCase("PL/SQL")
							|| inputType.equalsIgnoreCase("T-SQL")
							|| inputType.equalsIgnoreCase("Transact-SQL")
							|| inputType.equalsIgnoreCase("Sybase")
							|| inputType.equalsIgnoreCase("Informix") || inputType
							.equalsIgnoreCase("DB2/UDB")))
				checkNewLine();
			if (!commentStart && !isQuoteStart
					&& sqlNodes[currentNodeIndex].equals(" ")
					&& preWord(currentNodeIndex).endsWith(" "))
				needInsertNode = false;
			if (commentStart && isFormatToOneLine)
				needInsertNode = false;
			if ((lineCommentStart && !ao || isQuoteStart && !isQuoteEnd || sqlNodes[currentNodeIndex]
					.equals(" "))
					&& newLineCount > 0) {
				P = newLineCount;
				newLineCount = 0;
			}
			if (!commentStart && !isQuoteStart) {
				if (sqlNodes[currentNodeIndex].equals("("))
					bracketStart();
				if (sqlNodes[currentNodeIndex].equals(")"))
					bracketEnd();
				if (!sqlInLngStarted) {
					if (inputType.equalsIgnoreCase("Any SQL")
							|| inputType.equalsIgnoreCase("PL/SQL")
							|| inputType.equalsIgnoreCase("T-SQL")
							|| inputType.equalsIgnoreCase("Transact-SQL")
							|| inputType.equalsIgnoreCase("Sybase")
							|| inputType.equalsIgnoreCase("Informix")
							|| inputType.equalsIgnoreCase("DB2/UDB"))
						h();
				}
				if (sqlInLngStarted && !sqlNodes[currentNodeIndex].equals(" ")) {
					checkKeyWord();
					if (inputType.equalsIgnoreCase("PL/SQL"))
						checkPLKeyWord();
				}
			}
			if (needInsertNode)
				insertNode();
			Q = P;
			if (isQuoteEnd) {
				isQuoteStart = false;
				quoteStrings = "";
				isQuoteEnd = false;
			}
			if (commentEnd) {
				commentStart = false;
				commentEnd = false;
			}
			if (blockCommentEnd) {
				blockCommentStart = false;
				blockCommentEnd = false;
			}
			if (lineCommentEnd) {
				lineCommentStart = false;
				lineCommentEnd = false;
			}
		}
		try {
			document.insertString(document.getLength(), lineEnd,
					((javax.swing.text.AttributeSet) (colorAttrBlack)));
		} catch (Exception _ex) {
		}
		return document;
	}

	public final String writeFormattedToHtmlEditor(StyledDocument styleddocument) {
		try {
			StringWriter stringwriter;
			SqlFormatWriter writer;
			StyledDocument styleddocument1 = styleddocument;
			stringwriter = new StringWriter();
			writer = new SqlFormatWriter(((java.io.Writer) (stringwriter)),
					styleddocument1);
			writer.write();
			String s1;
			s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = (s1 = stringwriter
					.toString())
					.replaceAll("bold:normal;",
							"bold:normal;align: left; margin-top: 0; margin-bottom: 0;"))
					.replaceAll("size:3;", "size:1;")).replaceAll("New\">    ",
					"New\">")).replaceAll("New\">    ", "New\">")).replaceAll(
					"New\">    ", "New\">")).replaceAll("New\">    ", "New\">"))
					.replaceAll("New\">  ", "New\">")).replaceAll("New\">  ",
					"New\">")).replaceAll("New\">  ", "New\">")).replaceAll(
					"New\">  ", "New\">")).replaceAll("New\">  ", "New\">"))
					.replaceAll("New\">  ", "New\">")).replaceAll("New\"> ",
					"New\">")).replaceAll("        </span>", "</span>"))
					.replaceAll("        </span>", "</span>")).replaceAll(
					"        </span>", "</span>")).replaceAll("    </span>",
					"</span>")).replaceAll("    </span>", "</span>"))
					.replaceAll("    </span>", "</span>")).replaceAll(
					"    </span>", "</span>")).replaceAll("  </span>",
					"</span>")).replaceAll("  </span>", "</span>")).replaceAll(
					"  </span>", "</span>")).replaceAll(" </span>", "</span>"))
					.replaceAll(" </span>", "</span>")).replaceAll("\n</span>",
					"</span>")).replaceAll("        </p>", "</p>")).replaceAll(
					"        </p>", "</p>")).replaceAll("        </p>", "</p>"))
					.replaceAll("    </p>", "</p>")).replaceAll("    </p>",
					"</p>")).replaceAll("    </p>", "</p>")).replaceAll(
					"    </p>", "</p>")).replaceAll("  </p>", "</p>"))
					.replaceAll("  </p>", "</p>")).replaceAll("  </p>", "</p>"))
					.replaceAll(" </p>", "</p>")).replaceAll(" </p>", "</p>"))
					.replaceAll("\n</p>", "</p>")).replaceAll("        <span",
					"<span")).replaceAll("        <span", "<span")).replaceAll(
					"        <span", "<span")).replaceAll("    <span", "<span"))
					.replaceAll("    <span", "<span")).replaceAll("    <span",
					"<span")).replaceAll("    <span", "<span")).replaceAll(
					"  <span", "<span")).replaceAll("  <span", "<span"))
					.replaceAll("  <span", "<span")).replaceAll(" <span",
					"<span")).replaceAll(" <span", "<span")).replaceAll(
					"\n<span", "<span")).replaceFirst("p.default",
					"\np.default");
			return s1;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return "Error 201 in SQLForm";
	}

	protected final void checkComment() {
		if (!commentStart
				&& (sqlNodes[currentNodeIndex].startsWith("--") || sqlNodes[currentNodeIndex]
						.equalsIgnoreCase("//"))) {
			lineCommentStart = true;
			commentStart = true;
			if (an) {
				ao = true;
				an = false;
			}
		}
		if (!commentStart && sqlNodes[currentNodeIndex].equals("_/*")) {
			blockCommentStart = true;
			commentStart = true;
			newLineCount = 1;
			if (an) {
				ao = true;
				an = false;
			}
		}
		if (sqlNodes[currentNodeIndex].equals("\n") && lineCommentStart) {
			lineCommentEnd = true;
			commentEnd = true;
		}
		if (blockCommentStart && sqlNodes[currentNodeIndex].equals("*/")) {
			commentEnd = true;
			blockCommentEnd = true;
			lineCommentEnd = true;
		}
		if (commentEnd)
			P = 1;
	}

	protected final void checkQuoteStrings() {
		if (sqlNodes[currentNodeIndex].equals(((Object) (quoteChar))))
			if (isQuoteStart)
				isQuoteEnd = true;
			else
				isQuoteStart = true;
		if (isQuoteStart)
			quoteStrings = quoteStrings + sqlNodes[currentNodeIndex];
		if (isQuoteEnd)
			sqlNodes[currentNodeIndex] = quoteStrings;
	}

	protected final void filterTabChar() {
		String s1 = "";
		int j1 = sqlNodes[currentNodeIndex].length();
		int i1;
		for (i1 = 0; i1 < j1 - 1; i1++)
			if (sqlNodes[currentNodeIndex].substring(i1, i1 + 2).equals("\\t"))
				i1++;
			else
				s1 = s1 + sqlNodes[currentNodeIndex].substring(i1, i1 + 1);
		if (i1 < j1)
			s1 = s1 + sqlNodes[currentNodeIndex].substring(j1 - 1, j1);
		sqlNodes[currentNodeIndex] = s1;
		if (sqlNodes[currentNodeIndex].equals("\t")
				|| sqlNodes[currentNodeIndex].equals("\f")
				|| sqlNodes[currentNodeIndex].equals("\r")
				|| sqlNodes[currentNodeIndex].equals("\b"))
			sqlNodes[currentNodeIndex] = " ";
	}

	protected final void checkNewLine() {
		sqlNodes[currentNodeIndex] = " ";
		if (!isQuoteStart
				&& !blockCommentStart
				&& (nextNonSpaceWord(currentNodeIndex).startsWith("--")
						|| nextNonSpaceWord(currentNodeIndex).equalsIgnoreCase(
								"_/*") || nextNonSpaceWord(currentNodeIndex)
						.equalsIgnoreCase("//"))) {
			an = true;
			P = 1;
		}
		if (commentStart)
			P = 1;
	}

	protected final void checkKeyWord() {
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("select")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("update")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("with")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("create")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("insert")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("declare")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("drop")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("delete")) {
			H[openBracketCount] = " ";
			keyWordAppear();
			A = true;
			newLineCount = 1;
			temp = 1;
			U = 2;
			aJ[openBracketCount] = true;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("from")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("where")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("group")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("having")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("sort")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("into")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("order")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("fetch")) {
			keyWordAppear();
			A = true;
			temp = 1;
			U = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("distinct")) {
			keyWordAppear();
			if (preWordNonBracket.equalsIgnoreCase("select")) {
				A = true;
				newLineCount = 0;
			}
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("by")
				&& (preWordNonBracket.equalsIgnoreCase("group")
						|| preWordNonBracket.equalsIgnoreCase("order") || preWordNonBracket
						.equalsIgnoreCase("sort"))) {
			keyWordAppear();
			A = true;
			newLineCount = 0;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("all")) {
			keyWordAppear();
			newLineCount = 0;
			if (preWordNonBracket.equalsIgnoreCase("union"))
				P = 2;
			if (preWordNonBracket.equalsIgnoreCase("select")) {
				A = true;
				P = 1;
			}
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("between")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("values")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("by"))
			keyWordAppear();
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("as"))
			newLineCount = 0;
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("union")) {
			keyWordAppear();
			temp = 2;
			U = 1;
			newLineCount = 2;
			P = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("left")
				&& (nextNonSpaceWord(currentNodeIndex)
						.equalsIgnoreCase("outer") || nextNonSpaceWord(
						currentNodeIndex).equalsIgnoreCase("join"))) {
			keyWordAppear();
			temp = 1;
			U = 1;
			newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("right")
				&& (nextNonSpaceWord(currentNodeIndex)
						.equalsIgnoreCase("outer") || nextNonSpaceWord(
						currentNodeIndex).equalsIgnoreCase("join"))) {
			keyWordAppear();
			temp = 1;
			U = 1;
			newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("full")
				&& (nextNonSpaceWord(currentNodeIndex)
						.equalsIgnoreCase("outer")
						|| nextNonSpaceWord(currentNodeIndex).equalsIgnoreCase(
								"inner") || nextNonSpaceWord(currentNodeIndex)
						.equalsIgnoreCase("join"))) {
			keyWordAppear();
			temp = 1;
			U = 1;
			newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("inner")
				&& nextNonSpaceWord(currentNodeIndex).equalsIgnoreCase("join")) {
			keyWordAppear();
			temp = 1;
			U = 1;
			if (!preWordNonBracket.equalsIgnoreCase("full"))
				newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("outer")) {
			keyWordAppear();
			temp = 1;
			U = 1;
			if (!preWordNonBracket.equalsIgnoreCase("left")
					&& !preWordNonBracket.equalsIgnoreCase("right")
					&& !preWordNonBracket.equalsIgnoreCase("full"))
				newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("join")) {
			keyWordAppear();
			A = true;
			P = 0;
			temp = 1;
			U = 1;
			if (!preWordNonBracket.equalsIgnoreCase("inner")
					&& !preWordNonBracket.equalsIgnoreCase("outer")
					&& !preWordNonBracket.equalsIgnoreCase("left")
					&& !preWordNonBracket.equalsIgnoreCase("right")
					&& !preWordNonBracket.equalsIgnoreCase("full"))
				newLineCount = 2;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("case")) {
			keyWordAppear();
			U = 1;
			if (isBreakAfterComma && !isBreakBeforeComma || isCaseBreak)
				newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("when")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("then")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("else")) {
			B = true;
			keyWordAppear();
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("end")) {
			B = true;
			keyWordAppear();
			newLineCount = 1;
			P = 1;
			temp = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("set")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("iif")) {
			keyWordAppear();
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("or")) {
			if (isBreakAfterAnd)
				P = 1;
			if (isBreakBeforeAnd)
				newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("and")
				&& keyWordCount > 1
				&& !keyWords[keyWordCount - 1].equalsIgnoreCase("between")) {
			if (isBreakAfterAnd)
				P = 1;
			if (isBreakBeforeAnd)
				newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("on")) {
			keyWordAppear();
			A = true;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("with")) {
			keyWordAppear();
			temp = 1;
			U = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("ALL")
				&& preNonSpaceWord(currentNodeIndex).equalsIgnoreCase("Insert")) {
			keyWordAppear();
			newLineCount = 0;
			P = 1;
		}
		if (sqlNodes[currentNodeIndex].equals(",") && aJ[openBracketCount])
			try {
				Integer.parseInt(nextWord(currentNodeIndex));
				Integer.parseInt(preWord(currentNodeIndex));
			} catch (NumberFormatException _ex) {
				if (isBreakAfterComma)
					P = 1;
				if (isBreakBeforeComma)
					newLineCount = 1;
			}
		if (sqlNodes[currentNodeIndex].equals(";")) {
			P = 2;
			S = 1;
			H[openBracketCount] = " ";
			sqlInLngStarted = false;
		}
	}

	protected final void checkPLKeyWord() {
		if ((sqlNodes[currentNodeIndex].equalsIgnoreCase("Rollup") || sqlNodes[currentNodeIndex]
				.equalsIgnoreCase("Cube"))
				&& preNonSpaceWord(currentNodeIndex).equalsIgnoreCase("with"))
			keyWordAppear();
		if ((sqlNodes[currentNodeIndex].equalsIgnoreCase("Rollup") || sqlNodes[currentNodeIndex]
				.equalsIgnoreCase("Cube"))
				&& preNonSpaceWord(currentNodeIndex).equalsIgnoreCase("by"))
			keyWordAppear();
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Returning")
				&& H[openBracketCount].equalsIgnoreCase("Insert")) {
			keyWordAppear();
			temp = 1;
			U = 1;
			newLineCount = 1;
			A = true;
		}
	}

	protected final void h() {
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Create")) {
			U = 1;
			temp = 1;
			newLineCount = 1;
			z = true;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Function")) {
			z = true;
			U = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Procedure")) {
			z = true;
			U = 1;
			temp = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("If")) {
			z = true;
			if (!preNonSpaceWord(currentNodeIndex).equalsIgnoreCase("end")) {
				U = 1;
				newLineCount = 1;
			}
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("loop")) {
			z = true;
			if (!preNonSpaceWord(currentNodeIndex).equalsIgnoreCase("end")) {
				U = 1;
				P = 1;
				newLineCount = 1;
			}
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("declare")) {
			z = true;
			U = 1;
			temp = 1;
			P = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("begin")) {
			z = true;
			U = 1;
			if (!preWordNonSpace.equalsIgnoreCase("then")
					&& !preWordNonSpace.equalsIgnoreCase("else")
					&& !preWordNonSpace.equalsIgnoreCase("elseif")
					&& !preWordNonSpace.equalsIgnoreCase("elsif"))
				temp = 1;
			P = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("is")
				&& !nextNonSpaceWord(currentNodeIndex).equalsIgnoreCase("Null")
				&& !nextNonSpaceWord(currentNodeIndex).equalsIgnoreCase("Not")) {
			z = true;
			P = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Exception")) {
			z = true;
			U = 1;
			temp = 1;
			P = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("end")) {
			z = true;
			temp = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("case")) {
			z = true;
			U = 1;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("when")) {
			z = true;
			newLineCount = 1;
			U = 1;
			temp = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("For")) {
			z = true;
			newLineCount = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("then")) {
			z = true;
			P = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("in"))
			z = true;
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("else")) {
			z = true;
			newLineCount = 1;
			P = 1;
			U = 1;
			temp = 1;
		}
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("elseif")
				|| sqlNodes[currentNodeIndex].equalsIgnoreCase("elsif")) {
			z = true;
			newLineCount = 1;
			P = 1;
			U = 1;
			temp = 1;
		}
		if (sqlNodes[currentNodeIndex].equals(";")) {
			z = true;
			P = 1;
		}
	}

	protected final void insertNode() {
		if (preNonSpaceWord(currentNodeIndex).equalsIgnoreCase(" "))
			newLineCount = 1;
		if (sqlNodes[currentNodeIndex].equalsIgnoreCase("select") && !am) {
			if (!isBreakSubSelect)
				newLineCount = 0;
			am = true;
		}
		if (A && isBreakAfterSelect)
			P = 1;
		if (sqlNodes[currentNodeIndex].equals("(") && aP && !isBreakBracket) {
			P = 0;
			newLineCount = 0;
			aK[openBracketCount] = false;
			U = 0;
		}
		if (sqlNodes[currentNodeIndex].equals(")") && aP && !isBreakBracket) {
			P = 0;
			newLineCount = 0;
			temp = 0;
		}
		if (!isCaseBreak && B) {
			P = 0;
			newLineCount = 0;
		}
		if (isBreakBeforeComma
				&& sqlNodes[currentNodeIndex].equalsIgnoreCase("Case"))
			newLineCount = 0;
		if (isFormatToOneLine && sqlInLngStarted) {
			P = 0;
			newLineCount = 0;
		}
		if (!blockCommentStart && isEmptyLine && newLineCount > 1)
			newLineCount = 1;
		if (breakMainWord)
			breakMainWordCount++;
		if (temp > 1)
			temp = 1;
		indentCharCount = indentCharCount - temp * indentLen;
		indentCharCount = indentCharCount + breakMainWordCount * indentLen;
		int specialIndent = 0;
		if (sqlInLngStarted && !commentStart && !isQuoteStart
				&& !isFormatToOneLine
				&& !sqlNodes[currentNodeIndex].equalsIgnoreCase(" ")) {
			if (isBreakBeforeComma && aJ[openBracketCount]) {
				if (sqlNodes[currentNodeIndex].equals(",")
						&& !nextWord(currentNodeIndex).equalsIgnoreCase(" "))
					insertBlankNode();
				if (sqlNodes[currentNodeIndex].equals(","))
					specialIndent = -2;
			}
			if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Select")
					&& preWordNonSpace.equalsIgnoreCase("(")
					&& preWord(currentNodeIndex).equalsIgnoreCase(" ")
					&& !isBreakSubSelect)
				removeDocLastChar();
			if (!isBreakAfterSelect) {
				if (preWordNonSpace.equalsIgnoreCase("from"))
					insertWhiteSpace(indentLen - preWordNonSpace.length() - 1);
				if (preWordNonSpace.equalsIgnoreCase("where"))
					insertWhiteSpace(indentLen - preWordNonSpace.length() - 1);
				if (preWordNonSpace.equalsIgnoreCase("Select")) {
					if (L.equalsIgnoreCase("(") && !isBreakSubSelect)
						insertWhiteSpace(indentLen - preWordNonSpace.length()
								- 2 - 1);
					else
						insertWhiteSpace(indentLen - preWordNonSpace.length()
								- 1);
				}
			}
		}
		if (curIndentLen + sqlNodes[currentNodeIndex].length()
				+ newLineEnd.length() > maxLineLen
				&& !isFormatToOneLine) {
			if (sqlNodes[currentNodeIndex].equals(" ") && !isQuoteStart
					&& !commentStart)
				sqlNodes[currentNodeIndex] = "";
			else if (newLineCount == 0)
				newLineCount = 1;
			if (lineCommentStart
					&& !sqlNodes[currentNodeIndex].startsWith("--"))
				sqlNodes[currentNodeIndex] = "-- " + sqlNodes[currentNodeIndex];
		}
		for (int j1 = 1; j1 <= newLineCount; j1++) {
			lineNumber++;
			try {
				if (outUseLineNumber) {
					NumberFormat numberformat;
					(numberformat = NumberFormat.getNumberInstance())
							.setMinimumIntegerDigits(3);
					String ar = numberformat.format(lineNumber);
					if (lineNumber == 1)
						document
								.insertString(
										document.getLength(),
										linePrefix + ar + " ",
										((javax.swing.text.AttributeSet) (colorAttrBlack)));
					if (lineNumber > 1)
						document
								.insertString(
										document.getLength(),
										newLineEnd + linePrefix + ar + " ",
										((javax.swing.text.AttributeSet) (colorAttrBlack)));
					curIndentLen = linePrefix.length() + ar.length()
							+ " ".length();
				} else {
					if (lineNumber == 1)
						document
								.insertString(
										document.getLength(),
										linePrefix,
										((javax.swing.text.AttributeSet) (colorAttrBlack)));
					if (lineNumber > 1)
						document
								.insertString(
										document.getLength(),
										newLineEnd + linePrefix,
										((javax.swing.text.AttributeSet) (colorAttrBlack)));
					curIndentLen = linePrefix.length();
				}
			} catch (Exception _ex) {
			}

			String space = "";
			for (int k2 = 0; k2 < indentCharCount + specialIndent; k2++) {
				if (isShowLevel && k2 % indentLen == 0 && k2 < indentCharCount) {
					space = space + "|";
					continue;
				}
				if (isUseTab)
					space = space + "\t";
				else
					space = space + " ";
			}
			try {
				document.insertString(document.getLength(), space,
						((javax.swing.text.AttributeSet) (colorAttrBlack)));
				curIndentLen = curIndentLen + space.length();
			} catch (Exception _ex) {
			}
		}
		if ((isKeyWord && isKeyToUpper || isAllToUpper) && !commentStart
				&& !isQuoteStart)
			sqlNodes[currentNodeIndex] = sqlNodes[currentNodeIndex]
					.toUpperCase();
		if (isToLower && !commentStart && !isQuoteStart)
			sqlNodes[currentNodeIndex] = sqlNodes[currentNodeIndex]
					.toLowerCase();
		curIndentLen = curIndentLen + sqlNodes[currentNodeIndex].length();
		lineIndent[lineNumber] = curIndentLen;
		SimpleAttributeSet bD = colorAttrBlack;
		SimpleAttributeSet bE = new SimpleAttributeSet();
		if (commentStart || isQuoteStart) {
			bD = colorAttrGray;
		} else {
			if (!sqlInLngStarted) {
				if (z)
					bD = colorAttrOther;
			} else if (isKeyWord) {
				bD = colorAttrBlue;
				if (sqlNodes[currentNodeIndex].equalsIgnoreCase("Union")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("Intersect")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("Select")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("insert")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("declare")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("drop")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("create")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("update")
						|| sqlNodes[currentNodeIndex]
								.equalsIgnoreCase("delete")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("left")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("right")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("inner")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("outer")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("full")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("join"))
					bD = colorAttrRed;
				if (sqlNodes[currentNodeIndex].equalsIgnoreCase("case")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("when")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("then")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("else")
						|| sqlNodes[currentNodeIndex].equalsIgnoreCase("end"))
					bD = boldFont;
			}
			if (sqlNodes[currentNodeIndex].equalsIgnoreCase("(")) {
				StyleConstants.setForeground(
						((javax.swing.text.MutableAttributeSet) (bE)),
						COLORS[openBracketCount % colorCount]);
				bD = bE;
			}
			if (sqlNodes[currentNodeIndex].equalsIgnoreCase(")")) {
				StyleConstants.setForeground(
						((javax.swing.text.MutableAttributeSet) (bE)),
						COLORS[(openBracketCount + 1) % colorCount]);
				bD = bE;
			}
		}
		try {
			if (outUseColor)
				document.insertString(document.getLength(),
						sqlNodes[currentNodeIndex],
						((javax.swing.text.AttributeSet) (bD)));
			else
				document.insertString(document.getLength(),
						sqlNodes[currentNodeIndex],
						((javax.swing.text.AttributeSet) (colorAttrBlack)));
		} catch (Exception _ex) {
		}
		lineIndent[lineNumber] = curIndentLen;
		if (U > 1)
			U = 1;
		indentCharCount = indentCharCount - S * indentLen;
		if (indentCharCount < 0)
			indentCharCount = 0;
		indentCharCount = indentCharCount + U * indentLen;
	}

	protected final void keyWordAppear() {
		isKeyWord = true;
		keyWordCount++;
		keyWords[keyWordCount] = sqlNodes[currentNodeIndex];
		if (H[openBracketCount].equalsIgnoreCase(" "))
			H[openBracketCount] = sqlNodes[currentNodeIndex];
		G[openBracketCount] = sqlNodes[currentNodeIndex];
	}

	protected final void bracketOpenInit() {
		H[openBracketCount] = " ";
		G[openBracketCount] = " ";
		aK[openBracketCount] = false;
		aL[openBracketCount] = false;
		aM[openBracketCount] = false;
		aJ[openBracketCount] = false;
	}

	protected final String preWord(int i1) {
		x = i1 - 1;
		if (x >= 0)
			return sqlNodes[x];
		return " ";
	}

	protected final String preNonSpaceWord(int i1) {
		for (x = i1 - 1; x >= 0; x--)
			if (!sqlNodes[x].equals(" ") && !sqlNodes[x].equals("\t")
					&& !sqlNodes[x].equals("\f") && !sqlNodes[x].equals("\r")
					&& !sqlNodes[x].equals("\b") && !sqlNodes[x].equals("\n"))
				return sqlNodes[x];
		return " ";
	}

	protected final String preNonBracketWord(int i1) {
		for (x = i1 - 1; x >= 0; x--)
			if (!sqlNodes[x].equals("(") && !sqlNodes[x].equals(" ")
					&& !sqlNodes[x].equals("\t") && !sqlNodes[x].equals("\f")
					&& !sqlNodes[x].equals("\r") && !sqlNodes[x].equals("\b")
					&& !sqlNodes[x].equals("\n"))
				return sqlNodes[x];
		return " ";
	}

	protected final String nextWord(int i1) {
		x = i1 + 1;
		if (x <= sqlNodeCount)
			return sqlNodes[x];
		return "";
	}

	protected final String f(int i1) {
		nextNonSpaceWord(currentNodeIndex);
		return nextNonSpaceWord(x);
	}

	protected final String nextNonSpaceWord(int i1) {
		for (x = i1 + 1; x < sqlNodeCount; x++)
			if (!sqlNodes[x].equals(" ") && !sqlNodes[x].equals("\t")
					&& !sqlNodes[x].equals("\f") && !sqlNodes[x].equals("\r")
					&& !sqlNodes[x].equals("\b") && !sqlNodes[x].equals("\n"))
				return sqlNodes[x];
		return "";
	}

	protected final String nextNonBracketWord(int i1) {
		for (x = i1 + 1; x < sqlNodeCount; x++)
			if (!sqlNodes[x].equals(" ") && !sqlNodes[x].equals("(")
					&& !sqlNodes[x].equals("\t") && !sqlNodes[x].equals("\f")
					&& !sqlNodes[x].equals("\r") && !sqlNodes[x].equals("\b")
					&& !sqlNodes[x].equals("\n"))
				return sqlNodes[x];
		return "";
	}

	protected final void bracketStart() {
		if (openBracketCount < MAXBRACKETCOUNT)
			openBracketCount = openBracketCount + 1;
		bracketOpenInit();
		if (!sqlInLngStarted
				&& isBreakMainWord(nextNonBracketWord(currentNodeIndex), x)) {
			sqlInLngStarted = true;
			w = openBracketCount;
		}
		if (sqlInLngStarted) {
			if (nextNonBracketWord(currentNodeIndex).equalsIgnoreCase("Select")) {
				newLineCount = 1;
				aK[openBracketCount] = true;
				aL[openBracketCount] = true;
				U = 1;
				am = false;
				aO = true;
				sqlInLngStarted = true;
			}
			if (nextNonBracketWord(currentNodeIndex).equalsIgnoreCase("case")) {
				newLineCount = 1;
				aK[openBracketCount] = true;
				aP = true;
				U = 1;
				am = false;
			}
			if (preWordNonBracket.equalsIgnoreCase("iif")) {
				newLineCount = 1;
				P = 0;
				aK[openBracketCount] = true;
				U = 1;
				aP = true;
				aM[openBracketCount] = true;
				aJ[openBracketCount] = false;
			}
			if (preWordNonBracket.equalsIgnoreCase("where")
					|| preWordNonBracket.equalsIgnoreCase("having")
					|| preWordNonBracket.equalsIgnoreCase("and")
					|| preWordNonBracket.equalsIgnoreCase("not")
					|| preWordNonBracket.equalsIgnoreCase("or")
					|| preWordNonBracket.equalsIgnoreCase("when")
					|| preWordNonBracket.equalsIgnoreCase("on")) {
				newLineCount = 1;
				P = 1;
				aK[openBracketCount] = true;
				U = 1;
				aP = true;
				aM[openBracketCount] = true;
				aJ[openBracketCount] = true;
			}
			if (preWordNonBracket.equalsIgnoreCase("join")) {
				newLineCount = 1;
				aK[openBracketCount] = true;
				aL[openBracketCount] = true;
				U = 1;
				aO = true;
			}
			if (H[openBracketCount - 1].equalsIgnoreCase("insert")
					|| H[openBracketCount - 1].equalsIgnoreCase("create")
					|| H[openBracketCount - 1].equalsIgnoreCase("declare")
					|| H[openBracketCount - 1].equalsIgnoreCase("update")
					&& preWordNonBracket.equalsIgnoreCase("set")) {
				aK[openBracketCount] = true;
				aJ[openBracketCount] = true;
				newLineCount = 1;
				P = 1;
				U = 1;
			}
			if (openBracketCount >= 1
					&& (G[openBracketCount - 1].equalsIgnoreCase("with") || G[openBracketCount - 1]
							.equalsIgnoreCase("values"))) {
				aJ[openBracketCount] = true;
				newLineCount = 1;
				if (isBreakAfterComma || isBreakBeforeComma) {
					P = 1;
					U = 1;
					aK[openBracketCount] = true;
					return;
				}
				P = 0;
				U = 0;
				aK[openBracketCount] = false;
			}
		}
	}

	protected final void bracketEnd() {
		if (sqlInLngStarted) {
			if (aL[openBracketCount])
				aO = true;
			if (aM[openBracketCount])
				aP = true;
			if (aK[openBracketCount]) {
				newLineCount = 1;
				P = 1;
				if (H[openBracketCount].equalsIgnoreCase("insert")
						|| H[openBracketCount].equalsIgnoreCase("select")
						|| H[openBracketCount].equalsIgnoreCase("update")
						|| H[openBracketCount].equalsIgnoreCase("create")
						|| H[openBracketCount].equalsIgnoreCase("delete")
						|| H[openBracketCount].equalsIgnoreCase("declare"))
					temp = 2;
				else
					temp = 1;
			}
		}
		if (sqlInLngStarted && openBracketCount == w)
			sqlInLngStarted = false;
		openBracketCount = openBracketCount - 1;
		if (openBracketCount < 0)
			openBracketCount = 0;
	}

	protected final boolean isBreakMainWord(String node, int index1) {
		int index = index1;
		boolean flag;
		if (node.equalsIgnoreCase("select") && !f(index).equalsIgnoreCase("=")
				|| node.equalsIgnoreCase("update")
				|| node.equalsIgnoreCase("create")
				&& nextNonSpaceWord(index).equalsIgnoreCase("Table")
				|| node.equalsIgnoreCase("create")
				&& nextNonSpaceWord(index).equalsIgnoreCase("View")
				|| node.equalsIgnoreCase("create")
				&& nextNonSpaceWord(index).equalsIgnoreCase("Index")
				|| node.equalsIgnoreCase("With")
				&& !preNonSpaceWord(index).equalsIgnoreCase("Start")
				&& !preNonSpaceWord(index).equalsIgnoreCase("End")
				|| node.equalsIgnoreCase("insert")
				|| node.equalsIgnoreCase("drop")
				|| node.equalsIgnoreCase("delete"))
			flag = true;
		else
			flag = false;
		return flag;
	}

	private static boolean isFirstInSec(String s1, String s2) {
		return s2.indexOf(s1) > -1;
	}

	private void insertBlankNode() {
		sqlNodeCount++;
		int i1;
		for (i1 = sqlNodeCount; i1 > currentNodeIndex + 1; i1--)
			sqlNodes[i1] = sqlNodes[i1 - 1];
		sqlNodes[i1] = " ";
	}

	private void removeDocLastChar() {
		try {
			document.remove(document.getLength() - 1, 1);
		} catch (Exception _ex) {
		}
		curIndentLen--;
	}

	private void insertWhiteSpace(int i1) {
		for (int j1 = 1; j1 <= i1; j1++)
			try {
				document.insertString(document.getLength(), " ",
						((javax.swing.text.AttributeSet) (colorAttrBlack)));
			} catch (Exception _ex) {
			}
		curIndentLen = curIndentLen + i1;
	}

	int w = 0;
	int x = 0;
	int U = 0;
	int P = 0;
	int Q = 0;
	int temp = 0;
	int S = 0;
	boolean z = false;
	boolean A = false;
	boolean B = false;
	boolean am = true;
	boolean an = false;
	boolean ao = false;
	boolean aO = false;
	boolean aP = false;
	boolean[] aJ = null;
	boolean[] aK = null;
	boolean[] aL = null;
	boolean[] aM = null;
	String L = null;
	String[] G = null;
	String[] H = null;

	int keyWordCount = 0;
	int indentCharCount = 0;
	int newLineCount = 0;
	int breakMainWordCount = 0;
	int maxLineLen = 0;
	int curIndentLen = 0;
	int lineNumber = 0;
	int sqlNodeCount = 0;
	int indentLen = 0;
	int currentNodeIndex = 0;
	int openBracketCount = 0;
	int[] lineIndent = null;
	boolean isKeyWord = false;
	boolean isQuoteStart = false;
	boolean isQuoteEnd = false;
	boolean commentEnd = false;
	boolean blockCommentEnd = false;
	boolean lineCommentEnd = false;
	boolean commentStart = false;
	boolean lineCommentStart = false;
	boolean blockCommentStart = false;
	boolean sqlInLngStarted = false;
	boolean isKeyToUpper = false;
	boolean isAllToUpper = false;
	boolean isToLower = false;
	boolean isShowLevel = false;
	boolean isEmptyLine = false;
	boolean isBreakBeforeComma = false;
	boolean isBreakAfterComma = false;
	boolean isBreakAfterAnd = false;
	boolean isBreakBeforeAnd = false;
	boolean isUseTab = false;
	boolean isFormatToOneLine = false;
	boolean isBreakSubSelect = false;
	boolean isBreakBracket = false;
	boolean outUseColor = false;
	boolean isCaseBreak = false;
	boolean outUseLineNumber = false;
	boolean isBreakAfterSelect = false;
	boolean breakMainWord = false;
	String preWordNonBracket = null;
	String preWordNonSpace = null;
	String quoteStrings = null;
	String sqlStart = null;
	String linePrefix = null;
	String newLineEnd = null;
	String lineEnd = null;
	String quoteChar = "'";
	String outType = null;
	String inputType = null;
	String[] keyWords = null;
	String[] sqlNodes = null;
	Document document = null;
	SimpleAttributeSet colorAttrPink = null;
	SimpleAttributeSet colorAttrDarkGray = null;
	SimpleAttributeSet colorAttrOther = null;
	SimpleAttributeSet colorAttrRed = null;
	SimpleAttributeSet colorAttrBlue = null;
	SimpleAttributeSet colorAttrBlack = null;
	SimpleAttributeSet colorAttrGreen = null;
	SimpleAttributeSet colorAttrGray = null;
	SimpleAttributeSet boldFont = null;
	SimpleAttributeSet notBoldFont = null;
	static Color[] COLORS = { Color.ORANGE, Color.BLUE, Color.MAGENTA,
			Color.BLACK, Color.GREEN, Color.RED, Color.CYAN, Color.PINK };
	static final int colorCount = COLORS.length;
	int MAXBRACKETCOUNT = 0;
	int maxWordCount = 100000;
}
