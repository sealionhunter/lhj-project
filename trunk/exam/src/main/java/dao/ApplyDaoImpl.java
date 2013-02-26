package dao;

import java.util.ArrayList;
import java.util.List;

import model.Apply;
import model.ApplyPK;
import model.Depart;
import model.Exam;
import model.Office;
import model.User;

import org.springframework.orm.hibernate3.HibernateTemplate;

import command.SignUpPersonSearchCommand;

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

    @SuppressWarnings("unchecked")
    public List<Apply> find(Integer userId) throws Exception {
        return getHibernateTemplate().find(
                "from Apply as apply where apply.id.userid = ?", userId);
    }

    @Override
    public List<Apply> list(SignUpPersonSearchCommand cmd) throws Exception {
        String hql = "from Apply A, User U, Office O, Depart D "
                + "where A.id.userid=U.id and A.id.officeid=O.id and O.departId=D.id ";
        List<Object> paramList = new ArrayList<Object>();
        if (cmd.getDeptId() >= 0) {
            hql += "and D.id=? ";
            paramList.add(cmd.getDeptId());
        }
        if (cmd.getPostId() >= 0) {
            hql += "and O.id=? ";
            paramList.add(cmd.getPostId());
        }
        if (cmd.getState() >= 0) {
            hql += "and A.state=? ";
            paramList.add(cmd.getState());
        }
        if (cmd.getSex() >= 0) {
            hql += "and U.sex=? ";
            paramList.add(cmd.getSex());
        }
        if (cmd.getHomeTown() != null
                && cmd.getHomeTown().trim().length() > 0) {
            hql += "and (U.homeTown LIKE ? ";
            paramList.add("%" + cmd.getHomeTown() + "%");
            hql += ")";
        }
        if (cmd.getPoliticalCode() > 0) {
            hql += "and U.politicalCode=? ";
            paramList.add(cmd.getPoliticalCode());
        }
        List<?> list = null;
        if (!paramList.isEmpty()) {
            list = getHibernateTemplate().find(hql, paramList.toArray());
        } else {
            list = getHibernateTemplate().find(hql);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Apply> applyList = new ArrayList<Apply>();
        for (Object o : list) {
            Object[] objects = (Object[]) o;
            Apply apply = (Apply) objects[0];
            User user = (User) objects[1];
            Office office = (Office) objects[2];
            Depart dept = (Depart) objects[3];

            apply.setApplyUserName(user.getName());
            apply.setIdCardNo(user.getIdCardNo());
            apply.setApplyUserHomeTown(user.getHomeTown());
            apply.setAplyUserPolitical(String.valueOf(user.getPoliticalCode()));

            apply.setApplyOfficeName(office.getName());
            apply.setApplyOfficeCode(office.getCode());
            apply.setApplyOfficeDescrip(office.getDescription());
            apply.setApplyDepartName(dept.getName());

            applyList.add(apply);
        }
        return applyList;
    }

    public List<Apply> findApplyInfo(Integer userId) throws Exception {
        String hql = "from Apply A, User U, Office O, Depart D, Exam E "
                + "where A.id.userid=U.id and A.id.officeid=O.id and O.departId=D.id and E.id=O.examId "
                + "and U.id=?";
        List<?> list = getHibernateTemplate().find(hql, userId);

        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Apply> applyList = new ArrayList<Apply>();
        for (Object o : list) {
            Object[] objects = (Object[]) o;
            Apply apply = (Apply) objects[0];
            User user = (User) objects[1];
            Office office = (Office) objects[2];
            Depart dept = (Depart) objects[3];
            Exam exam = (Exam) objects[4];

            apply.setApplyUserName(user.getName());
            apply.setIdCardNo(user.getIdCardNo());
            apply.setApplyUserHomeTown(user.getHomeTown());
            apply.setAplyUserPolitical(String.valueOf(user.getPoliticalCode()));

            apply.setApplyOfficeName(office.getName());
            apply.setApplyOfficeCode(office.getCode());
            apply.setApplyOfficeDescrip(office.getDescription());
            apply.setApplyDepartName(dept.getName());
            apply.setApplyExamId(exam.getId());
            apply.setApplyExamPosition(exam.getExamPosition());

            applyList.add(apply);
        }
        return applyList;
    }

    public void delete(Apply apply) {
        getHibernateTemplate().delete(apply);
    }

    @Override
    public void update(Apply apply) throws Exception {
        getHibernateTemplate().update(apply);
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
