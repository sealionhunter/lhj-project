package com.ustcsoft.gs.myerp.webui.login;

public interface LoginService {
	public LoginInfo login(String uid, String password) throws Exception;
}
