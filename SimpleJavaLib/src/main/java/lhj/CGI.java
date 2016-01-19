package lhj;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class CGI {
	
	public static void main(String[] args) {
		PrintStream out = System.out;
		out.println(UUID.randomUUID().toString().length());
		out.print("Content-Type: text/plain \r\n\r\n");
		out.print("\r\n\r\n");
		out.print("hello");
		
		Map<String,String> env = System.getenv();
		String contents = CGIUtil.contents();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("errorsss.log"));
			bw.write("Content-Type: text/html \r\n\r\n");
			bw.write("<!DOCTYPE HTML><html><body>");
			bw.write("<h2>System Environments</h2>");
			for (Entry<String, String> entry : env.entrySet()) {
				bw.write(entry.getKey() + ":  " + entry.getValue() + "<br/>");
			}
			bw.write("contents: " + contents);
			bw.write("</body></html>");
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("Content-Type: text/html \r\n\r\n");
		out.print("<!DOCTYPE HTML><html><body>");
		out.print("<h2>System Environments</h2>");
		out.print("System Environments");
		for (Entry<String, String> entry : env.entrySet()) {
			out.print(entry.getKey() + ":  " + entry.getValue() + "<br/>");
		}
		out.print("contents: " + contents);
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
