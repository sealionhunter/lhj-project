package com.ustcsoft.gs.myerp.webui.food;

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

import com.ustcsoft.gs.myerp.webui.common.MyHotelUtils;
import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "foodDao")
public class FoodDaoImpl implements FoodDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Food> list(String hid, SearchCondition condition, Paging paging)
			throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Food.class);
		makeCondition(condition, hid, criteria);
		List<Food> result = (List<Food>) hibernateTemplate.findByCriteria(
				criteria, (paging.getPcurrent() - 1) * paging.getPercount(),
				paging.getPercount());
		// hibernateTemplate.find(sql);

		if (result == null || result.size() == 0) {
			return new ArrayList<Food>(0);
		}
		return result;
	}

	private void makeCondition(SearchCondition condition, String hid,
			DetachedCriteria criteria) {
		criteria.add(Restrictions.eq("hid", hid));
		if (condition != null) {
			if (!StringUtils.isEmpty(condition.getName())) {
				criteria.add(Restrictions.like("name", condition.getName(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getCategory())) {
				criteria.add(Restrictions.like("category",
						condition.getCategory(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getSubCategory())) {
				criteria.add(Restrictions.like("subCategory",
						condition.getSubCategory(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getPrice())) {
				criteria.add(Restrictions.ge("price",
						MyHotelUtils.parseBigDecimal(condition.getPrice())));
			}
			if (!StringUtils.isEmpty(condition.getDescription())) {
				criteria.add(Restrictions.like("description",
						condition.getDescription(), MatchMode.ANYWHERE));
			}
		}
	}

	public int count(String hid, SearchCondition condition) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Food.class);
		makeCondition(condition, hid, criteria);
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
	public Food get(String id) throws Exception {
		return (Food) hibernateTemplate.get(Food.class, id);
	}

	@Override
	public void add(Food table) throws Exception {
		hibernateTemplate.save(table);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		for (String id : ids) {
			Food hotel = (Food) hibernateTemplate.get(Food.class, id);
			if (hotel == null) {
				throw new Exception("餐桌不存在或已被删除");
			}
			try {
				hibernateTemplate.delete(hotel);
			} catch (Exception ex) {
				throw new Exception("删除餐桌失败！", ex);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Food> gets(String[] ids) throws Exception {

		DetachedCriteria criteria = DetachedCriteria.forClass(Food.class);
		criteria.add(Restrictions.in("uuid", ids));
		return hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public void update(Food table) throws Exception {
		hibernateTemplate.merge(table);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
