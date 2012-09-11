package com.ustcsoft.gs.myerp.webui.login;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;

@Service(value = "loginAction")
public class LoginAction extends AbstractAction<LoginInfo> {
	private String userId;
	private String password;

	@Autowired
	private LoginService loginService;

	public String execute() throws Exception {
		setErrormsg(null);
		if (getLoginInfo() != null) {
			return "main";
		}
		return Action.SUCCESS;
	}

	public String login() throws Exception {
		setErrormsg(null);
		try {
			LoginInfo l = loginService.login(getUserId(), getPassword());
			ServletActionContext.getRequest().getSession()
					.setAttribute(MyErpConstant.SESSION_LOGIN_INFO, l);
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String logout() throws Exception {
		setErrormsg(null);
		try {
			ServletActionContext.getRequest().getSession()
					.removeAttribute(MyErpConstant.SESSION_LOGIN_INFO);
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
