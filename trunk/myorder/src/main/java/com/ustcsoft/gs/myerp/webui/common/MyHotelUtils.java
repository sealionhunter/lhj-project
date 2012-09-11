package com.ustcsoft.gs.myerp.webui.common;

import java.math.BigDecimal;

import org.apache.struts2.ServletActionContext;

import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

public class MyHotelUtils {

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
}
