package dao;

import java.util.List;

import model.City;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class CityDaoImpl implements CityDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public City get(int id) throws Exception {
        return (City) getHibernateTemplate().load(City.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<City> list() throws Exception {
        return getHibernateTemplate().loadAll(City.class);
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
