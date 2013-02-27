package model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String code;

	private String name;

	private int officeId;

	private String position;

	private int seats;

	private List<Seat> seatsSet;

	public Room() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOfficeId() {
		return this.officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSeats() {
		return this.seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Seat> getSeatsSet() {
		return this.seatsSet;
	}

	public void setSeatsSet(List<Seat> seatsSet) {
		this.seatsSet = seatsSet;
	}

}