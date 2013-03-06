package model;

import java.io.Serializable;
import java.util.ArrayList;
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

    private String position;

    private int seats;

    private int remainSeats;

    private int assignedSeats = 0;

    private List<Seat> seatsSet;

    private List<RoomOffice> roomOffices = new ArrayList<RoomOffice>();

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

    public List<RoomOffice> getRoomOffices() {
        return roomOffices;
    }

    public void setRoomOffices(List<RoomOffice> roomOffices) {
        this.roomOffices = roomOffices;
    }

    public int getAssignedSeats() {
        return assignedSeats;
    }

    public void setAssignedSeats(int assignedSeats) {
        this.assignedSeats = assignedSeats;
    }

    public void add(RoomOffice ro) {
        roomOffices.add(ro);
        assignedSeats += ro.getAssignSeats();
        remainSeats -= ro.getAssignSeats();
    }

    public int remain() {
        return remainSeats;
    }

    public int getRemainSeats() {
        return remainSeats;
    }

    public void setRemainSeats(int remainSeats) {
        this.remainSeats = remainSeats;
    }

}