package lhj.java.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Created on 2005-9-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author IBM USER
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		File file = new File("test");
		if (file.exists()) {
			boolean del = file.delete();
			file.mkdirs();
			System.out.println(del);
		} else {
			file.mkdirs();
		}
//		file = new File(file, "test.txt");
//		file.createNewFile();
		
		Map<String, String> test = new  LinkedHashMap<String, String>();
		test.put("1", "1");
		for (String t : test.values()) {
			System.out.println(t);
		}
		test.clear();
		test.put("2", "2");
		test.put("0", "0");
		for (String t : test.values()) {
			System.out.println(t);
		}
	}
	void test() throws Exception {
		
//		for (Charset charset :Charset.availableCharsets().values()) {
//			System.out.println(charset.name() + "     " + charset.displayName());
//		}
		Charset ch = Charset.forName("GB2312");
		System.out.println("GB2312");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("GB18030");
		System.out.println("GB18030");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}
		System.out.println();
		ch = Charset.forName("GBK");
		System.out.println("GBK");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}
		System.out.println();
		ch = Charset.forName("ISO-2022-CN");
		System.out.println("ISO-2022-CN");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("Big5");
		System.out.println("Big5");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("Big5-HKSCS");
		System.out.println("Big5-HKSCS");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("CP935");
		System.out.println("CP935");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("MS936");
		System.out.println("MS936");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("CP1383");
		System.out.println("CP1383");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}

		System.out.println();
		ch = Charset.forName("CP1381");
		System.out.println("CP1381");
		for (String al : ch.aliases()) {
			System.out.println(al);
		}
		
//		System.out.println(MimeUtility.decodeText(MimeUtility.encodeText("abc   팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
//		System.out.println(MimeUtility.decodeWord(MimeUtility.encodeWord("abc   팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
//		System.out.println(MimeUtility.decodeText(MimeUtility.encodeWord("abc   팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
		}

	public void print() {
		System.out.println("Hello, Java World!");
	}
}
