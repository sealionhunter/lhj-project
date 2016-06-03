package lhj.java.util;

import java.security.MessageDigest;

public final class PassWordCrypter {

	/**
	 */
	private static final String CRYPTOR_MD5 = "MD5";

	/**
	 * default contractor
	 */
	private PassWordCrypter() {
		super();
	}

	/**
	 * 
	 * @param password
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String crypt(String password) throws Exception {
		byte[] pwd = null;

		if (password != null) {
			MessageDigest md = MessageDigest.getInstance(CRYPTOR_MD5);
			md.update(password.getBytes());
			pwd = md.digest();
		} else {
			pwd = new byte[0];
		}
		return byte2hex(pwd);
	}

	/**
	 * 
	 * @param password
	 * @param spwd
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean isCrypt(String password, String spwd) {
		boolean isOk = false;
		String pwd = null;

		try {
			if (password != null && spwd != null) {
				pwd = crypt(password);
				if (pwd.equals(spwd)) {
					isOk = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}

	/**
	 * 
	 * @param b
	 * 
	 * @return String
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		int len = b.length;
		for (int i = 0; i < len; i++) {
			stmp = (Integer.toHexString(b[i] & 0XFF));

			if (stmp.length() == 1) {
				stmp = "0".concat(stmp);
			}
			hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(crypt("1"));
		// System.out.println(crypt("hjliangS1"));
		// System.out.println(crypt("hjliangJ1"));
	}
}
