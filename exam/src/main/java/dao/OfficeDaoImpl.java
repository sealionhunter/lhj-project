package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Depart;
import model.Office;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OfficeDaoImpl implements OfficeDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public Office get(int id) throws Exception {
        return (Office) getHibernateTemplate().load(Office.class, id);
    }

    @Override
    public List<Office> list() throws Exception {
        String hql = "from Office O, Depart D, City C "
                + " where O.departId=D.id and C.id = D.cityId ";
        List<?> list = getHibernateTemplate().find(hql);

        List<Office> offices = new ArrayList<Office>();
        for (Object o : list) {
            Object[] objects = (Object[]) o;
            final Office office = (Office) objects[0];
            office.setDepartName(((Depart) objects[1]).getName());
            office.setCityName(((City) objects[2]).getName());
            final String sql = "select count(*) as applyCount, "
                    + " sum(case when state = 2 then 1 else 0 end) as validateCount "
                    + " from Apply as apply where apply.officeid = ? ";
            Object[] apply = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return (Object[]) session.createSQLQuery(sql)
                            .setParameter(0, office.getId()).uniqueResult();
                }
            });
            int validateCount = ((Number) apply[1]).intValue();
            office.setApplyCount(((Number) apply[0]).intValue());
            office.setValidataCount(validateCount);
            office.setScale(validateCount / office.getRecruits());
            offices.add(office);
        }
        return offices;
    }

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
}
