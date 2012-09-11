package com.ustcsoft.gs.myerp.webui.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.myerp.webui.common.Paging;
import com.ustcsoft.gs.myerp.webui.table.DinningTable;
import com.ustcsoft.gs.myerp.webui.table.DinningTableDao;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private DinningTableDao dinningTableDao;

	@Override
	public List<Orders> list(String hid, SearchCondition condition,
			Paging paging) throws Exception {
		return orderDao.list(hid, condition, paging);
	}

	@Override
	public int count(String tid, SearchCondition condition) throws Exception {
		return orderDao.count(tid, condition);
	}

	@Override
	public Orders get(String id) throws Exception {
		return orderDao.get(id);
	}

	@Override
	@Transactional
	public void save(Orders order, List<OrderDetail> details) throws Exception {
		String oid = order.getUuid();
		BigDecimal oprice = BigDecimal.ZERO;
		for (OrderDetail detail : details) {
			detail.setUuid(null);
			oprice = oprice.add(detail.getFood().getPrice());
		}
		order.setOprice(oprice);
		if (oid == null || oid.startsWith("tmp")) {
			order.setState("1");
			oid = orderDao.add(order);
			for (OrderDetail detail : details) {
				detail.setOid(oid);
			}
		} else {
			orderDao.update(order);
		}
		orderDao.setDetail(oid, details);
		DinningTable table = dinningTableDao.get(order.getTid());
		if (table != null) {
			table.setState("1");
			dinningTableDao.update(table);
		}
	}

	@Override
	@Transactional
	public void add(Orders table, List<OrderDetail> details) throws Exception {
		String oid = orderDao.add(table);
		orderDao.setDetail(oid, details);
	}

	@Override
	@Transactional
	public void update(Orders order, List<OrderDetail> details)
			throws Exception {
		orderDao.update(order);
		orderDao.setDetail(order.getUuid(), details);
	}

	public Orders findByTable(String tid) throws Exception {
		SearchCondition condition = new SearchCondition();
		condition.setState("1");
		List<Orders> os = orderDao.list(tid, condition, null);
		return os == null || os.size() == 0 ? null : os.get(0);
	}

	@Transactional
	public void pay(String uuid, BigDecimal rprice) throws Exception {
		Orders o = orderDao.get(uuid);
		o.setState("2");
		o.setRprice(rprice);
		orderDao.update(o);
	}

	@Override
	@Transactional
	public void delete(String[] ids) throws Exception {
		for (String id : ids) {
			orderDao.delete(id);
			orderDao.setDetail(id, null);
		}
	}

	public List<OrderDetail> getDetail(String oid) throws Exception {
		return orderDao.getDetail(oid);
	}

	/**
	 * @return the hotelDao
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}

	/**
	 * @param hotelDao
	 *            the hotelDao to set
	 */
	public void setOrderDao(OrderDao hotelDao) {
		this.orderDao = hotelDao;
	}

	// @Override
	// @Transactional
	// public void setDetail(String oid, List<OrderDetail> sDetails)
	// throws Exception {
	// orderDao.setDetail(oid, sDetails);
	// }

}
