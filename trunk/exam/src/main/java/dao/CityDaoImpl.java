package dao;

import java.util.List;

import model.City;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class CityDaoImpl implements CityDao {

    private HibernateTemplate hibernateTemplate;

    // @Override
    // public void add(User user) throws Exception {
    // getHibernateTemplate().save(user);
    // }

    @Override
    public City get(int id) throws Exception {
        return (City) getHibernateTemplate().load(City.class, id);
    }

    @Override
    public List<City> list() throws Exception {
        return getHibernateTemplate().loadAll(City.class);
    }

    //
    // @Override
    // public void delete(int id) throws Exception {
    // this.getHibernateTemplate().delete(get(id));
    //
    // }
    //
    // @Override
    // public void update(User user) throws Exception {
    // this.getHibernateTemplate().saveOrUpdate(user);
    //
    // }

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
