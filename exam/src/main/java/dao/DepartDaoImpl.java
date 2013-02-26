package dao;

import java.util.List;

import model.City;
import model.Depart;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class DepartDaoImpl implements DepartDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public Depart get(int id) throws Exception {
        return (Depart) getHibernateTemplate().load(Depart.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Depart> list() throws Exception {
        List<Depart> departs = getHibernateTemplate().loadAll(Depart.class);

        for (Depart depart : departs) {
            if (depart.getCityId() != null) {
                City city = (City) getHibernateTemplate().load(City.class,
                        depart.getCityId());
                if (city != null) {
                    depart.setCityName(city.getName());
                }
            }
        }
        return departs;
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
