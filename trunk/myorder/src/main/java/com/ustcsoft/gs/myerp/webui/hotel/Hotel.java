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
	@Column(name = "name", length = 256, nullable = false)
	private String name;
	@Column(name = "maxService", nullable = false)
	private int maxService = 0;

//	@Column(name = "serviceFrom", nullable = false)
	private String serviceFrom;
//	@Column(name = "serviceTo", nullable = false)
	private String serviceTo;
//	@Column(name = "foodCategory", nullable = false)
	private String foodCategory;
	
	@Column(name = "address", length = 256, nullable = false)
	private String address;
	@Column(name = "telNum", length = 64, nullable = false)
	private String telNum;
	@Column(name = "description", length = 1024)
	private String description;
	@Column(name = "imgUrl", length = 512)
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

}
