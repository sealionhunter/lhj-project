package com.ustcsoft.gs.myerp.webui.order;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Orders {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "hid", length = 256)
	private String hid;
	@Column(name = "tid", length = 256)
	private String tid;
	@Column(name = "cName", length = 256)
	private String cname;
	@Column(name = "cid", length = 256)
	private String cid;
	@Column(name = "ccount", nullable = false)
	private int ccount = 1;
	@Column(name = "state", length = 256, nullable = false)
	private String state;
	@Column(name = "category", length = 256, nullable = false)
	private String category;
	@Column(name = "description", length = 1024)
	private String description;
	@Column(name = "address", length = 256, nullable = false)
	private String address;
	@Column(name = "telNum", length = 64, nullable = false)
	private String telNum;
	@Column(name = "oprice", precision = 12, scale = 2, nullable = false)
	private BigDecimal oprice = BigDecimal.ZERO;
	@Column(name = "rprice", precision = 12, scale = 2, nullable = false)
	private BigDecimal rprice = BigDecimal.ZERO;

	@Transient
	private boolean hasDetail;
	@Transient
	private List<OrderDetail> details;

	/**
	 * @return the id
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setUuid(String id) {
		this.uuid = id;
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
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the ccount
	 */
	public int getCcount() {
		return ccount;
	}

	/**
	 * @param ccount
	 *            the ccount to set
	 */
	public void setCcount(int ccount) {
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

	public boolean isHasDetail() {
		return hasDetail;
	}

	public void setHasDetail(boolean hasFood) {
		this.hasDetail = hasFood;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> detail) {
		this.details = detail;
	}

	/**
	 * @return the oprice
	 */
	public BigDecimal getOprice() {
		return oprice;
	}

	/**
	 * @param oprice
	 *            the oprice to set
	 */
	public void setOprice(BigDecimal oprice) {
		this.oprice = oprice;
	}

	/**
	 * @return the rprice
	 */
	public BigDecimal getRprice() {
		return rprice;
	}

	/**
	 * @param rprice
	 *            the rprice to set
	 */
	public void setRprice(BigDecimal rprice) {
		this.rprice = rprice;
	}
}
