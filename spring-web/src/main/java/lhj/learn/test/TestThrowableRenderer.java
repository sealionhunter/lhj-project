package lhj.learn.test;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.log4j.spi.ThrowableRenderer;

public class TestThrowableRenderer implements ThrowableRenderer {

	private static final String IP_REGX = "(1\\d\\d|2[0-4]\\d|25[0-5]|\\d{1,2})\\.(1\\d\\d|2[0-4]\\d|25[0-5]|\\d{1,2})\\.(1\\d\\d|2[0-4]\\d|25[0-5]|\\d{1,2})\\.(1\\d\\d|2[0-4]\\d|25[0-5]|\\d{1,2})";

	private static final String IP_REPLACEREGX = "<<<$1\\.$2\\.$3\\.$4>>>";

	public static void main(String[] args) {
		String s = "192.168.200.248  ==== 192.168.200.53 ";
		System.out.println(s);
		System.out.println(s.replaceAll(IP_REGX, IP_REPLACEREGX));
	}

	@Override
	public String[] doRender(Throwable t) {
		return render(t);
	}

	/**
	 * Render throwable using Throwable.printStackTrace.
	 * 
	 * @param throwable
	 *            throwable, may not be null.
	 * @return string representation.
	 */
	public static String[] render(final Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			throwable.printStackTrace(pw);
		} catch (RuntimeException ex) {
		}
		pw.flush();
		LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
		ArrayList<String> lines = new ArrayList<String>();
		try {
			String line = reader.readLine();
			while (line != null) {
				lines.add(checkLine(line));
				line = reader.readLine();
			}
		} catch (IOException ex) {
			if (ex instanceof InterruptedIOException) {
				Thread.currentThread().interrupt();
			}
			lines.add(ex.toString());
		}
		String[] tempRep = new String[lines.size()];
		lines.toArray(tempRep);
		return tempRep;
	}

	private static String checkLine(String line) {
		return line.replaceAll(IP_REGX, IP_REPLACEREGX);
	}
}
