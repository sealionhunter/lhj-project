package lhj.web.learn.springweb.repository.impl;

import java.util.List;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.repository.ProductDAO;

import org.hibernate.SessionFactory;

public class HibernateProductDAO implements ProductDAO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4086092595367199963L;
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProductList() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }

    @Override
    public void saveProduct(Product prod) {
        sessionFactory.getCurrentSession().save(prod);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void delete(Product prod) {
        sessionFactory.getCurrentSession().delete(prod);
    }

    @Override
    public Product load(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

}
