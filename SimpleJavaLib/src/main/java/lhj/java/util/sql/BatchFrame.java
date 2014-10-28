package lhj.java.util.sql;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.MinimalHTMLWriter;

public class BatchFrame extends JFrame {
	private static final long serialVersionUID = 5206541781003284975L;

	SQLinForm sqlInForm = null;

	private JPanel pnlMain = null;

	public BatchFrame() {
		sqlInForm = new SQLinForm();
		init();
		sqlInForm.init();
		sqlInForm.start();
		pnlMain.add(sqlInForm);
		setSize(1000, 700);
	}

	private void init() {
		pnlMain = new JPanel();
		setDefaultCloseOperation(3);
		setTitle("SQLinForm v 2.5.7. Unlicensed Trial Version");
		pnlMain.setLayout(new GridLayout(1, 0));
		pnlMain.setMinimumSize(new Dimension(500, 500));
		getContentPane().add(pnlMain, "Center");
		pack();
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public final void run() {
				(new BatchFrame()).setVisible(true);
			}
		});
	}
}

// Unreferenced inner class SQLinForm_200/g
class SqlFormatWriter extends MinimalHTMLWriter {
	protected final void text(Element element) throws IOException,
			BadLocationException {
		String s;
		if ((s = getText(element)).length() > 0
				&& s.charAt(s.length() - 1) == '\n')
			s = s.substring(0, s.length() - 1);
		if (s.length() > 0) {
			String s1;
			s1 = (s1 = (s1 = (s1 = (s1 = s).replaceAll("&", "&amp;"))
					.replaceAll(" ", "&nbsp;")).replaceAll("<", "&lt;"))
					.replaceAll(">", "&gt;");
			write(s1);
		}
	}

	protected final void writeStartTag(String s) throws IOException {
		indent();
		write(s);
		incrIndent();
	}

	public SqlFormatWriter(Writer writer, StyledDocument styleddocument) {
		super(writer, styleddocument);
	}
}
