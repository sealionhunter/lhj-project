package com.ustcsoft.gs.myerp.webui.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(name = "idx_category", columnNames = {
		"category", "value" }) })
public class Category {

	@Id
	@GeneratedValue(generator = "IDENTITY")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private int uuid;
	@Column(name = "name", length = 256, nullable = false)
	private String name;
	@Column(name = "value", length = 256, nullable = false)
	private String value;
	@Column(name = "category", length = 256)
	private String category;

	/**
	 * @return the uuid
	 */
	public int getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(int uuid) {
		this.uuid = uuid;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
}
