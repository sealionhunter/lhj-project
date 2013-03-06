package dao;

import java.util.List;

import model.Apply;
import model.City;
import model.Depart;
import model.Office;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class OfficeDaoImpl implements OfficeDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public Office get(int id) throws Exception {
        return (Office) getHibernateTemplate().load(Office.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Office> list() throws Exception {
        List<Office> offices = getHibernateTemplate().loadAll(Office.class);
        for (Office office : offices) {
            if (office.getDepartId() != null) {
                Depart depart = (Depart) getHibernateTemplate().load(
                        Depart.class, office.getDepartId());
                if (depart != null) {
                    office.setDepartName(depart.getName());
                }
                City city = (City) getHibernateTemplate().load(City.class,
                        depart.getCityId());
                if (city != null) {
                    office.setCityName(city.getName());
                }
                List<Apply> applys = getHibernateTemplate().find(
                        "from Apply as apply where apply.id.officeid = ?",
                        office.getId());
                int applyCount = applys.size();
                if (applys != null && applyCount != 0) {
                    office.setApplyCount(applyCount);
                    int validateCount = 0;
                    for (Apply apply : applys) {
                        if (apply.getState() == 2) {
                            validateCount++;
                        }
                        office.setValidataCount(validateCount);
                    }
                    int recruits = office.getRecruits();
                    office.setScale(validateCount / recruits);
                }
            }

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
