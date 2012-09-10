package com.ustcsoft.gs.myerp.webui.hotel;

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

import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "hotelDao")
public class HotelDaoImpl implements HotelDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Hotel> list(SearchCondition condition, Paging paging)
			throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Hotel.class);
		makeCondition(condition, criteria);

		hibernateTemplate.findByCriteria(criteria, paging.getPcurrent(),
				paging.getPercount());
		List<Hotel> result = (List<Hotel>) hibernateTemplate.findByCriteria(
				criteria, (paging.getPcurrent() - 1) * paging.getPercount(),
				paging.getPercount());
		// hibernateTemplate.find(sql);

		if (result == null || result.size() == 0) {
			return new ArrayList<Hotel>(0);
		}
		return result;
	}

	private void makeCondition(SearchCondition condition,
			DetachedCriteria criteria) {
		if (condition != null) {
			if (!StringUtils.isEmpty(condition.getName())) {
				criteria.add(Restrictions.like("name", condition.getName(),
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
			if (!StringUtils.isEmpty(condition.getDescription())) {
				criteria.add(Restrictions.like("description",
						condition.getDescription(), MatchMode.ANYWHERE));
			}
		}
	}

	@Override
	public int count(SearchCondition condition) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Hotel.class);
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
	public Hotel get(String hid) throws Exception {
		return hibernateTemplate.get(Hotel.class, hid);
	}

	@Override
	public String add(Hotel hotel) throws Exception {
		return (String) hibernateTemplate.save(hotel);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		for (String id : ids) {
			Hotel hotel = (Hotel) hibernateTemplate.get(Hotel.class, id);
			if (hotel == null) {
				throw new Exception("酒店不存在或已被删除");
			}
			try {
				hibernateTemplate.delete(hotel);
			} catch (Exception ex) {
				throw new Exception("删除酒店失败！", ex);
			}
		}
	}

	@Override
	public void update(Hotel hotel) throws Exception {
		hibernateTemplate.merge(hotel);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
