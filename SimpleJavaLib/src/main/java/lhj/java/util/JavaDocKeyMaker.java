package lhj.java.util;

import java.security.MessageDigest;

public class JavaDocKeyMaker {
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
			String user = System.getProperty("user.name");
			SP.println(user);
			SP.println(JavaDocKeyMaker.keyGen(user));
		} else if (args.length == 1) {
			SP.println(args[0]);
			SP.println(JavaDocKeyMaker.keyGen(args[0]));
		} else {
			SP.println(args[0]);
			SP.println(JavaDocKeyMaker.keyGen(args[0], args[1]));
		}

	}

	public static String keyGen(String s) {
		return keyGen(s, "JavaDOCHelper");
	}

	public static String keyGen(String s1, String type) {
		if (s1 == null || type == null)
			return "Error occurs!";
		byte abyte0[] = s1.getBytes();
		StringBuffer stringbuffer = new StringBuffer();
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.update(abyte0);
			byte abyte1[] = messagedigest.digest();
			if (abyte1.length != 16)
				return "Error occurs!";
			if (type.equals("JavaBeginner_Test")) {
				for (int j5 = 7; j5 >= 4; j5--) {
					int l = abyte1[j5] & 0xff;
					stringbuffer.append((char) (l % 25 + 65));
				}

				stringbuffer.append("-");
				for (int i6 = 15; i6 >= 11; i6--) {
					int i1 = abyte1[i6] & 0xff;
					if (i6 == 13)
						stringbuffer.append(Integer.toHexString(i1 % 16));
					else
						stringbuffer.append(i1 % 10);
				}

				stringbuffer.append("1");
				stringbuffer.append("-");
				int j1 = abyte1[11] & 0xff;
				stringbuffer.append((char) (j1 % 25 + 65));
				for (int l6 = 10; l6 >= 8; l6--) {
					int k1 = abyte1[l6] & 0xff;
					stringbuffer.append(k1 % 10);
				}

				for (int k7 = 7; k7 >= 4; k7--) {
					int l1 = abyte1[k7] & 0xff;
					stringbuffer.append(Integer.toHexString(l1 % 16));
				}

				stringbuffer.append("-");
				for (int j8 = 3; j8 >= 0; j8--) {
					int i2 = abyte1[j8] & 0xff;
					stringbuffer.append(i2 % 10);
				}

			} else if (type.equals("JavaDOCHelper")) {
				for (int k5 = 12; k5 >= 9; k5--) {
					int j2 = abyte1[k5] & 0xff;
					stringbuffer.append((char) (j2 % 25 + 65));
				}

				stringbuffer.append("-");
				for (int j6 = 7; j6 >= 3; j6--) {
					int k2 = abyte1[j6] & 0xff;
					if (j6 == 5)
						stringbuffer.append(Integer.toHexString(k2 % 16));
					else
						stringbuffer.append(k2 % 10);
				}

				stringbuffer.append("3");
				stringbuffer.append("-");
				int l2 = abyte1[15] & 0xff;
				stringbuffer.append((char) (l2 % 25 + 65));
				for (int i7 = 14; i7 >= 12; i7--) {
					int i3 = abyte1[i7] & 0xff;
					stringbuffer.append(i3 % 10);
				}

				for (int l7 = 7; l7 >= 4; l7--) {
					int j3 = abyte1[l7] & 0xff;
					stringbuffer.append(Integer.toHexString(j3 % 16));
				}

				stringbuffer.append("-");
				for (int k8 = 3; k8 >= 0; k8--) {
					int k3 = abyte1[k8] & 0xff;
					stringbuffer.append(k3 % 10);
				}

			} else if (type.equals("JavaHelpAuthor")) {
				for (int l5 = 8; l5 >= 5; l5--) {
					int l3 = abyte1[l5] & 0xff;
					stringbuffer.append((char) (l3 % 25 + 65));
				}

				stringbuffer.append("-");
				for (int k6 = 11; k6 >= 7; k6--) {
					int i4 = abyte1[k6] & 0xff;
					if (k6 == 9)
						stringbuffer.append(Integer.toHexString(i4 % 16));
					else
						stringbuffer.append(i4 % 10);
				}

				stringbuffer.append("4");
				stringbuffer.append("-");
				int j4 = abyte1[12] & 0xff;
				stringbuffer.append((char) (j4 % 25 + 65));
				for (int j7 = 15; j7 >= 13; j7--) {
					int k4 = abyte1[j7] & 0xff;
					stringbuffer.append(k4 % 10);
				}

				for (int i8 = 3; i8 >= 0; i8--) {
					int l4 = abyte1[i8] & 0xff;
					stringbuffer.append(Integer.toHexString(l4 % 16));
				}

				stringbuffer.append("-");
				for (int l8 = 7; l8 >= 4; l8--) {
					int i5 = abyte1[l8] & 0xff;
					stringbuffer.append(i5 % 10);
				}

			} else {
				return "Error occurs!";
			}
			return stringbuffer.toString();
		} catch (Exception exception) {
			return "Error occurs!";
		}
	}
}