package lhj.java.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import lhj.java.util.HexTool;
import lhj.java.util.SP;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {
	private JTextArea jta = new JTextArea(20, 20);
	private JTextArea jtb = new JTextArea(20, 20);
	private JButton jbtn = new JButton("Action");

	public TestFrame() {
		super("Test Frame");
		JPanel panel = new JPanel();
		jbtn.addActionListener(this);
		jta.setLineWrap(true);
		jtb.setLineWrap(true);
		panel.add(jta);
		panel.add(jbtn);
		panel.add(jtb);
		getContentPane().add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestFrame();
	}

	public void actionPerformed(ActionEvent e) {
		String text = jta.getText();
		try {
			byte[] gb = text.getBytes();
			SP.println(HexTool.byteToHexChars(gb));
			gb = text.getBytes("gb2312");
			SP.println(HexTool.byteToHexChars(gb));
			gb = text.getBytes("gbk");
			SP.println(HexTool.byteToHexChars(gb));
			gb = text.getBytes("UTF-8");
			SP.println(HexTool.byteToHexChars(gb));
			gb = text.getBytes("UNICODE");
			SP.println(HexTool.byteToHexChars(gb));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		char[] hexChar = HexTool.stringToHexChars(text);
		jtb.setText(text + '\n' + String.valueOf(hexChar));
	}

}
