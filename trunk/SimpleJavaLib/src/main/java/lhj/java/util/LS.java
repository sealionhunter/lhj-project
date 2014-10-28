package lhj.java.util;

import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;

public class LS {

	private String currentPath;

	public LS() {
		currentPath = System.getProperty("user.dir");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 new LS().output(System.out);
	}

	public File[] filter(File[] src) {
		File[] dest = new File[src.length];
		int index = 0;
		for (int i = 0; i < src.length; i++) {
			File file = src[i];
			dest[index++] = file;
		}
		return dest;
	}

	public File[] listFile() {
		return new File(currentPath).listFiles();
	}

	public void output(PrintStream out) {
		File[] files = filter(new File(currentPath).listFiles());
		out.println(currentPath + " のディレクトリ");
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(file.lastModified());
			out.print(cal.getTime() + "  ");
			if (file.isDirectory()) {
				out.print(pad("<DIR>", 12, false));
			} else {
				out.print(pad(String.valueOf(file.length()), 10, true) + "  ");
			}
			out.println(file.getName());
		}
		out.println();
	}

	public static final String SPACE_8 = "        ";

	public String pad(String src, int length, boolean isRight) {
		if (src.length() > length) {
			return src.substring(0, length);
		}
		StringBuffer sb = new StringBuffer(SPACE_8);
		while (sb.length() < length - src.length()) {
			sb.append(SPACE_8);
		}
		if (isRight) {
			sb.append(src);
			return sb.substring(sb.length() - length);
		} else {
			sb = new StringBuffer(src).append(sb);
		}
		return sb.substring(0, length);
	}
}
