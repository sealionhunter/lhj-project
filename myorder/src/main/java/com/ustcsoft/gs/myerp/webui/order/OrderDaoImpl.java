package com.ustcsoft.gs.myerp.webui.order;

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
import com.ustcsoft.gs.myerp.webui.food.Food;

@Service(value = "orderDao")
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Orders> list(String hid, SearchCondition condition,
			Paging paging) throws Exception {
		List<Object> conditions = new ArrayList<Object>();
		conditions.add(hid);

		DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
		makeCondition(condition, hid, criteria);

		hibernateTemplate.findByCriteria(criteria, paging.getPcurrent(),
				paging.getPercount());
		List<Orders> result = (List<Orders>) hibernateTemplate.findByCriteria(
				criteria, (paging.getPcurrent() - 1) * paging.getPercount(),
				paging.getPercount());
		// hibernateTemplate.find(sql);

		if (result == null || result.size() == 0) {
			return new ArrayList<Orders>(0);
		}
		List<String> oids = new ArrayList<String>();
		for (Orders table : result) {
			oids.add(table.getUuid());
		}

		List<Object[]> counts = hibernateTemplate
				.findByNamedParam(
						"select oid, count(*) as tcount from OrderDetail where oid IN (:oids) group by oid",
						"oids", oids);
		if (counts != null && counts.size() > 0) {
			for (Orders table : result) {
				for (Object[] count : counts) {
					if (count[0].equals(table.getUuid())) {
						table.setHasDetail(((Number) count[1]).intValue() > 0);
					}
				}
			}
		}
		return result;
	}

	private void makeCondition(SearchCondition condition, String hid,
			DetachedCriteria criteria) {

		criteria.add(Restrictions.eq("tid", hid));
		if (condition != null) {
			if (!StringUtils.isEmpty(condition.getCname())) {
				criteria.add(Restrictions.like("cName", condition.getCname(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getAddress())) {
				criteria.add(Restrictions.like("address",
						condition.getAddress(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getState())) {
				criteria.add(Restrictions.like("state", condition.getState(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getTelNum())) {
				criteria.add(Restrictions.like("telNum", condition.getTelNum(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getCategory())) {
				criteria.add(Restrictions.like("category",
						condition.getCategory(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getCcount())) {
				criteria.add(Restrictions.gt("ccount",
						MyHotelUtils.parseInt(condition.getCcount())));
			}
			if (!StringUtils.isEmpty(condition.getDescription())) {
				criteria.add(Restrictions.like("description",
						condition.getDescription(), MatchMode.ANYWHERE));
			}
		}
	}

	@Override
	public int count(String hid, SearchCondition condition) throws Exception {

		DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
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
	public Orders get(String id) throws Exception {
		Orders order = (Orders) hibernateTemplate.get(Orders.class, id);
		// if (order != null) {
		// DetachedCriteria criteria = DetachedCriteria
		// .forClass(OrderDetail.class);
		// criteria.add(Restrictions.eq("oid", order.getUuid()));
		// List<OrderDetail> details = hibernateTemplate
		// .findByCriteria(criteria);
		// order.setDetails(details);
		// }
		return order;
	}

	public List<OrderDetail> getDetail(String oid) throws Exception {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OrderDetail.class);
		criteria.add(Restrictions.eq("oid", oid));
		List<OrderDetail> details = hibernateTemplate.findByCriteria(criteria);
		if (details.size() == 0) {
			return details;
		}
		List<String> fids = new ArrayList<String>();
		for (OrderDetail detail : details) {
			fids.add(detail.getFoodId());
		}
		DetachedCriteria criteria2 = DetachedCriteria.forClass(Food.class);
		criteria2.add(Restrictions.in("uuid", fids));
		List<Food> foods = hibernateTemplate.findByCriteria(criteria2);
		for (OrderDetail detail : details) {
			for (Food food : foods) {
				if (food.getUuid().equals(detail.getFoodId())) {
					detail.setFood(food);
					break;
				}
			}
		}
		return details;
	}

	public void setDetail(String oid, List<OrderDetail> sDetails)
			throws Exception {
		List<OrderDetail> details = getDetail(oid);
		for (OrderDetail detail : details) {
			hibernateTemplate.delete(detail);
		}
		if (sDetails != null) {
			for (OrderDetail detail : sDetails) {
				hibernateTemplate.save(detail);
			}
		}
	}

	@Override
	public String add(Orders table) throws Exception {
		return (String) hibernateTemplate.save(table);
	}

	@Override
	public void delete(String id) throws Exception {
		Orders hotel = (Orders) hibernateTemplate.load(Orders.class, id);
		hibernateTemplate.delete(hotel);
	}

	@Override
	public void update(Orders table) throws Exception {
		hibernateTemplate.merge(table);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
