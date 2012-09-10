package com.ustcsoft.gs.myerp.webui.common;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyLoginInterceptor implements Interceptor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8493661591672788233L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object o = ServletActionContext.getRequest().getSession()
				.getAttribute(MyErpConstant.SESSION_LOGIN_INFO);
		if (o == null) {
			return "loginForm";
		}
		// TODO Auto-generated method stub
		return invocation.invoke();
	}

}
