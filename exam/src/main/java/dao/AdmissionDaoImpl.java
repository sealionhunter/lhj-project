package dao;

import java.sql.SQLException;
import java.util.List;

import model.Admission;
import model.Room;
import model.Seat;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AdmissionDaoImpl implements AdmissionDao {
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

    public void add(Admission admission) throws Exception {
        hibernateTemplate.save(admission);
    }

    public void add(List<Admission> admissions) throws Exception {
        for (Admission admission : admissions) {
            hibernateTemplate.save(admission);
        }
    }

    @Override
    public Admission get(Integer userId) throws Exception {
        String hql = "from Admission A, Seat S, Room R where A.userId=S.userId and R.id=S.roomId and A.userId=?";
        List<?> list = hibernateTemplate.find(hql, userId);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Object obj = list.get(0);
        Object[] o = (Object[]) obj;
        Admission admission = (Admission) o[0];
        Seat seat = (Seat) o[1];
        Room room = (Room) o[2];
        seat.setRoom(room);
        admission.setSeat(seat);

        return admission;
    }

    @Override
    public void update(Admission admission) throws Exception {
        hibernateTemplate.update(admission);
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public void deleteByUids(final List<Integer> uids) throws Exception {

        List<Admission> ads = (List<Admission>) hibernateTemplate
                .execute(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        return session
                                .createQuery(
                                        "from Admission where userId in (:uids)")
                                .setParameterList("uids", uids).list();
                    }
                });
        for (Admission ad : ads) {
            hibernateTemplate.delete(ad);
        }
        hibernateTemplate.flush();
    }
}
