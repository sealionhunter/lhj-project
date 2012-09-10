package com.ustcsoft.gs.myerp.webui.order;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class SearchCondition {

	private String tid;
	private String cname;
	private String ccount;
	private String state;
	private String category;
	private String description;
	private String address;
	private String telNum;

	@OneToMany
	@JoinColumn(name = "oId")
	private List<OrderDetail> orderDetails;

	@OneToOne
	@JoinColumn(name = "oId")
	private OrderPay orderPay;

	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @return the cName
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cName
	 *            the cName to set
	 */
	public void setCname(String cName) {
		this.cname = cName;
	}

	/**
	 * @return the ccount
	 */
	public String getCcount() {
		return ccount;
	}

	/**
	 * @param ccount
	 *            the ccount to set
	 */
	public void setCcount(String ccount) {
		this.ccount = ccount;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the orderDetails
	 */
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	/**
	 * @param orderDetails
	 *            the orderDetails to set
	 */
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the orderPay
	 */
	public OrderPay getOrderPay() {
		return orderPay;
	}

	/**
	 * @param orderPay
	 *            the orderPay to set
	 */
	public void setOrderPay(OrderPay orderPay) {
		this.orderPay = orderPay;
	}

	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * @param telNum
	 *            the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
