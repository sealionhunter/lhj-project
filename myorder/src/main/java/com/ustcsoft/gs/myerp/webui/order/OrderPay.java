package com.ustcsoft.gs.myerp.webui.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class OrderPay {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name = "oprice", precision = 12, scale = 2, nullable = false)
	private BigDecimal oprice;
	@Column(name = "rprice", precision = 12, scale = 2, nullable = false)
	private BigDecimal rprice;
	@Column(name = "description", length = 1024)
	private String description;

	@Column(name = "oId", length = 255)
	private String oid;

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
	 * @return the oId
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oId
	 *            the oId to set
	 */
	public void setOid(String oId) {
		this.oid = oId;
	}
}
