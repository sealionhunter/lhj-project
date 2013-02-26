package dao;

import java.util.List;

import model.User;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public Integer add(User user) throws Exception {
        return (Integer) getHibernateTemplate().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findIdCardNo(String idCardNo) throws Exception {
        return getHibernateTemplate().find(
                "from User as user where user.idCardNo = ?", idCardNo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUserName(String userName) throws Exception {
        return getHibernateTemplate().find(
                "from User as user where user.name = ?", userName);
    }

    @Override
    public User get(int id) throws Exception {
        return (User) getHibernateTemplate().load(User.class, id);
    }

    @Override
    public void delete(int id) throws Exception {
        this.getHibernateTemplate().delete(get(id));

    }

    @Override
    public void update(User user) throws Exception {
        this.getHibernateTemplate().saveOrUpdate(user);

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
