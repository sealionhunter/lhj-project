package lhj.java.util;

import java.security.MessageDigest;

public class CheckKey {
	public static boolean checkKey(String name, String key) throws Exception {
		byte nameBytes[];
		StringBuffer theKey;
		if (name == null || key == null)
			return false;
		nameBytes = name.getBytes();
		theKey = new StringBuffer();
		byte td[];
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(nameBytes);
		td = md.digest();
		if (td.length != 16)
			return false;
		int n = 0;
		for (int i = 12; i >= 9; i--) {
			n = td[i] & 0xff;
			theKey.append((char) (n % 25 + 65));
		}

		theKey.append("-");
		for (int i = 7; i >= 3; i--) {
			n = td[i] & 0xff;
			if (i == 5)
				theKey.append(Integer.toHexString(n % 16));
			else
				theKey.append(n % 10);
		}

		theKey.append("3");
		theKey.append("-");
		n = td[15] & 0xff;
		theKey.append((char) (n % 25 + 65));
		for (int i = 14; i >= 12; i--) {
			n = td[i] & 0xff;
			theKey.append(n % 10);
		}

		for (int i = 7; i >= 4; i--) {
			n = td[i] & 0xff;
			theKey.append(Integer.toHexString(n % 16));
		}

		theKey.append("-");
		for (int i = 3; i >= 0; i--) {
			n = td[i] & 0xff;
			theKey.append(n % 10);
		}
		System.out.println(theKey.toString());
		return key.equals(theKey.toString());
	}
}