package dao;

import java.util.List;

import model.Admin;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class AdminDaoImpl implements AdminDao {
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

    @SuppressWarnings("unchecked")
    @Override
    public Admin get(String adminId) throws Exception {
        List<Admin> adminList = getHibernateTemplate().find(
                "from Admin where administratorId=?", adminId);
        if (adminList == null || adminList.isEmpty()) {
            return null;
        }
        return adminList.get(0);
    }

    @Override
    public void update(Admin admin) throws Exception {
        getHibernateTemplate().update(admin);
    }
}
