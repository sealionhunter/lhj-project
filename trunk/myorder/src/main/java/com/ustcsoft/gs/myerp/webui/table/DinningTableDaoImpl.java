package com.ustcsoft.gs.myerp.webui.table;

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

@Service(value = "dinningTableDao")
public class DinningTableDaoImpl implements DinningTableDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<DinningTable> list(String hid, SearchCondition condition,
			Paging paging) throws Exception {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(DinningTable.class);
		makeCondition(condition, hid, criteria);
		List<DinningTable> result = (List<DinningTable>) hibernateTemplate
				.findByCriteria(criteria,
						(paging.getPcurrent() - 1) * paging.getPercount(),
						paging.getPercount());
		// hibernateTemplate.find(sql);

		if (result == null || result.size() == 0) {
			return new ArrayList<DinningTable>(0);
		}
		List<String> tids = new ArrayList<String>();
		for (DinningTable table : result) {
			tids.add(table.getUuid());
		}

		// DetachedCriteria criteria2 = DetachedCriteria.forClass(Orders.class);
		// criteria2.add(Restrictions.in("tid", tids)).setProjection(
		// Projections.countDistinct("tid"));
		List<Object[]> counts = hibernateTemplate
				.findByNamedParam(
						"select tid, count(*) as tcount from Orders where state = :state and tid IN (:tids) group by tid",
						new String[] { "state", "tids" }, new Object[] { "1",
								tids });
		if (counts != null && counts.size() > 0) {
			for (DinningTable table : result) {
				for (Object[] count : counts) {
					if (count[0].equals(table.getUuid())) {
						table.setHasOrders(((Number) count[1]).intValue() > 0);
					}
				}
			}
		}
		return result;

		// List<Object> conditions = new ArrayList<Object>();
		// conditions.add(hid);
		// StringBuffer sql = makeCondition(condition, conditions);
		// List<DinningTable> result = (List<DinningTable>) hibernateTemplate
		// .find(sql.toString(), conditions.toArray());
		//
		// if (result == null || result.size() == 0) {
		// return new ArrayList<DinningTable>(0);
		// }
		// return result;
	}

	public int count(String hid, SearchCondition condition) throws Exception {
		// String sql = "select count(1) from " + Food.class;
		// + makeCondition(condition, conditions);
		// criteria
		DetachedCriteria criteria = DetachedCriteria
				.forClass(DinningTable.class);
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

	private void makeCondition(SearchCondition condition, String hid,
			DetachedCriteria criteria) {

		criteria.add(Restrictions.eq("hid", hid));
		if (condition != null) {
			if (!StringUtils.isEmpty(condition.getName())) {
				criteria.add(Restrictions.like("name", condition.getName(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getArea())) {
				criteria.add(Restrictions.like("area", condition.getArea(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getState())) {
				criteria.add(Restrictions.like("state", condition.getState(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getCategory())) {
				criteria.add(Restrictions.like("category",
						condition.getCategory(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(condition.getMaxService())) {
				criteria.add(Restrictions.gt("maxService",
						MyHotelUtils.parseInt(condition.getMaxService())));
			}
			if (!StringUtils.isEmpty(condition.getDescription())) {
				criteria.add(Restrictions.like("description",
						condition.getDescription(), MatchMode.ANYWHERE));
			}
		}
	}

	@Override
	public DinningTable get(String id) throws Exception {
		return (DinningTable) hibernateTemplate.get(DinningTable.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(DinningTable table) throws Exception {
		List<DinningTable> exists = hibernateTemplate.find("from "
				+ DinningTable.class.getName() + " where name = ? and hid = ?",
				table.getName(), table.getHid());
		if (exists != null && exists.size() > 0) {
			throw new Exception("同名的餐桌已经存在。请重新输入！");
		}
		try {
			hibernateTemplate.save(table);
		} catch (Exception ex) {
			throw new Exception("追加餐桌失败！", ex);
		}
	}

	@Override
	public void delete(String[] ids) throws Exception {
		for (String id : ids) {
			DinningTable hotel = (DinningTable) hibernateTemplate.get(
					DinningTable.class, id);
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

	@Override
	public void update(DinningTable table) throws Exception {
		if (hibernateTemplate.get(DinningTable.class, table.getUuid()) == null) {
			throw new Exception("餐桌不存在或已被删除.");
		}
		try {
			hibernateTemplate.merge(table);
		} catch (Exception ex) {
			throw new Exception("追加餐桌失败！", ex);
		}
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
