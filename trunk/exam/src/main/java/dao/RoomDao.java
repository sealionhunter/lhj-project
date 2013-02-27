package dao;

import java.util.List;

import model.Room;
import model.Seat;

public interface RoomDao {
    public void add(Room room) throws Exception;

    public List<Room> list() throws Exception;

    public Room get(Integer userId) throws Exception;

    public void update(Room room) throws Exception;

    public void addSeat(Seat admission) throws Exception;

    public void addSeat(List<Seat> admissions) throws Exception;

    public Seat getSeat(Integer userId) throws Exception;

    public void updateSeat(Seat admission) throws Exception;
}
