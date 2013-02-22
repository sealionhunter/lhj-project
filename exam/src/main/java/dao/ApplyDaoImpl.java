package dao;

import java.util.List;

import model.Apply;
import model.ApplyPK;
import model.Depart;
import model.Exam;
import model.Office;
import model.User;

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
        return getHibernateTemplate().find(
                "from Apply as apply where apply.id.userid = ?", userId);
    }

    @Override
    public List<Apply> list() throws Exception {
        List<Apply> applys = getHibernateTemplate().loadAll(Apply.class);
        for (Apply apply : applys) {
            if (apply.getId() != null) {
                User user = (User) getHibernateTemplate().load(User.class,
                        apply.getId().getUserid());
                if (user != null) {
                    apply.setApplyUserName(user.getName());
                    apply.setIdCardNo(user.getIdCardNo());
                }
                Office office = (Office) getHibernateTemplate().load(
                        Office.class, apply.getId().getOfficeid());
                if (office != null) {
                    apply.setApplyOfficeName(office.getName());
                    apply.setApplyOfficeCode(office.getCode());
                    apply.setApplyOfficeDescrip(office.getDescription());
                    Depart depart = (Depart) getHibernateTemplate().load(
                            Depart.class, office.getDepartId());
                    apply.setApplyDepartName(depart.getName());
                }

            }

        }
        return applys;
    }

    public List<Apply> findApplyInfo(Integer userId) throws Exception {
        List<Apply> applys = getHibernateTemplate().find(
                "from Apply as apply where apply.id.userid = ?", userId);
        for (Apply apply : applys) {
            if (apply.getId() != null) {
                User user = (User) getHibernateTemplate().load(User.class,
                        apply.getId().getUserid());
                if (user != null) {
                    apply.setApplyUserName(user.getName());
                    apply.setIdCardNo(user.getIdCardNo());
                }
                Office office = (Office) getHibernateTemplate().load(
                        Office.class, apply.getId().getOfficeid());
                if (office != null) {
                    apply.setApplyOfficeName(office.getName());
                    apply.setApplyOfficeCode(office.getCode());
                    apply.setApplyOfficeDescrip(office.getDescription());
                    Depart depart = (Depart) getHibernateTemplate().load(
                            Depart.class, office.getDepartId());
                    apply.setApplyDepartName(depart.getName());
                }
                Exam exam = (Exam) getHibernateTemplate().load(Exam.class,
                        office.getExamId());
                if (exam != null) {
                    apply.setApplyExamId(exam.getId());
                    apply.setApplyExamPosition(exam.getExamPosition());
                }
            }

        }
        return applys;
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
