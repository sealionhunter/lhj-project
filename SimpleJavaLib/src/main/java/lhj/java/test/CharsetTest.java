/*
 * Created on 2005-9-15
 *
 */
package lhj.java.test;

import java.nio.charset.Charset;

/**
 * @author Sealion Hunter
 * 
 */
public class CharsetTest {

	public static void main(String[] args) throws Exception {
		test();
	}

	private static void test() throws Exception {
		String[] CH_NAMES = { "GB2312", "GB18030", "GBK", "ISO-2022-CN", "Big5", "Big5-HKSCS", "CP935", "MS936",
				"CP1383", "CP1381" };
		Charset ch;

		for (String cname : CH_NAMES) {
			ch = Charset.forName(cname);
			System.out.println(cname);
			for (String al : ch.aliases()) {
				System.out.println(al);
			}
			System.out.println();
		}
		for (Charset charset : Charset.availableCharsets().values()) {
			System.out.println(charset.name() + " " + charset.displayName());
		}

		// System.out.println(MimeUtility.decodeText(MimeUtility.encodeText("abc
		// 팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
		// System.out.println(MimeUtility.decodeWord(MimeUtility.encodeWord("abc
		// 팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
		// System.out.println(MimeUtility.decodeText(MimeUtility.encodeWord("abc
		// 팩스 통신 관리 리포트 작성创建传真通信管理报告建立傳真通信管理報表.txt", "UTF-8", "B")));
	}

	public void print() {
		System.out.println("Hello, Java World!");
	}
}
