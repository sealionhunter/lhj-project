package dao;

import java.util.List;

import model.Room;
import model.Seat;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class RoomDaoImpl implements RoomDao {
    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void add(Room room) throws Exception {
        hibernateTemplate.saveOrUpdate(room);
    }

    @Override
    public Room get(Integer userId) throws Exception {
        return (Room) hibernateTemplate.load(Room.class, userId);
    }

    @Override
    public void update(Room room) throws Exception {
        hibernateTemplate.saveOrUpdate(room);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Room> list() throws Exception {
        return hibernateTemplate.loadAll(Room.class);
    }

    @Override
    public void addSeat(Seat seat) throws Exception {
        hibernateTemplate.save(seat);
    }

    @Override
    public void addSeat(List<Seat> seats) throws Exception {
        hibernateTemplate.saveOrUpdateAll(seats);
    }

    @Override
    public Seat getSeat(Integer id) throws Exception {
        return (Seat) hibernateTemplate.load(Seat.class, id);
    }

    @Override
    public void updateSeat(Seat seat) throws Exception {
        hibernateTemplate.saveOrUpdate(seat);
    }

}
