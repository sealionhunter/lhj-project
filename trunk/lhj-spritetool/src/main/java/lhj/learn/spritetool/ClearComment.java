package lhj.learn.spritetool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClearComment {
	private static final String LINE_SEP = System.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			printHelp();
			return;
		}
		Pattern p = Pattern.compile("(.*)/\\*\\*### (.*) \\*/");
		Pattern p2 = Pattern.compile("(.*)///\\*(.*)");
		BufferedReader in = new BufferedReader(
				new FileReader(new File(args[0])));
		BufferedWriter output = new BufferedWriter(new FileWriter(new File(
				args[1])));
		String line = null;
		while ((line = in.readLine()) != null) {
			Matcher m = p.matcher(line);
			if (m.matches()) {
				output.write(m.group(1).trim());
			} else {
				m = p2.matcher(line);
				if (m.matches()) {
					output.write(m.group(1));
				} else {
					output.write(line);
				}
			}
			output.write(LINE_SEP);
		}
		in.close();
		output.close();
	}

	public static void printHelp() {
		String help = "usage:\n"
				+ "java lhj.learn.spritetool.ClearComment src-cssfile dest-cssfile\n"
				+ "\n"
				+ "    src-cssfile:           original css file\n"
				+ "    dest-cssfile:          destination css file\n"
				+ "sample: \n"
				+ "    java lhj.learn.spritetool.ClearComment ./old.css ./new.css\n";

		System.out.println(help);
	}

}
