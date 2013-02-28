package dao;

import java.util.List;

import model.Admission;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class AdmissionDaoImpl implements AdmissionDao {
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

    public void add(Admission admission) throws Exception {
        hibernateTemplate.save(admission);
    }

    public void add(List<Admission> admissions) throws Exception {
        for (Admission admission : admissions) {
            hibernateTemplate.save(admission);
        }
    }

    @Override
    public Admission get(Integer userId) throws Exception {
        return (Admission) hibernateTemplate.load(Admission.class, userId);
    }

    @Override
    public void update(Admission admission) throws Exception {
        hibernateTemplate.update(admission);
    }
}
