package dao;

import java.util.List;

import command.RoomEditCommand;

import model.Office;
import model.Room;
import model.Seat;

public interface RoomDao {
    public void add(Room room) throws Exception;

    public List<Room> list() throws Exception;

    public List<Room> list(Integer officeId) throws Exception;

    public Room get(Integer userId) throws Exception;

    public void update(Room room) throws Exception;

    public void addSeat(Seat admission) throws Exception;

    public void addSeat(List<Seat> admissions) throws Exception;

    public Seat getSeat(Integer userId) throws Exception;

    public List<Seat> findSeat(Integer roomId) throws Exception;

    public void updateSeat(Seat admission) throws Exception;

    public List<Office> findOfficeInfo(RoomEditCommand cmd) throws Exception;

    public boolean checkSeats(RoomEditCommand cmd) throws Exception;

    public boolean checkRoom(String code) throws Exception;

    public void removeSeat(Integer userId) throws Exception;

    public boolean checkAdmission(RoomEditCommand condition) throws Exception;
}
