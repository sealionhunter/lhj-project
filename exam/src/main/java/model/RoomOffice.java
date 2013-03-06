package model;

import java.io.Serializable;

/**
 * The persistent class for the room database table.
 * 
 */
public class RoomOffice implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6117979594419141053L;
    private Integer id;
    private int officeId;
    private int roomId;
    private int assignSeats;
    private int assignedSeat = 0;
    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getAssignSeats() {
        return assignSeats;
    }

    public void setAssignSeats(int assignSeats) {
        this.assignSeats = assignSeats;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void incSeats() {
        assignedSeat++;
    }

    public int getAssignedSeat() {
        return assignedSeat;
    }

    public void setAssignedSeat(int assignedSeat) {
        this.assignedSeat = assignedSeat;
    }

}