package dao;

import java.util.List;

import command.RoomEditCommand;

import model.Office;
import model.Room;
import model.RoomOffice;
import model.Seat;

public interface RoomDao {

    public Room getRoom(Integer roomId) throws Exception;

    public Seat getSeat(Integer seatId) throws Exception;

    public RoomOffice getRoomOffice(Integer roomOfficeId) throws Exception;

    public Seat getSeatByRidAndCode(Integer roomId, String seatCode)
            throws Exception;

    public void addRoom(Room room) throws Exception;

    public void addSeat(Seat seat) throws Exception;

    public void addSeat(List<Seat> seats) throws Exception;

    public void addRoomOffice(List<RoomOffice> roomOffices) throws Exception;

    public void updateRoom(Room room) throws Exception;

    public void updateSeat(Seat admission) throws Exception;

    public void removeRoom(Room room) throws Exception;

    public void removeRoomOffice(RoomOffice roomOffice) throws Exception;

    public void removeRoomOfficeByRid(Integer roomId) throws Exception;

    public void removeSeatByUids(List<Integer> uids) throws Exception;

    public void removeSeatByRid(final Integer rid) throws Exception;

    public List<Room> listRoom() throws Exception;

    // public List<Room> listRoomByOid(Integer officeId) throws Exception;

    public List<RoomOffice> listRoomOfficeByOid(Integer officeId)
            throws Exception;

    public List<Office> listOfficeByCond(RoomEditCommand cmd) throws Exception;

    // public List<Seat> findSeat(Integer roomId) throws Exception;

    public boolean checkSeats(RoomEditCommand cmd) throws Exception;

    public boolean checkRoom(String code) throws Exception;

    public boolean checkAdmission(RoomEditCommand condition) throws Exception;
}
