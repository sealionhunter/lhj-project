/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;

import lhj.java.util.SP;

/**
 * @author Sealion Hunter
 *
 */
public class FindAndReplace {

	public static final String F_SEPERATOR = System.getProperty("file.separator");
	private static final String L_SEPERATOR = System.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		find("./src/main/java/lhj/java/util/StringUtil.java", "maxLenString");
		// replace("./src/main/java/org/jr/CharSplitProperty.java",
		// "./src/main/java/org/jr/CharSplitProperty.java",
		// "numbers",
		// "lineNumbers");
	}

	public static void find(String file, String what) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = null;
		int lineNum = 0;
		int count = 0;
		while ((line = in.readLine()) != null) {
			lineNum++;
			if (line.contains(what)) {
				count++;
				SP.println(file + " (" + lineNum + ", " + line.indexOf(what) + ")\t" + line);
			}
		}
		in.close();
		SP.println(count + " occurrences hava been found in " + file);
	}

	public static void replaceDir(File from, File to, String what, String replaceTo, String include, String exclude)
			throws Exception {
		if (from.isFile()) {
			replace(from, to, what, replaceTo);
			return;
		}

		File[] files = from.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {

				return false;
			}
		});
		if (files != null) {

		}
	}

	public static void replace(String from, String to, String what, String replaceTo) throws Exception {
		replace(new File(from), new File(to), what, replaceTo);
	}

	public static void replace(File from, File to, String what, String replaceTo) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(from));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			if (line.contains(what)) {
				sb.append(line.replace(what, replaceTo));
			} else {
				sb.append(line);
			}
			sb.append(L_SEPERATOR);
		}
		in.close();

		BufferedWriter out = new BufferedWriter(new FileWriter(to));
		out.write(sb.toString());
		out.flush();
		out.close();
	}
}
