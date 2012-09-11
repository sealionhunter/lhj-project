package com.ustcsoft.gs.myerp.webui.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Hotel {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "name", length = 256)
	private String name;
	@Column(name = "maxService")
	private int maxService = 0;
	@Column(name = "serviceFrom", length = 256)
	private String serviceFrom;
	@Column(name = "serviceTo", length = 256)
	private String serviceTo;
	@Column(name = "foodCategoris", length = 256)
	private String foodCategoris;
	@Column(name = "address", length = 256)
	private String address;
	@Column(name = "telNum", length = 64)
	private String telNum;
	@Column(name = "description")
	private String description;
	@Column(name = "imgUrl")
	private String imgUrl;

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

	// /**
	// * @return the uid
	// */
	// public String getUid() {
	// return uid;
	// }
	//
	// /**
	// * @param uid
	// * the uid to set
	// */
	// public void setUid(String uid) {
	// this.uid = uid;
	// }

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

	//
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

	/**
	 * @return the serviceFrom
	 */
	public String getServiceFrom() {
		return serviceFrom;
	}

	/**
	 * @param serviceFrom
	 *            the serviceFrom to set
	 */
	public void setServiceFrom(String serviceFrom) {
		this.serviceFrom = serviceFrom;
	}

	/**
	 * @return the serviceTo
	 */
	public String getServiceTo() {
		return serviceTo;
	}

	/**
	 * @param serviceTo
	 *            the serviceTo to set
	 */
	public void setServiceTo(String serviceTo) {
		this.serviceTo = serviceTo;
	}

	/**
	 * @return the foodCategoris
	 */
	public String getFoodCategoris() {
		return foodCategoris;
	}

	/**
	 * @param foodCategoris
	 *            the foodCategoris to set
	 */
	public void setFoodCategoris(String foodCategoris) {
		this.foodCategoris = foodCategoris;
	}
}
