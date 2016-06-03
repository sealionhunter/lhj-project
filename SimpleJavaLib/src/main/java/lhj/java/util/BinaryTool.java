/*
 * Created on 2005-9-21
 * 
 */
package lhj.java.util;

import java.io.UnsupportedEncodingException;

import lhj.java.exception.HexException;

/**
 * Binary tool
 * 
 * @author hjliang
 * 
 */
public class BinaryTool {
	/**
	 * Comment for <code>BINARY_CHAR</code> Binary chars as '0', '1'
	 */
	public static final char[] BINARY_CHAR = { '0', '1' };

	/**
	 * Byte to binary String
	 * 
	 * @param b
	 *            byte
	 * @return binary string
	 */
	public static char[] byteToBinChar(byte b) {
		char[] retChar = new char[8];
		for (int i = 7; i >= 0; i--, b >>= 1) {
			retChar[i] = BINARY_CHAR[b & 0x01];
		}
		return retChar;
	}

	/**
	 * Bytes to binary String with space each 8 bytes
	 * 
	 * @param bs
	 *            byte[]
	 * @return binary string
	 */

	public static char[] byteToBinChar(byte[] b) {
		checkNull(b);
		char[] retChar = new char[b.length << 3];
		for (int i = 0; i < b.length; i++) {
			System.arraycopy(byteToBinChar(b[i]), 0, retChar, i << 3, 8);
		}
		return retChar;
	}

	/**
	 * Bytes to binary
	 * 
	 * @param bs
	 *            byte[]
	 * @return binary string
	 */
	public static char[] byteToBinCharWithSP(byte[] b) {
		checkNull(b);
		char[] retChar = new char[(b.length << 3) + b.length - 1];
		for (int i = 0; i < b.length; i++) {
			System.arraycopy(byteToBinChar(b[i]), 0, retChar, (i << 3) + i, 8);
			if (i < b.length - 1) {
				retChar[(i << 3) + i + 8] = ' ';
			}
		}
		return retChar;
	}

	/**
	 * Char to binary String
	 * 
	 * @param b
	 *            byte
	 * @return binary string
	 */
	public static char[] charToBinChar(char c) {
		char[] retChar = new char[16];
		byte high = (byte) (c >> 8);
		byte low = (byte) c;
		System.arraycopy(byteToBinChar(high), 0, retChar, 0, 8);
		System.arraycopy(byteToBinChar(low), 0, retChar, 8, 8);
		return retChar;
	}

	/**
	 * Char to binary String with space each 8 bytes
	 * 
	 * @param b
	 *            byte
	 * @return binary string
	 */
	public static char[] charToBinCharWithSP(char c) {
		char[] retChar = new char[17];
		byte high = (byte) (c >> 8);
		byte low = (byte) c;
		System.arraycopy(byteToBinChar(high), 0, retChar, 0, 8);
		retChar[8] = ' ';
		System.arraycopy(byteToBinChar(low), 0, retChar, 9, 8);
		return retChar;
	}

	public static char[] charToBinChar(char[] c) {
		checkNull(c);
		char[] retChar = new char[c.length << 4];
		for (int i = 0; i < c.length; i++) {
			System.arraycopy(charToBinChar(c[i]), 0, retChar, i << 4, 16);
		}
		return retChar;
	}

	public static char[] charToBinCharWithSP(char[] c) {
		checkNull(c);
		char[] retChar = new char[(c.length << 4) + (c.length << 1) - 1];
		for (int i = 0; i < c.length; i++) {
			System.arraycopy(charToBinCharWithSP(c[i]), 0, retChar, (i << 4)
					+ (i << 1), 17);
			if (i < c.length - 1) {
				retChar[(i << 4) + (i << 1) + 17] = ' ';
			}
		}
		return retChar;
	}

	/**
	 * Int to binary String
	 * 
	 * @param b
	 *            byte
	 * @return binary string
	 */
	public static char[] intToBinChar(int i) {
		char[] retChar = new char[32];
		char low = (char) i;
		char high = (char) (i >> 16);
		System.arraycopy(charToBinChar(high), 0, retChar, 0, 16);
		System.arraycopy(charToBinChar(low), 0, retChar, 16, 16);
		return retChar;
	}

	/**
	 * Int to binary String with space each 8 bytes
	 * 
	 * @param b
	 *            byte
	 * @return binary string
	 */
	public static char[] intToBinCharWithSP(int i) {
		char[] retChar = new char[35];
		char low = (char) i;
		char high = (char) (i >> 16);
		System.arraycopy(charToBinCharWithSP(high), 0, retChar, 0, 17);
		retChar[17] = ' ';
		System.arraycopy(charToBinCharWithSP(low), 0, retChar, 18, 17);
		return retChar;
	}

	public static char[] stringToBinChar(String s) {
		checkNull(s);
		return byteToBinChar(s.getBytes());
	}

	public static char[] stringToBinCharWithSP(String s) {
		checkNull(s);
		return byteToBinCharWithSP(s.getBytes());
	}

