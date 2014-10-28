package lhj.java.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpConnections {

	static String title;
	static String author;
	static List<String[]> pages = new ArrayList<String[]>();
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		readFromIndex("F:/Sealion/books/极品女仙/index.html");
		for (int i = 0, size = pages.size(); i < size; i++) {
			String[] file = pages.get(i);
			Get.get("http://www.qmshu.com/html/7/7229/"+file[0], "F:/Sealion/books/极品女仙/"+file[0]);
			exec2("F:/Sealion/books/极品女仙/"+file[0], "F:/Sealion/books/极品女仙1/"+file[0], file[1], i == 0 ? null : pages.get(i - 1)[0], i == size - 1 ? null : pages.get(i + 1)[0]);
		}
//		for (String[] file : pages) {
//		}
	}
	
	static class Get extends Thread {

	public static void get(String url, String dest) throws Exception {
//		System.out.println(url);
		URL u = new URL(url);
		BufferedInputStream in = new BufferedInputStream(u.openStream());
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));
		byte[] buffer = new byte[8192];
		int len = 0;
		while ((len = in.read(buffer, 0, 8192)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();

	}
	}

	private static void exec2(String from, String to, String pageTitle, String left, String right) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(from), "GBK"));
		StringBuffer content = new StringBuffer(1024);
		String line = null;
		while ((line = in.readLine()) != null) {
			if (line.contains("<div id=\"content\">")) {
				content.append(line);
				break;
			}
		}
		in.close();
		File f = new File(to);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
		out.write("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\" />"
				+ "<title>" + title + "</title>"
				+ "<link rel=\"stylesheet\" href=\"../page.css\" />"
				+ "</head>"
				+ "<body leftmargin='2' topmargin='0' marginwidth='0' marginheight='0'>"
				+ "<div align='center' class='pagefeet'><a href='http://www.qmshu.com/'>启蒙书网</a>-&gt;<a href='http://www.qmshu.com/qmshu/16407.htm'>" + title + "</a></div>"
				+ "<div align='center' class='pagefeet'>"
				+ (left == null ? "" : "<a href='" + left + "'>上一页</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				+ "<a href='index.html'>返回目录</a>"
				+ (right == null ? "" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='" + right + "'>下一页</a>")
				+ "</div>"
				+ "<hr />"
				+ "<div id='title'>" + pageTitle + "</div>"
				+ content.toString()
				+ "<hr />"
				+ "<div align='center' class='pagefeet'>"
				+ (left == null ? "" : "<a href='" + left + "'>上一页</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				+ "<ahref='index.html'>返回目录</a>"
				+ (right == null ? "" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='" + right + "'>下一页</a>")
				+ "</div>"
				+ "</body>"
				+ "</html>");
		out.close();
	}
	
	private static void readFromIndex(String indexFile) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(indexFile), "GBK"));
		String line = null;
		Pattern p = Pattern.compile("^.*<a href=\"(.*)\">(.*)</a>$");
		boolean start = false;
		while ((line = in.readLine()) != null) {
			if (line.startsWith("<div id=\"title\">")) {
				title = line.substring("<div id=\"title\">".length(), line.length() - "</div>".length());
				continue;
			}
			if (line.startsWith("<div id=\"info\">")) {
				author = line.substring("<div id=\"info\">".length(), line.length() - "</div>".length());
				continue;
			}
			if (line.contains("acss")) {
				start = true;
				continue;
			}
			if (start) {
				Matcher m = p.matcher(line);
				if (m.matches()) {
					pages.add(new String[]{m.group(1), m.group(2)});
				}
			}
		}
		in.close();
		
//		System.out.println(title);
//		System.out.println(author);
//		for (String[] page: pages) {
//			System.out.println("{\"" + page[0] + "\", \"" + page[1] + "\"}");
//		}
	}
}
