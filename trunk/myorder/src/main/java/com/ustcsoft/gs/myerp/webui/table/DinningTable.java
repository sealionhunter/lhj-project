package com.ustcsoft.gs.myerp.webui.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class DinningTable {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "name", length = 256, nullable = false)
	private String name;
	@Column(name = "hid", length = 256, nullable = false)
	private String hid;
	@Column(name = "state", length = 256, nullable = false)
	private String state;
	@Column(name = "category", length = 256, nullable = false)
	private String category;
	@Column(name = "area", length = 256, nullable = false)
	private String area;
	@Column(name = "maxService", length = 256, nullable = false)
	private int maxService = 1;
	// @Column(name = "manager", length = 128)
	// private String manager;
	@Column(name = "description", length = 1024)
	private String description;
	@Column(name = "imgUrl", length = 512)
	private String imgUrl;

	@Transient
	private boolean hasOrders;
	@Transient
	private String hname;

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public boolean isHasOrders() {
		return hasOrders;
	}

	public void setHasOrders(boolean hasOrders) {
		this.hasOrders = hasOrders;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the maxService
	 */
	public int getMaxService() {
		return maxService;
	}

	/**
	 * @param maxService
	 *            the maxService to set
	 */
	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}

	// /**
	// * @return the manager
	// */
	// public String getManager() {
	// return manager;
	// }
	//
	// /**
	// * @param manager
	// * the manager to set
	// */
	// public void setManager(String manager) {
	// this.manager = manager;
	// }

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
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl
	 *            the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
