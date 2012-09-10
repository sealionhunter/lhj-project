package com.ustcsoft.gs.myerp.webui.user;

import java.util.Date;

public class SearchCondition {
	private String uid;
	private String uname;
	private String sex;
	private Date birthday;
	private String telNum;
	private String address;
	private boolean isAdmin;
	private Date validTo;
	private Date validToFrom;
	private Date validToTo;

	public String getUid() {
		return uid;
	}

	public void setUid(String userId) {
		this.uid = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String userName) {
		this.uname = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String userSex) {
		this.sex = userSex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String mobile) {
		this.telNum = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/**
	 * @return the validToFrom
	 */
	public Date getValidToFrom() {
		return validToFrom;
	}

	/**
	 * @param validToFrom
	 *            the validToFrom to set
	 */
	public void setValidToFrom(Date validToFrom) {
		this.validToFrom = validToFrom;
	}

	/**
	 * @return the validToTo
	 */
	public Date getValidToTo() {
		return validToTo;
	}

	/**
	 * @param validToTo
	 *            the validToTo to set
	 */
	public void setValidToTo(Date validToTo) {
		this.validToTo = validToTo;
	}
}
