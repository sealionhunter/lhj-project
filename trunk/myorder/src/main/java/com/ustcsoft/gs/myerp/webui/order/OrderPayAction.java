package com.ustcsoft.gs.myerp.webui.order;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.table.DinningTable;
import com.ustcsoft.gs.myerp.webui.table.DinningTableService;

@Service(value = "orderPayAction")
public class OrderPayAction extends AbstractAction<Orders> {
	private String tid;
	private String rprice;
	private DinningTable table;
	private List<OrderDetail> details;

	@Autowired
	private OrderService orderService;
	@Autowired
	private DinningTableService dinningTableService;

	public String edit() throws Exception {
		setErrormsg(null);
		if (StringUtils.isEmpty(tid)) {
			setErrormsg("请选择餐台");
		}
		table = dinningTableService.get(tid);
		if (table == null) {
			setErrormsg("选择的餐台不存在或已被删除");
		}
		try {
			data = orderService.findByTable(tid);
			uuid = data.getUuid();
			details = orderService.getDetail(uuid);
		} catch (Exception ex) {
			setErrormsg("订单不存在");
			data = new Orders();
			return "error";
		}
		return Action.SUCCESS;
	}

	public String editOk() throws Exception {
		setErrormsg(null);
		try {
				orderService.pay(uuid, data.getRprice());	
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	/**
	 * @return the hotel
	 */
	public Orders getData() {
		return data;
	}

	/**
	 * @param hotel
	 *            the hotel to set
	 */
	public void setData(Orders data) {
		this.data = data;
	}

	/**
	 * @return the hotelService
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setOrderService(OrderService hotelService) {
		this.orderService = hotelService;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public DinningTable getTable() {
		return table;
	}

	public void setTable(DinningTable table) {
		this.table = table;
	}

	/**
	 * @return the hotelService
	 */
	public DinningTableService getDinningTableService() {
		return dinningTableService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setDinningTableService(DinningTableService hotelService) {
		this.dinningTableService = hotelService;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public String getRprice() {
		return rprice;
	}

	public void setRprice(String rprice) {
		this.rprice = rprice;
	}
}
