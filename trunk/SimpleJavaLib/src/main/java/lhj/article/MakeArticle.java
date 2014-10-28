package lhj.article;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class MakeArticle {
	static String title;
	static String author;
	static List<String> chapters = new ArrayList<String>();
	static List<String> chapterTitles = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
//		Map<String, String> p = new HashMap<String, String>();
//		p.put("title", "西游记");
//		p.put("author", "吴承恩");
//		p.put("left", "index.html");
//		p.put("index", "index.html");
//		p.put("right", "002.html");
//		p.put("chapterTitle", "第一回 灵根育孕源流出 心性修持大道生");
//		p.put("content", "诗曰：\n混沌未分天地乱，茫茫渺渺无人见。\n自从盘古破鸿蒙，开辟从兹清浊辨。");
		Template t = Velocity.getTemplate("src/main/resources/xiaoshuo/chapter-template.html", "UTF-8");
//		VelocityContext c = new VelocityContext(p);
//		
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xiyouji.html"), "UTF-8"));
//		t.merge(c, out);
//		out.close();
		
		readArticle("./0103 西游记.txt");
		String to = "./西游记";
		
		String content = "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\" />\n"
				+ "<title>" + title + "--(" + author + ")</title>\n"
				+ "<link rel=\"stylesheet\" href=\"style.css\" />\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "	<article>\n"
				+ "		<header>\n"
				+ "			<h2>" + title + "<span class=\"author\">" + author + "</span></h2>\n"
				+ "		</header>\n"
				+ "		<hr />\n"
				+ "		<div class=\"indexes\">\n"
				+ "			<table class=\"indextbl\">\n"
				+ "				<tbody>\n";
		
		new File(to).mkdirs();
		int i = 0, size = chapterTitles.size();
		for (; i < size; i++) {
			if (i % 4 == 0) {
				if (i > 0) {
					content += "</tr>";
				}
				content += "<tr>";
			}
			content += "<td><a href=\"" + padd(i+1) + ".html\">" + chapterTitles.get(i) + "</a></td>";
			makArticle(t, to + "/" + padd(i+1) + ".html",
					chapterTitles.get(i),
					chapters.get(i),
					(i == 0 ? "index.html" : padd(i) + ".html"),
					(i == size - 1 ? "index.html" : padd(i+2) + ".html"));
		}
		if (size % 4 != 0) {
			for (i = 0; i < size % 4; i++) {
				System.out.println("<td>&nbsp;</td>");
			}
		}
		content += "</tr>";

		content += "				</tbody>\n"
				+ "			</table>\n"
				+ "		</div>\n"
				+ "	</article>\n"
				+ "</body>\n"
				+ "</html>\n";
	}
	private static String padd(int i) {
		if (i < 10) {
			return "000000" + i;
		} else if (i < 100) {
			return "00000" + i;
		} else if (i < 1000) {
			return "0000" + i;
		} else if (i < 10000) {
			return "000" + i;
		} else if (i < 100000) {
			return "00" + i;
		} else if (i < 1000000) {
			return "0" + i;
		} else {
			return "" +i;
		}
	}
	private static void makArticle(Template t, String to, String chapterTitle, String content, String left, String right) throws Exception {
		Map<String, String> p = new HashMap<String, String>();
		p.put("title", title);
		p.put("author", author);
		p.put("left", left);
		p.put("index", "index.html");
		p.put("right", right);
		p.put("chapterTitle", chapterTitle);
		p.put("content", content);
		VelocityContext c = new VelocityContext(p);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to), "UTF-8"));
		t.merge(c, out);
		out.close();
	}
	
	private static void readArticle(String from) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(from), "GBK"));
		title = in.readLine();
		author = in.readLine();
		Pattern p = Pattern.compile("^第.*回　.*$");
		String line = null;
		boolean start = false;
		StringBuffer chapter = new StringBuffer(2048);
		while ((line = in.readLine()) != null) {
			if (p.matcher(line).matches()) {
				chapterTitles.add(line);
				if (!start) {
					start = true;
				} else {
					chapters.add(chapter.toString());
					chapter.delete(0, chapter.length());
				}
			} else {
				chapter.append(line).append("\n");
			}
		}
		chapters.add(chapter.toString());
		in.close();
	}
	
	static class Chapter {
		String title;
		String content;
		public Chapter(String title) {
			this.title = title;
		}
	}

}
