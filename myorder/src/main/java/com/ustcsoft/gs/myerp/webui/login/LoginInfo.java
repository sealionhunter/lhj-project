package com.ustcsoft.gs.myerp.webui.login;

public class LoginInfo {

	private String uid;
	private String uname;
	private String hid;
	private String hname;
	private boolean isAdmin;

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public LoginInfo() {
		super();
	}

	public LoginInfo(String uid, String uname) {
		super();
		this.uid = uid;
		this.uname = uname;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname
	 *            the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the hid
	 */
	public String getHid() {
		return hid;
	}

	/**
	 * @param hid
	 *            the hid to set
	 */
	public void setHid(String hid) {
		this.hid = hid;
	}

	/**
	 * @return the name
	 */
	public String getHname() {
		return hname;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setHname(String hname) {
		this.hname = hname;
	}
}
