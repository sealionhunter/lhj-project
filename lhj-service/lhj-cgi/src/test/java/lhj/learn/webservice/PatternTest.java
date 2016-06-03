package lhj.learn.webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	static Pattern imageUrlPattern = Pattern
			.compile("background-image:\\s*url\\(\\.\\./(([^/]+/[^.]+)\\.([^)]+))\\);");
	static Pattern imageUrlPattern3 = Pattern
			.compile("-webkit-border-image:\\s*url\\(['|\"]*\\.\\./((.*)(\\.[png|gif|jpg]+))['|\"]*\\)");
	static Pattern imageUrlPattern2 = Pattern
			.compile("BX_TXX_1");

	public static void main(String[] args) throws Exception {
		trav(new File("F:/fx-xux2/xux/xux-js/target/custom/xux-css/xux-easg"));
	}
	
	public static void trav(File parent) throws Exception {
		if (parent.isDirectory()) {
			File[] sub = parent.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.isDirectory() || pathname.getName().endsWith(".css");
				}
			});
			
			if (sub != null) {
				for (File f: sub) {
					trav(f);
				}
			}
		} else if (parent.isFile()) {
			parseCss(parent);
		}
	}


	public static void parseCss(File cssSourceFile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(cssSourceFile));
		String line;
		while ((line = in.readLine()) != null) {
			Matcher matcher = imageUrlPattern.matcher(line);
			Matcher matcher2 = imageUrlPattern2.matcher(line);
//			Matcher matcher3 = imageUrlPattern3.matcher(line);
			if (matcher.find()/* && matcher3.find()*/) {
				System.out.println("found: " + cssSourceFile.getAbsolutePath() + "\n\t" + line);
			}
		}
		in.close();
	}
}
