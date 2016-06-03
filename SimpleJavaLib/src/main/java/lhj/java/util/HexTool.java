/*
 * Created on 2005-9-7
 *
 */
package lhj.java.util;

import java.io.UnsupportedEncodingException;

import lhj.java.exception.HexException;

/**
 * Hex tool
 * 
 * @author hjliang
 * 
 */
public class HexTool {
	/**
	 * Comment for <code>HEX_CHAR</code> Hex chars as '0', 'A', ..., 'F'
	 */
	public static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Byte to hex String
	 * 
	 * @param b
	 *            byte
	 * @return hex string
	 */
	public static final char[] byteToHexChars(byte b) {
		char[] retChar = new char[2];
		int low = b & 0x0f;
		int high = (b >> 4) & 0x0f;
		retChar[0] = HEX_CHAR[high];
		retChar[1] = HEX_CHAR[low];
		return retChar;
	}

	/**
	 * Bytes to hex chars
	 * 
	 * @param data
	 *            bytes data
	 * @return hex chars
	 */
	public static final char[] byteToHexChars(byte[] data) {
		checkNull(data);
		final int l = data.length;
		char[] retChar = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			char temp[] = byteToHexChars(data[i]);
			retChar[j++] = temp[0];
			retChar[j++] = temp[1];
		}
		return retChar;
	}

	/**
	 * Bytes to hex chars
	 * 
	 * @param data
	 *            bytes data
	 * @return hex chars
	 */
	public static final char[] byteToHexCharsWithSP(byte[] data) {
		checkNull(data);
		char[] retChar = new char[data.length == 0 ? 0 : data.length * 3 - 1];
		for (int i = 0; i < data.length; i++) {
			char temp[] = byteToHexChars(data[i]);
			retChar[i * 3] = temp[0];
			retChar[i * 3 + 1] = temp[1];
			if (i < data.length - 1) {
				retChar[i * 3 + 2] = ' ';
			}
		}
		return retChar;
	}

	/**
	 * Char to hex chars
	 * 
	 * @param c
	 *            char
	 * @return hex chars
	 */
	public static final char[] charToHexChars(char c) {
		char[] retChar = new char[4];
		byte high = (byte) (c >> 8);
		byte low = (byte) c;
		System.arraycopy(byteToHexChars(high), 0, retChar, 0, 2);
		System.arraycopy(byteToHexChars(low), 0, retChar, 2, 2);
		return retChar;
	}

	/**
	 * Chars to hex chars
	 * 
	 * @param data
	 *            chars data
	 * @return hex chars
	 */
	public static final char[] charToHexChars(char[] data) {
		checkNull(data);
		char[] retChar = new char[data.length << 2];
		for (int i = 0; i < data.length; i++) {
			System.arraycopy(charToHexChars(data[i]), 0, retChar, i << 2, 4);
		}
		return retChar;
	}

	/**
	 * Chars to hex chars
	 * 
	 * @param data
	 *            chars data
	 * @return hex chars
	 */
	public static final char[] charToHexCharsWithSP(char[] data) {
		checkNull(data);
		char[] retChar = new char[data.length == 0 ? 0 : data.length * 5 - 1];
		for (int i = 0; i < data.length; i++) {
			System.arraycopy(charToHexChars(data[i]), 0, retChar, i * 5, 4);
			if (i < data.length - 1) {
				retChar[i * 5 + 4] = ' ';
			}
		}
		return retChar;
	}

	/**
	 * Int to hex chars
	 * 
	 * @param data
	 *            int
	 * @return hex chars
	 */
	public static final char[] intToHexChars(int data) {
		char[] retChar = new char[8];
		char low = (char) (data & 0xffff);
		char high = (char) ((data >> 16) & 0xffff);
		System.arraycopy(charToHexChars(high), 0, retChar, 0, 4);
		System.arraycopy(charToHexChars(low), 0, retChar, 4, 4);
		return retChar;
	}

	/**
	 * Int to hex chars
	 * 
	 * @param data
	 *            int
	 * @return hex chars
	 */
	public static final char[] intToHexCharsWithSP(int data) {
		int length = 2;
		int forLoop = data;
		while ((data = data >> 8) > 0) {
			length = length + 2;
		}
		char[] retChar = new char[length];
		byte low = (byte) (forLoop & 0xff);
		System.arraycopy(byteToHexChars(low), 0, retChar, length - 2, 2);
		for (int i = 1; i <= length / 2; i++, forLoop = forLoop >> 8) {
			low = (byte) (forLoop & 0xff);
			char[] lowChar = byteToHexChars(low);
			retChar[length - i * 2 + 1] = lowChar[1];
			retChar[length - i * 2] = lowChar[0];
		}
		/* remove the first zeros start */
		int index;
		for (index = 0; index < retChar.length - 1; index++) {
			if (retChar[index] != '0') {
				break;
			}
		}
		if (index > 0) {
			char[] temp = new char[retChar.length - index];
			System.arraycopy(retChar, index, temp, 0, temp.length);
			retChar = temp;
		}
		/* remove the first zeros end */
		return retChar;
	}

	/**
	 * String to hex chars
	 * 
	 * @param data
	 *            string data
	 * @return hex chars
	 */
	public static final char[] stringToHexChars(String data) {
		return charToHexChars(data.toCharArray());
	}

	/**
	 * String to hex chars with given encoding
	 * 
	 * @param data
	 *            String data
	 * @param encoding
	 *            given encoding
	 * @return hex chars
	 */
	public static final char[] stringToHexChars(String data, String encoding) {
		checkNull(data);
		byte[] bytes = null;
		try {
			bytes = data.getBytes(encoding);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			bytes = data.getBytes();
		}
		return byteToHexChars(bytes);
		// int length = bytes.length;
		// char[] retChar = new char[length == 0 ? 0 : length * 2 + (length / 2
		// - (length % 2 == 0 ? 1 : 0))];
		// int j = -3;
		// for (int i = 0; i < length; i++) {
		// if ((i + 1) % 2 == 0) {
		// j = j + 2;
		// } else {
		// j = j + 3;
		// }
		// System.arraycopy(byteToHexChars(bytes[i]), 0, retChar, j, 2);
		// if (i != 0 && (i + 1) % 2 != 0) {
		// retChar[j - 1] = ' ';
		// }
		// }
		// return retChar;
	}

	/**
	 * Check if value is null.If so, throw exception
	 * 
	 * @param value
	 *            value to check
	 */
	public static final void checkNull(Object value) {
		if (value == null) {
			throw new NullPointerException("Data shoud not be null!");
		}
	}

	/**
	 * Check if the aChar is a hex char. If so, return the upper case of the
	 * char, otherwise throw HexException
	 * 
	 * @param aChar
	 *            the char to check
	 * @return the upper case of the char
	 * 
	 */
	public static final char checkHexCharToUpper(char aChar) {
		char upper = Character.toUpperCase(aChar);
		if (upper >= '0' & upper <= '9' || upper >= 'A' & upper <= 'F') {
			return upper;
		}
		throw new HexException("Hex char overflow: '" + aChar + "'");
	}

	/**
	 * hex char to byte
	 * 
	 * @param hexChar
	 * @return
	 */
	public static final byte hexCharToByte(char hexChar) {
		char upper = checkHexCharToUpper(hexChar);
		if (upper >= '0' & upper <= '9') {
			return (byte) (upper - '0');
		} else {
			return (byte) (upper - 'A' + 10);
		}
	}

	/**
	 * hex chars to byte
	 * 
	 * @param hexChars
	 * @return
	 */
	public static final byte byteHexCharToByte(char[] hexChars) {
		checkNull(hexChars);
		if (hexChars.length > 2) {
			throw new HexException("Hex char overflow: "
					+ String.valueOf(hexChars));
		}
		if (hexChars.length == 0) {
			throw new HexException("Hex char shoud not be null!");
		}
		if (hexChars.length == 1) {
			return hexCharToByte(hexChars[0]);
		}
		int high = hexCharToByte(hexChars[0]) << 4;
		int low = hexCharToByte(hexChars[1]);
		return (byte) (high + low);
	}

	/**
	 * @param hexChars
	 * @return
	 */
	public static final byte[] byteHexCharsToBytes(char[] hexChars) {
		checkNull(hexChars);
		if (hexChars.length == 0) {
			throw new HexException("Hex char shoud not be null!");
		}
		int length = hexChars.length;
		byte[] retBytes = new byte[length >> 1 + (length >> 1 == 0 ? 0 : 1)];
		for (int i = 0; i < retBytes.length; i++) {
			char[] chs = new char[2];
			if ((i << 1 + 2) <= length) {
				System.arraycopy(hexChars, i << 1, chs, 0, 2);
			} else {
				chs[0] = '0';
				chs[1] = hexChars[length - 1];
			}
			retBytes[i] = byteHexCharToByte(chs);
		}
		return retBytes;
	}

	public static final char hexCharToChar(char[] hexChar) {
		checkNull(hexChar);
		if (hexChar.length != 2) {
			throw new HexException();
		}
		char highChar = checkHexCharToUpper(hexChar[0]);
		char lowChar = checkHexCharToUpper(hexChar[1]);
		int high;
		int low;
		if (highChar > 'A') {
			high = highChar - 'A';
		} else {
			high = highChar - '0';
		}
		if (lowChar > 'A') {
			low = lowChar - 'A';
		} else {
			low = lowChar - '0';
		}
		return (char) ((high << 4) + low);
	}

	public static final char charHexCharsToChar(char[] hexChar) {
		checkNull(hexChar);
		if (hexChar.length != 4) {
			throw new HexException();
		}
		int retInt = 0;
		for (int i = 0; i < 4; i++) {
			int temp;
			char tempChar = checkHexCharToUpper(hexChar[i]);
			if (tempChar > 'A') {
				temp = tempChar - 'A' + 10;
			} else {
				temp = tempChar - '0';
			}
			retInt = (retInt << 4) + temp;
		}
		return (char) retInt;
	}

	public static void main(String[] args) throws Exception {
		SP.println(charHexCharsToChar(charToHexChars(' ')));
		SP.println(charHexCharsToChar(charToHexChars(' ')));
		SP.println(charHexCharsToChar(charToHexChars(' ')));
		SP.println(charHexCharsToChar(charToHexChars(' ')));
		SP.println("byte to hex:");
		SP.println(byteToHexChars((byte) 0x1FFF));
		SP.println(byteToHexChars((byte) 0x0));
		SP.println(byteToHexChars((byte) 0xF));
		SP.println(byteToHexChars((byte) 0x7F));
		SP.println(byteToHexChars((byte) 0x80));
		SP.println("char to hex:");
		SP.println(charToHexChars((char) 0xF7F7F));
		SP.println(charToHexChars('0'));
		SP.println(charToHexChars('a'));
		SP.println((int) '0');
		SP.println(charToHexChars('f'));
		SP.println(charToHexChars('A'));
		SP.println(charToHexChars('F'));
		SP.println(charToHexChars((char) 0xF7F7F));
		SP.println(charToHexChars((char) 0xF7F7F));
		SP.println(charToHexChars((char) 0xF7F7F));
	}

}
