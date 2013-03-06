package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Office;
import model.Room;
import model.RoomOffice;
import model.Seat;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import command.RoomEditCommand;

public class RoomDaoImpl implements RoomDao {
    private static final String hql = 
            " SELECT "
            + "     O.id as id, "
            + "     O.name as name, "
            + "     O.code as code, "
            + "     U.totalUser, "
            + "     RO.totalSeats, "
            + "     O.departId as departId, "
            + "     D.departName as departName "
            + " FROM "
            + "     office O "
            + "     left join (SELECT id as departId, name as departName from depart) D on O.departId = D.departId "
            + "     left join (SELECT "
            + "         officeId, "
            + "         count(1) as totalUser "
            + "     FROM "
            + "         apply "
            + "     WHERE "
            + "         state = 2 "
            + "     GROUP BY "
            + "         officeId "
            + "     ) U on U.officeId = O.id "
            + "     left join (SELECT "
            + "         officeId, "
            + "         sum(assignSeats) as totalSeats "
            + "     FROM "
            + "         roomOffice "
            + "     GROUP BY "
            + "         officeId "
            + "     ) RO on RO.officeId = O.id "
            + " where "
            // + "     (totalSeats is null OR totalUser > totalSeats) "
            + "     1=1  ";
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

    public void addRoom(Room room) throws Exception {
        hibernateTemplate.save(room);
    }

    @Override
    public Room getRoom(Integer roomId) throws Exception {
        return (Room) hibernateTemplate.get(Room.class, roomId);
    }

