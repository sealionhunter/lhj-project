package com.ustcsoft.gs.myerp.webui.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.ustcsoft.gs.myerp.webui.food.Food;

@Entity
@Table
public class OrderDetail {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "foodId", length = 256, nullable = false)
	private String foodId;
//	@Column(name = "foodName", length = 256, nullable = false)
//	private String foodName;
//	@Column(name = "state", length = 256, nullable = false)
//	private int state = 0;
	@Column(name = "unit")
	private int unit = 0;
//	@Column(name = "unitName")
//	private String unitName;
//	@Column(name = "description", length = 1024)
//	private String description;
	@Column(name = "oId", length = 255)
	private String oid;

	@Transient
	private Food food;

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
	 * @return the foodId
	 */
	public String getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId
	 *            the foodId to set
	 */
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

//	/**
//	 * @return the foodName
//	 */
//	public String getFoodName() {
//		return foodName;
//	}
//
//	/**
//	 * @param foodName
//	 *            the foodName to set
//	 */
//	public void setFoodName(String foodName) {
//		this.foodName = foodName;
//	}
//
//	/**
//	 * @return the state
//	 */
//	public int getState() {
//		return state;
//	}
//
//	/**
//	 * @param state
//	 *            the state to set
//	 */
//	public void setState(int state) {
//		this.state = state;
//	}

	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

//	/**
//	 * @return the unitName
//	 */
//	public String getUnitName() {
//		return unitName;
//	}
//
//	/**
//	 * @param unitName
//	 *            the unitName to set
//	 */
//	public void setUnitName(String unitName) {
//		this.unitName = unitName;
//	}
//
//	/**
//	 * @return the description
//	 */
//	public String getDescription() {
//		return description;
//	}
//
//	/**
//	 * @param description
//	 *            the description to set
//	 */
//	public void setDescription(String description) {
//		this.description = description;
//	}

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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
}
