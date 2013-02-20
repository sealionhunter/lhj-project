package dao;

import java.util.List;

import model.Apply;
import model.ApplyPK;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class ApplyDaoImpl implements ApplyDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public ApplyPK add(Apply user) throws Exception {
        return (ApplyPK) getHibernateTemplate().save(user);
    }
    public Apply get(Integer userId, Integer officeId) {
    	ApplyPK id = new ApplyPK();
    	id.setUserid(userId);
    	id.setOfficeid(officeId);
    	return (Apply) getHibernateTemplate().load(Apply.class, id);
    	
    }
	public List<Apply> find(Integer userId) throws Exception {
        return getHibernateTemplate().find("from Apply as apply where apply.id.userid = ?", userId);
	}
	
	public void delete(Apply apply) {
		getHibernateTemplate().delete(apply);
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