    @Override
    public void updateRoom(Room room) throws Exception {
        hibernateTemplate.saveOrUpdate(room);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Room> listRoom() throws Exception {
        return hibernateTemplate.loadAll(Room.class);
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public List<Room> listRoomByOid(Integer officeId) throws Exception {
    // if (officeId == null || officeId == -1) {
    // return hibernateTemplate.loadAll(Room.class);
    // }
    // return (List<Room>) hibernateTemplate.find(
    // "from model.Room as room where room.officeId = ? ", officeId);
    // }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public List<RoomOffice> listRoomOfficeByOid(Integer officeId)
            throws Exception {
        List l;
        if (officeId == null || officeId == -1) {
            l = hibernateTemplate
                    .find("from model.RoomOffice ro, model.Room  r where r.id = ro.roomId ");
        } else {
            l = hibernateTemplate
                    .find("from model.RoomOffice ro, model.Room  r where r.id = ro.roomId and ro.officeId = ? ",
                            officeId);
        }
        List<RoomOffice> rs = new ArrayList<RoomOffice>(l.size());
        for (Object o : l) {
            Object[] os = (Object[]) o;
            RoomOffice ro = (RoomOffice) os[0];
            Room r = (Room) os[1];
            ro.setRoom(r);
            rs.add(ro);
        }
        return rs;
    }

    @Override
    public void addSeat(Seat seat) throws Exception {
        hibernateTemplate.save(seat);
    }

    @Override
    public void addSeat(List<Seat> seats) throws Exception {
        hibernateTemplate.saveOrUpdateAll(seats);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Seat getSeat(Integer userId) throws Exception {
        List<Seat> seats = (List<Seat>) hibernateTemplate.find(
                "from model.Seat as seat where seat.userId = ? ", userId);
        if (seats == null || seats.isEmpty()) {
            return null;
        }
        Seat seat = seats.get(0);
        if (seat != null) {
            seat.setRoom((Room) hibernateTemplate.get(Room.class,
                    seat.getRoomId()));
        }
        return seat;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Seat getSeatByRidAndCode(Integer roomId, String seatCode)
            throws Exception {
        List<Seat> seats = (List<Seat>) hibernateTemplate
                .find("from model.Seat as seat where seat.code = ? and seat.roomId = ?",
                        new Object[] { seatCode, roomId });
        if (seats == null || seats.isEmpty()) {
            return null;
        }
        Seat seat = seats.get(0);
        if (seat != null) {
            seat.setRoom((Room) hibernateTemplate.get(Room.class,
                    seat.getRoomId()));
        }
        return seat;
    }

    @Override
    public void updateSeat(Seat seat) throws Exception {
        hibernateTemplate.saveOrUpdate(seat);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Office> listOfficeByCond(final RoomEditCommand cmd)
            throws Exception {

        return hibernateTemplate.executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                String sql = hql;
                Map<String, Integer> params = new HashMap<String, Integer>();
                if (cmd.getDepartId() != null && cmd.getDepartId() > 0) {
                    sql += " and O.departId = :departId";
                    params.put("departId", cmd.getDepartId());
                }
                if (cmd.getOfficeId() != null && cmd.getOfficeId() > 0) {
                    sql += " and O.id = :officeId";
                    params.put("officeId", cmd.getOfficeId());
                }
                SQLQuery query = session.createSQLQuery(sql);
                for (Map.Entry<String, Integer> entry : params.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
                List<Object[]> objs = query.list();

                List<Office> offices = new ArrayList<Office>();
                if (objs != null && objs.size() > 0) {
                    for (Object[] obj : objs) {
                        Office office = new Office();
                        office.setId((Integer) obj[0]);
                        office.setName((String) obj[1]);
                        office.setCode((String) obj[2]);
                        if (obj[3] == null) {
                            office.setTotalUsers(0);
                        } else {
                            office.setTotalUsers(((Number) obj[3]).intValue());
                        }
                        if (obj[4] == null) {
                            office.setTotalSeats(0);
                        } else {
                            office.setTotalSeats(((Number) obj[4]).intValue());
                        }
                        office.setDepartId((Integer) obj[5]);
                        office.setDepartName((String) obj[6]);
                        offices.add(office);
                    }
                }
                return offices;
            }
        });
    }

    public boolean checkSeats(final RoomEditCommand cmd) throws Exception {

        return (Boolean) hibernateTemplate.execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {

                String sql = "select count(*) from (" + hql;
                Map<String, Integer> params = new HashMap<String, Integer>();
                if (cmd.getDepartId() != null && cmd.getDepartId() > 0) {
                    sql += " and O.departId = :departId";
                    params.put("departId", cmd.getDepartId());
                }
                if (cmd.getOfficeId() != null && cmd.getOfficeId() > 0) {
                    sql += " and O.id = :officeId";
                    params.put("officeId", cmd.getOfficeId());
                }
                sql += " and (totalSeats is null OR totalUser > totalSeats)) offices";
                SQLQuery query = session.createSQLQuery(sql);

                for (Map.Entry<String, Integer> entry : params.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }

                Object obj = query.uniqueResult();
                if (obj != null && ((Number) obj).intValue() > 0) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
        });
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public List<Seat> findSeat(Integer roomId) throws Exception {
    // return (List<Seat>) hibernateTemplate.find(
    // "from model.Seat as seat where seat.roomId = ? ", roomId);
    // }

    @SuppressWarnings("unchecked")
    @Override
    public boolean checkRoom(String code) throws Exception {
        List<Room> rooms = (List<Room>) hibernateTemplate.find(
                "from model.Room as room where room.code = ? ", code);
        return rooms != null && rooms.size() > 0;
    }

    @Override
    public void removeSeatByUids(final List<Integer> uids) throws Exception {
        hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return session
                        .createQuery(
                                "delete from model.Seat as seat where seat.userId IN (:uids)")
                        .setParameterList("uids", uids).executeUpdate();
            }
        });
        hibernateTemplate.flush();
    }

    @Override
    public void removeRoom(Room room) throws Exception {
        hibernateTemplate.delete(room);
    }

    @Override
    public void removeRoomOffice(RoomOffice roomOffice) throws Exception {
        hibernateTemplate.delete(roomOffice);
    }

    @Override
    public boolean checkAdmission(final RoomEditCommand cmd) throws Exception {

        return (Boolean) hibernateTemplate.execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {

                String sql = " select count(*) from apply A "
                        + " inner join office O on A.officeId = O.id "
                        + " inner join admission Ad on Ad.userId = A.userId "
                        + " where Ad.printFlg = true ";
                Map<String, Integer> params = new HashMap<String, Integer>();
                if (cmd.getDepartId() != null && cmd.getDepartId() > 0) {
                    sql += " and O.departId = :departId";
                    params.put("departId", cmd.getDepartId());
                }
                if (cmd.getOfficeId() != null && cmd.getOfficeId() > 0) {
                    sql += " and O.id = :officeId";
                    params.put("officeId", cmd.getOfficeId());
                }
                SQLQuery query = session.createSQLQuery(sql);

                for (Map.Entry<String, Integer> entry : params.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }

                Object obj = query.uniqueResult();
                if (obj != null && ((Number) obj).intValue() > 0) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
        });
    }

    @Override
    public void addRoomOffice(List<RoomOffice> roomOffices) throws Exception {
        for (RoomOffice ro : roomOffices) {
            hibernateTemplate.save(ro);
        }
    }

    @Override
    public RoomOffice getRoomOffice(Integer roomOfficeId) throws Exception {
        return (RoomOffice) hibernateTemplate.get(RoomOffice.class, roomOfficeId);
    }

    @Override
    public void removeRoomOfficeByRid(final Integer roomId) throws Exception {
        hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return session
                        .createQuery(
                                "delete from model.RoomOffice as ro where ro.roomId = :roomId")
                        .setParameter("roomId", roomId).executeUpdate();
            }
        });
        hibernateTemplate.flush();
        
    }
}
