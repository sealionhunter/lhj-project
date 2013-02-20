package dao;

import java.util.List;

import model.Exam;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class ExamDaoImpl implements ExamDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public Exam get(int id) throws Exception {
        return (Exam) getHibernateTemplate().load(Exam.class, id);
    }

    @Override
    public List<Exam> list() throws Exception {
        return (List<Exam>) getHibernateTemplate().loadAll(Exam.class);
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
