package lhj.java.util;

public class StringUtil {
	public static String pad(String src, int nlen) {
		return pad(src, ' ', nlen, 0);
	}

	public static String leftPad(String src, char padChar, int nLen) {
		return pad(src, padChar, nLen, 1);
	}

	public static String rightPad(String src, char padChar, int nLen) {
		return pad(src, padChar, nLen, 0);
	}

	public static String pad(String src, char padChar, int nLen, int direction) {
		String strRet = "";
		int blen = src.getBytes().length;
		if (blen >= nLen) {
			return src;
		}
		int clen = src.length();
		for (int i = 0; i < clen; i++) {
			int tLen = (strRet + src.charAt(i)).getBytes().length;
			if (tLen > nLen) {
				break;
			}
			strRet = strRet + src.charAt(i);
		}
		blen = strRet.getBytes().length;
		String sb = "" + padChar;
		while (sb.toString().getBytes().length < nLen - blen) {
			sb += sb;
		}
		if (0 == direction) {
			strRet += maxLenString(sb, nLen - blen);
		} else {
			strRet = maxLenString(sb, nLen - blen) + strRet;
		}
		return strRet;
	}

	public static String maxLenString(String src, int maxLen) {
		int blen = src.getBytes().length;
		if (blen <= maxLen) {
			return src;
		}
		String strRet = "";
		int clen = src.length();
		for (int i = 0; i < clen; i++) {
			int tLen = (strRet + src.charAt(i)).getBytes().length;
			if (tLen > maxLen) {
				if (strRet.getBytes().length < maxLen) {
					strRet += ' ';
				}
				break;
			}
			strRet = strRet + src.charAt(i);
		}
		return strRet;
	}

	public static boolean isEmpty(String src) {
		return src == null || src.length() == 0;
	}
}
