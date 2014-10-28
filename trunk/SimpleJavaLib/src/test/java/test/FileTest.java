package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS"); 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("F:\\Work\\LM\\FromSDCC\\20130711\\33276\\33276\\7.10_JobLogs\\20130707\\JLTC100507_707246.csv");
		out2(f);
	}

	
	public static void out(File f) {
		if (f.isFile()) {
			System.out.println(f.getAbsolutePath() + "\t" + f.length() + "\t" + (f.length() % 16384)+ "\t" + sdf.format(new Date(f.lastModified())) + "\t");
		} else {
			File[] s = f.listFiles(new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					if (dir.isDirectory()) {
						return true;
					}
					return name.endsWith(".csv");
				}
			});
			
			if (s != null) {
				for (File sf : s) {
					out(sf);
				}
			}
		}
	}
	

	public static void out2(File f) {
		if (f.isFile()) {
			System.out.print(f.getAbsolutePath() + "\t" + f.length() + "\t" + (f.length() % 16384)+ "\t" + sdf.format(new Date(f.lastModified())) + "\t");
			try {
//				if ((f.length() % 16384) == 0) {
					BufferedReader br = new BufferedReader(new FileReader(f)) ;
					String err = "OK";
					String line = br.readLine();
					while ((line = br.readLine()) != null) {
						if (",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,".equals(line)) {
							err = "ERR1";
							break;
						}
						if (!(line.startsWith("SGRMYKL") || line.startsWith("\"SGRMYKL"))) {
							err = "ERR2";
							break;
						}
//						System.out.println(line);
					}
					br.close();
					System.out.print("\t" + err);
//				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		} else {
			File[] s = f.listFiles(new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					if (dir.isDirectory()) {
						return true;
					}
					return name.endsWith(".csv");
				}
			});
			
			if (s != null) {
				for (File sf : s) {
					out2(sf);
				}
			}
		}
	}
}
