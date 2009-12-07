package lhj.learn.ejb3.facade.Impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lhj.learn.ejb3.facade.UserInfoFacade;
import lhj.learn.jpa.persistence.UserInfoEntity;

/**
 * Session Bean implementation class UserInfoFacadeImpl
 */
@Stateless(name="UserInfoFacade")
public class UserInfoFacadeImpl implements UserInfoFacade {

    /**
     * Default constructor.
     */
    public UserInfoFacadeImpl() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserInfoEntity> list() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        try {
            factory = Persistence.createEntityManagerFactory("lhj-openjpa");
            em = factory.createEntityManager();
            return em.createNamedQuery("UserInfoEntity.list").getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void add(UserInfoEntity userinfo) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            factory = Persistence.createEntityManagerFactory("lhj-openjpa");
            em = factory.createEntityManager();
            et = em.getTransaction();
            et.begin();

            em.persist(userinfo);

            et.commit();

        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void remove(long id) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            factory = Persistence.createEntityManagerFactory("lhj-openjpa");
            em = factory.createEntityManager();
            et = em.getTransaction();
            et.begin();
            UserInfoEntity toBeDelete = em.find(UserInfoEntity.class, id);
            if (toBeDelete != null) {
                em.remove(toBeDelete);
            }

            et.commit();

        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
