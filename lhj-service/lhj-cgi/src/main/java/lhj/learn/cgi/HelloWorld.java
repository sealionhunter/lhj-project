package lhj.learn.cgi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;

public class HelloWorld {
	public static PrintStream sysOut = System.out;
	public static Map<String, String> env = System.getenv();
	public static void main(String[] args) {
		try {
			env();
		} catch (Exception e) {
			sysOut.println("Content-Type: text/plain \r\n\r\n");
			e.printStackTrace(System.out);
		}
	}
	
	protected static void env() throws Exception {
		sysOut.print("Content-Type: text/plain \r\n\r\n");
		for (Entry<String, String> e: env.entrySet()) {
			sysOut.println(e.getKey() + ": " + e.getValue() + "\r\n");
		}
	}
	
	protected static void env2() throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter("errorsss.log"));
		out.write("Content-Type: text/plain \r\n\r\n");
		for (Entry<String, String> e: env.entrySet()) {
			out.write(e.getKey() + ": " + e.getValue() + "\r\n");
		}
		out.flush();
		out.close();
	}
}
