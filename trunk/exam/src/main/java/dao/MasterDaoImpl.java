package dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import model.Master;

public class MasterDaoImpl implements MasterDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Master> list(int category) throws Exception {
        // TODO Auto-generated method stub
        return getHibernateTemplate().find("from Master as master where master.id.category = ?", category );
    }

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}
