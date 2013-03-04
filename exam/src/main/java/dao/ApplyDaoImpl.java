package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Apply;
import model.ApplyPK;
import model.Depart;
import model.Exam;
import model.Office;
import model.User;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
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
        if (cmd.getHomeTown() != null && cmd.getHomeTown().trim().length() > 0) {
            hql += "and U.homeTown LIKE ? ";
            paramList.add(getLikeStr(cmd.getHomeTown()));
        }
        if (cmd.getPoliticalCode() > 0) {
            hql += "and U.politicalCode=? ";
            paramList.add(cmd.getPoliticalCode());
        }
        if (cmd.getName() != null && cmd.getName().trim().length() > 0) {
            hql += "and U.name LIKE ? ";
            paramList.add(getLikeStr(cmd.getName().trim()));
        }
        if (cmd.getIdCardNo() != null && cmd.getIdCardNo().trim().length() > 0) {
            hql += "and U.idCardNo = ? ";
            paramList.add(cmd.getIdCardNo().trim());
        }
        if (cmd.getDegree() != null && cmd.getDegree().trim().length() > 0) {
            hql += "and U.degree LIKE ? ";
            paramList.add(getLikeStr(cmd.getDegree().trim()));
        }
        if (cmd.getIdentity() != null
                && Integer.parseInt(cmd.getIdentity()) >= 0) {
            hql += "and U.identity = ? ";
            paramList.add(cmd.getIdentity().trim());
        }
        if (cmd.getAge() >= 0) {
            hql += "and (? - U.birthdayYear < ? AND (? - U.birthdayYear) >= ?)";
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            switch (cmd.getAge()) {
            case 0:// below 18
                paramList.add(year);
                paramList.add(18);
                paramList.add(year);
                paramList.add(0);
                break;
            case 1: // 18~25
                paramList.add(year);
                paramList.add(25);
                paramList.add(year);
                paramList.add(18);
                break;
            case 2:// 25~30
                paramList.add(year);
                paramList.add(30);
                paramList.add(year);
                paramList.add(25);
                break;
            case 3:// 30~35
                paramList.add(year);
                paramList.add(35);
                paramList.add(year);
                paramList.add(30);
                break;
            case 4:// 35以上
                paramList.add(year);
                paramList.add(100);
                paramList.add(year);
                paramList.add(35);
                break;
            }
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

            apply.setUser(user);
            apply.setOffice(office);
            apply.setDepart(dept);

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

            apply.setUser(user);
            apply.setOffice(office);
            apply.setDepart(dept);

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

    @Override
    public void delete(Apply apply) {
        getHibernateTemplate().delete(apply);
    }

    @Override
    public void update(Apply apply) throws Exception {
        getHibernateTemplate().update(apply);
    }

    @Override
    public boolean hasUnVerified() throws Exception {

        return (Boolean) hibernateTemplate.execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                String sql = "select count(*) from apply where state = ?";
                SQLQuery query = session.createSQLQuery(sql);
                query.setInteger(0, 0);
                Object obj = query.uniqueResult();
                if (obj != null && ((Number) obj).intValue() > 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        });
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

    private String getLikeStr(String target) {
        if (target == null) {
            return null;
        }
        return "%" + target.replaceAll("%", "%%") + "%";
    }
}
