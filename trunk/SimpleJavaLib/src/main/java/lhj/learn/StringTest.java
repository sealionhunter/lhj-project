package lhj.learn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringTest {
	public native   synchronized  void test();
//	public abstract   static  void test1();
//	public abstract   native  void test2();
//	public abstract   synchronized  void test3();
    /** 正規表現 */
    public static final String REGULAR_EXPRESSION_ERROR = ".*[/\\\\\":|<>?*].*";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(String.format("%03d", 1223));
//		System.out.println(String.format("%03d", 12));
		System.out.println((int)'A');
		for (char i = 'A'; i <= 'D'; i++) {
			System.out.println(i + " " + (int) i);
		}
//		short s2 = Short.MAX_VALUE;
//		System.out.println(s2);
//		s2 += 1;
//		System.out.println(s2);
//		int i = 0;
//		long a = 1l;
//		byte b = 1;
//		String c = "";
////		switch (a) {
////		
////		}
//		switch (b) {
//		
//		}
////		switch (c) {
////		
////		}
//		System.out.println(String.format("%08x", 10000));
//		System.out.println(Long.valueOf("00002710", 16));
//		System.out.println(checkRegex("\\", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("/", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex(":", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("*", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("?", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("\"", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex(">", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("<", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("|", REGULAR_EXPRESSION_ERROR));
//		System.out.println(11111111);
//		// \/:*?"><|
//		// \/:*?"><|
//		System.out.println(checkRegex(",", REGULAR_EXPRESSION_ERROR));
//		System.out.println(checkRegex("a", REGULAR_EXPRESSION_ERROR));
	}

    /**
     * 正規表現をチェックします。
     * 
     * @param target
     *            チェック文字列
     * @param strRegex
     *            正規表現
     * @return マッチの場合、True<br>
     *         マッチしてない場合、False
     */
    public static boolean checkRegex(String target, String strRegex) {
        if (strRegex != null && strRegex.trim().length() > 0) {
            Pattern pattern = Pattern.compile(strRegex);
            Matcher matcher = pattern.matcher(target);
            return matcher.matches();
        }
        return false;
    }
}
