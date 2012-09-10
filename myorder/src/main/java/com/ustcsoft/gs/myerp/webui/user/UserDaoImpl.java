package com.ustcsoft.gs.myerp.webui.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.myerp.webui.common.Paging;
import com.ustcsoft.gs.myerp.webui.hotel.Hotel;

@Service(value = "userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> list() throws Exception {
		return hibernateTemplate.find("from " + UserInfo.class);
	}

	@Override
	public UserInfo get(String uid) throws Exception {
		UserInfo user = (UserInfo) hibernateTemplate.get(UserInfo.class, uid);
		if (user != null && !StringUtils.isEmpty(user.getHid())) {
			Hotel h = hibernateTemplate.get(Hotel.class, user.getUid());
			if (h != null) {
				user.setHname(h.getName());
			}
		}
		return user;
	}

	@Override
	@Transactional
	public void add(UserInfo user) throws Exception {
		hibernateTemplate.save(user);
	}

	@Override
	@Transactional
	public void delete(String userId) throws Exception {
		hibernateTemplate
				.delete(hibernateTemplate.load(UserInfo.class, userId));
	}

	@Override
	@Transactional
	public void update(UserInfo user) throws Exception {
		hibernateTemplate.update(user);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserInfo> list(SearchCondition condition, Paging paging)
			throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserInfo.class);
		makeCondition(condition, criteria);

		hibernateTemplate.findByCriteria(criteria, paging.getPcurrent(),
				paging.getPercount());
		List<UserInfo> result = (List<UserInfo>) hibernateTemplate
				.findByCriteria(criteria,
						(paging.getPcurrent() - 1) * paging.getPercount(),
						paging.getPercount());
		// hibernateTemplate.find(sql);

		if (result == null || result.size() == 0) {
			return new ArrayList<UserInfo>(0);
		}

		List<String> hids = new ArrayList<String>();
		for (UserInfo user : result) {
			if (!StringUtils.isEmpty(user.getHid())) {
				hids.add(user.getHid());
			}
		}
		if (hids.size() > 0) {
			DetachedCriteria criteria2 = DetachedCriteria.forClass(Hotel.class);
			criteria2.add(Restrictions.in("uuid", hids));
			List<Hotel> hotels = hibernateTemplate.findByCriteria(criteria2);
			for (UserInfo detail : result) {
				for (Hotel h : hotels) {
					if (h.getUuid().equals(detail.getHid())) {
						detail.setHname(h.getName());
						break;
					}
				}
			}
		}
		return result;
	}

	private void makeCondition(SearchCondition condition,
			DetachedCriteria criteria) {
		if (condition != null) {
			if (!StringUtils.isEmpty(condition.getUname())) {
				criteria.add(Restrictions.like("uname", condition.getUname(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getAddress())) {
				criteria.add(Restrictions.like("address",
						condition.getAddress(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getTelNum())) {
				criteria.add(Restrictions.like("telNum", condition.getTelNum(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getUid())) {
				criteria.add(Restrictions.like("uid", condition.getUid(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getSex())) {
				criteria.add(Restrictions.eq("sex", condition.getSex()));
			}
			if (condition.getValidToFrom() != null) {
				criteria.add(Restrictions.ge("validTo",
						condition.getValidToFrom()));
			}
			if (condition.getValidToTo() != null) {
				criteria.add(Restrictions.le("validTo",
						condition.getValidToTo()));
			}
		}
	}

	@Override
	public int count(SearchCondition condition) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserInfo.class);
		makeCondition(condition, criteria);
		criteria.setProjection(Projections.rowCount());
		hibernateTemplate.findByCriteria(criteria);
		Session s = null;
		try {
			s = hibernateTemplate.getSessionFactory().openSession();
			Number n = (Number) criteria.getExecutableCriteria(s)
					.uniqueResult();
			return n.intValue();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	@Override
	public void delete(String[] ids) throws Exception {
		for (String id : ids) {
			UserInfo hotel = (UserInfo) hibernateTemplate.get(UserInfo.class,
					id);
			if (hotel == null) {
				throw new Exception("用户不存在或已被删除");
			}
			try {
				hibernateTemplate.delete(hotel);
			} catch (Exception ex) {
				throw new Exception("删除用户失败！", ex);
			}
		}
	}

}
