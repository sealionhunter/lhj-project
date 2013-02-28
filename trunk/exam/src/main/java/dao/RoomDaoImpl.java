package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Office;
import model.Room;
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
            + "     office.id as id, "
            + "     office.name as name, "
            + "     office.code as code, "
            + "     totalUser, "
            + "     totalSeats, "
            + "     office.departId as departId, "
            + "     departName as departName "
            + " FROM "
            + "     office office "
            + "     left join (select id as departId, name as departName from depart) depart on office.departId = depart.departId "
            + "     left join (SELECT "
            + "         officeId, "
            + "         count(1) as totalUser "
            + "     FROM "
            + "         apply "
            + "     WHERE "
            + "         state = 2 "
            + "     GROUP BY "
            + "         officeId "
            + "     ) users on users.officeId = office.id "
            + "     left join (SELECT "
            + "         officeId, "
            + "         sum(seats) as totalSeats "
            + "     FROM "
            + "         room "
            + "     GROUP BY "
            + "         officeId "
            + "     ) rooms on rooms.officeId = office.id "
            + " where "
            //+ "     (totalSeats is null OR totalUser > totalSeats) "
            + "     1=1  "
            ;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Room> list(Integer officeId) throws Exception {
        return (List<Room>) hibernateTemplate.find(
                "from model.Room as room where room.officeId = ? ", officeId);
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Office> findOfficeInfo(final RoomEditCommand cmd)
            throws Exception {

        return hibernateTemplate.executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                String sql = hql;
                Map<String, Integer> params = new HashMap<String, Integer>();
                if (cmd.getDepartId() != null && cmd.getDepartId() > 0) {
                    sql += " and office.departId = :departId";
                    params.put("departId", cmd.getDepartId());
                }
                if (cmd.getOfficeId() != null && cmd.getOfficeId() > 0) {
                    sql += " and office.id = :officeId";
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

}