	public static char[] stringToBinChar(String s, String encoding) {
		checkNull(s);
		byte[] bytes = null;
		try {
			bytes = s.getBytes(encoding);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			bytes = s.getBytes();
		}
		return byteToBinChar(bytes);
	}

	public static char[] stringToBinCharWithSP(String s, String encoding) {
		checkNull(s);
		byte[] bytes = null;
		try {
			bytes = s.getBytes(encoding);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			bytes = s.getBytes();
		}
		return byteToBinCharWithSP(bytes);
	}

	/**
	 * Check if value is null.If so, throw exception
	 * 
	 * @param value
	 *            value to check
	 */
	public static void checkNull(Object value) {
		if (value == null) {
			throw new NullPointerException("Data shoud not be null!");
		}
	}

	public static void checkBinChar(char[] binChar) {
		checkNull(binChar);
		for (int i = 0; i < binChar.length; i++) {
			if (binChar[i] != '0' && binChar[i] != '1') {
				throw new HexException("Binary char overflow: '" + binChar[i]
						+ "'");
			}
		}
	}

	public static final byte binCharToByte(char[] binChar) {
		checkBinChar(binChar);
		if (binChar.length != 8) {
			throw new HexException("Binary char length isn't 8 for byte:"
					+ binChar.length);
		}
		int retI = 0;
		for (int i = 0; i < binChar.length; i++) {
			retI = (retI << 1) + (binChar[i] - '0');
		}
		return (byte) retI;

	}

	public static final byte[] byteBinCharToByte(char[] binChar) {
		checkBinChar(binChar);
		if (binChar.length % 8 != 0) {
			throw new HexException(
					"Binary char length should be times of 8 for bytes:"
							+ binChar.length);
		}
		byte[] retByte = new byte[binChar.length >> 3];
		for (int i = 0; i < retByte.length; i++) {
			char[] temp = new char[8];
			System.arraycopy(binChar, i << 3, temp, 0, 8);
			retByte[i] = binCharToByte(temp);
		}
		return retByte;
	}

	public static final char binCharToChar(char[] binChar) {
		checkBinChar(binChar);
		if (binChar.length != 16) {
			throw new HexException("Binary char length isn't 16 for char:"
					+ binChar.length);
		}
		int retI = 0;
		for (int i = 0; i < binChar.length; i++) {
			retI = (retI << 1) + (binChar[i] - '0');
		}
		return (char) retI;
	}

	public static final char[] charBinCharToChar(char[] binChar) {
		checkBinChar(binChar);
		if (binChar.length % 16 != 0) {
			throw new HexException(
					"Binary char length should be times of 16 for chars:"
							+ binChar.length);
		}
		char[] retByte = new char[binChar.length >> 4];
		for (int i = 0; i < retByte.length; i++) {
			char[] temp = new char[16];
			System.arraycopy(binChar, i << 4, temp, 0, 16);
			retByte[i] = binCharToChar(temp);
		}
		return retByte;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SP.println(BinaryTool.byteToBinChar((byte) 0x0ff));
		SP.println(BinaryTool.byteToBinChar(new byte[] { (byte) 0x0ff,
				(byte) 0x0ff }));
		SP.println(BinaryTool.byteToBinCharWithSP(new byte[] { (byte) 0x0ff,
				(byte) 0x0ff, (byte) 0x0ff, (byte) 0x0ff }));
		SP.println(BinaryTool.charToBinChar((char) 0x0ff));
		SP.println(BinaryTool.charToBinCharWithSP((char) 0x0ff));
		SP.println(BinaryTool.charToBinChar(new char[] { (char) 0x0ff,
				(char) 0x0ff }));
		SP.println(BinaryTool.charToBinCharWithSP(new char[] { (char) 0x0ff,
				(char) 0x0ff, (char) 0x0ff, (char) 0x0ff }));
		SP.println(BinaryTool.intToBinChar(0x0ff));
		SP.println(BinaryTool.intToBinCharWithSP(0x0ffffffff));
		SP.println(BinaryTool.stringToBinChar("this"));
		SP.println(BinaryTool.stringToBinCharWithSP("this"));
		SP.println(BinaryTool.stringToBinChar("this", "ISO-8859-1"));
		SP.println(BinaryTool.stringToBinCharWithSP("this", "ISO-8859-1"));
		char[] chs = BinaryTool.byteToBinChar((byte) 0x0ff);
		byte b = BinaryTool.binCharToByte(chs);
		SP.println(HexTool.byteToHexChars(b));
		chs = BinaryTool.charToBinChar((char) 0x0ff);
		char c = BinaryTool.binCharToChar(chs);
		SP.println(HexTool.charToHexChars(c));
		chs = BinaryTool.charToBinChar(new char[] { (char) 0x0ff, (char) 0x0ff,
				(char) 0x0ff, (char) 0x0ff });
		char[] cs = BinaryTool.charBinCharToChar(chs);
		SP.println(HexTool.charToHexCharsWithSP(cs));
	}

}
