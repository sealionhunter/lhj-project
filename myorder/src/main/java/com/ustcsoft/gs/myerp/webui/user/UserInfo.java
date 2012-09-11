package com.ustcsoft.gs.myerp.webui.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class UserInfo {

	@Id
	@Column(name = "uid", length = 64, unique = true, nullable = false)
	private String uid;
	@Column(name = "hid", length = 255)
	private String hid;
	@Column(name = "password", length = 64, nullable = false)
	private String password;
	@Column(name = "uname", length = 128, nullable = false)
	private String uname;
	@Column(name = "sex", length = 2, nullable = false)
	private String sex;
	private Date birthday;
	@Column(name = "email", length = 64)
	private String email;
	@Column(name = "mobile", length = 16)
	private String telNum;
	@Column(name = "address", length = 512)
	private String address;
	@Column(name = "isAdmin", nullable = false)
	private boolean isAdmin = false;
	@Column(name = "validTo")
	private Date validTo;
	@Transient
	private String hname;

	public String getUid() {
		return uid;
	}

	public void setUid(String userId) {
		this.uid = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	 * @return the hname
	 */
	public String getHname() {
		return hname;
	}

	/**
	 * @param hname
	 *            the hname to set
	 */
	public void setHname(String hname) {
		this.hname = hname;
	}
}
