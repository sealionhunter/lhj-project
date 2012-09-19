package com.ustcsoft.gs.myerp.webui.common;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;

import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

public class MyHotelUtils {

	private static String KEY_MD5 = "MD5";

	static LoginInfo getLoginInfo() {
		return (LoginInfo) ServletActionContext.getRequest().getSession()
				.getAttribute(MyErpConstant.SESSION_LOGIN_INFO);
	}

	public static String like(String value) {
		return "%" + value + "%";
	}

	public static Integer parseInt(String maxService) {
		if (maxService == null) {
			// TODO Auto-generated method stub
			return 0;
		}
		try {
			return Integer.valueOf(maxService);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static BigDecimal parseBigDecimal(String maxService) {
		if (maxService == null) {
			// TODO Auto-generated method stub
			return BigDecimal.ZERO;
		}
		try {
			return new BigDecimal(maxService);
		} catch (NumberFormatException e) {
			return BigDecimal.ZERO;
		}
	}

	public static String encrypt(String plain) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encrypted = md5.digest(plain.getBytes());
		// return StringUtils.newStringUtf8(encrypted);
		return Base64.encodeBase64String(encrypted);
	}

	public static String encode(String plain) {
		return Base64.encodeBase64String(plain.getBytes());
	}

	public static String decode(String plain) {
		return new String(Base64.decodeBase64(plain));
	}
}
