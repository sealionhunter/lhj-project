package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader fr = new BufferedReader(new FileReader("f:\\JLTC100507_707317.csv"));
		BufferedWriter fw = new BufferedWriter(new FileWriter("f:\\JLTC100507_707317.csv", true));
		String line = "";
		int index = 0;
		while ((line = fr.readLine()) != null) {
			fw.write("\n");
			fw.write(line);
			if (index++ > 500) {
				break;
			}
		}
		fw.write("\n");
		fr.close();
		fw.close();
	}

}
