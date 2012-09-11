package com.ustcsoft.gs.myerp.webui.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;
import com.ustcsoft.gs.myerp.webui.food.Food;
import com.ustcsoft.gs.myerp.webui.food.FoodService;
import com.ustcsoft.gs.myerp.webui.table.DinningTable;
import com.ustcsoft.gs.myerp.webui.table.DinningTableService;

@Service(value = "orderAction")
public class OrderAction extends AbstractAction<Orders> {
	private SearchCondition condition;

	private String tid;
	private DinningTable table;
	private List<OrderDetail> details;
	private String fids;
	private String dids;

	@Autowired
	private OrderService orderService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private DinningTableService dinningTableService;

	protected int doCount() throws Exception {
		return orderService.count(tid, this.condition);
	}

	protected List<Orders> doSearch() throws Exception {
		return orderService.list(tid, this.condition, paging);
	}

	protected void preList() throws Exception {
		setErrormsg(null);
		if (StringUtils.isEmpty(tid)) {
			throw new Exception("请选择餐台");
		}
		table = dinningTableService.get(tid);
		if (table == null) {
			throw new Exception("选择的餐台不存在或已被删除");
		}
	}

	public String edit() throws Exception {
		setErrormsg(null);

		if ("foodSelectBack".equals(actionType)) {
			data = (Orders) ActionContext.getContext().getSession()
					.get(MyErpConstant.SESSION_ORDER_INFO);
			uuid = data.getUuid();
			tid = data.getTid();
			details = (List<OrderDetail>) ActionContext.getContext()
					.getSession()
					.get(MyErpConstant.SESSION_ORDER_DETAIL_INFO + uuid);
			return Action.SUCCESS;
		} else {
			removeSessionInfo();
		}
		if (StringUtils.isEmpty(tid)) {
			setErrormsg("请选择餐台");
		}
		table = dinningTableService.get(tid);
		if (table == null) {
			setErrormsg("选择的餐台不存在或已被删除");
		}
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		// LoginInfo l = (LoginInfo)
		// session.get(MyErpConstant.SESSION_LOGIN_INFO);
		// foods = foodService.list(l.getHid(), null, getPaging());

		data = orderService.findByTable(tid);
		if (data != null) {
			uuid = data.getUuid();
			details = orderService.getDetail(uuid);
		} else {
			uuid = "tmp".concat(UUID.randomUUID().toString());
			setActionType(MyErpConstant.ACTION_NEW);
			data = new Orders();
			data.setUuid(uuid);
			data.setState("预订中");
			// data.setHid(l.getHid());
			data.setTid(tid);
			details = new ArrayList<OrderDetail>();
		}
		Map<String, Object> s = ActionContext.getContext().getSession();
		s.put(MyErpConstant.SESSION_ORDER_DETAIL_INFO + uuid, details);
		return Action.SUCCESS;
	}

	private void removeSessionInfo() {
		Map<String, Object> s = ActionContext.getContext().getSession();
		for (Map.Entry<String, Object> entry : s.entrySet()) {
			if (entry.getKey().startsWith(
					MyErpConstant.SESSION_ORDER_DETAIL_INFO + uuid)) {
				s.remove(entry.getKey());
			} else if (entry.getKey().startsWith(
					MyErpConstant.SESSION_ORDER_INFO)) {
				s.remove(entry.getKey());
			}
		}
	}

	public String foodSelect() throws Exception {
		ActionContext.getContext().getSession()
				.put(MyErpConstant.SESSION_ORDER_INFO, data);
		setFCounts();
		return Action.SUCCESS;
	}

	public String foodSelectOk() throws Exception {
		// if (StringUtils.isEmpty(fids)) {
		// setError
		// }
		data = (Orders) ActionContext.getContext().getSession()
				.get(MyErpConstant.SESSION_ORDER_INFO);
		uuid = data.getUuid();
		tid = data.getTid();
		List<OrderDetail> sDetails = getSDetails(uuid);
		if (!StringUtils.isEmpty(fids)) {
			List<Food> selectFoods = foodService.gets(fids.split(","));
			for (Food f : selectFoods) {
				OrderDetail detail = null;
				for (OrderDetail sDetail : sDetails) {
					if (sDetail.getFoodId().equals(f.getUuid())) {
						detail = sDetail;
						break;
					}
				}
				if (detail == null) {
					detail = new OrderDetail();
					detail.setUuid(UUID.randomUUID().toString());
					detail.setOid(uuid);
					detail.setFoodId(f.getUuid());
					// detail.setFoodName(f.getName());
					// detail.setUnitName(f.getUnit());
					detail.setUnit(1);
					// detail.setDescription("");
					detail.setFood(f);
					sDetails.add(detail);
				} else {
					detail.setUnit(detail.getUnit() + 1);
				}
			}
		}
		details = sDetails;
		return Action.SUCCESS;
	}

	public String foodSelectRemove() throws Exception {
		if (StringUtils.isEmpty(uuids)) {
			setErrormsg("请选择菜品");
			return Action.INPUT;
		}
		List<OrderDetail> sDetails = getSDetails(uuid);
		for (String did : dids.split(",")) {
			int index = 0;
			for (; index < sDetails.size(); index++) {
				if (sDetails.get(index).getUuid().equals(did)) {
					sDetails.remove(index);
					break;
				}
			}
		}
		details = sDetails;
		return Action.SUCCESS;
	}

	public String editOk() throws Exception {
		setErrormsg(null);
		try {
			setFCounts();
			orderService.save(data, getSDetails(uuid));
			ActionContext.getContext().getSession()
					.remove(MyErpConstant.SESSION_ORDER_DETAIL_INFO + uuid);
			ActionContext.getContext().getSession()
					.remove(MyErpConstant.SESSION_ORDER_INFO);
			// if (MyErpConstant.ACTION_NEW.equals(actionType)) {
			// data.setState("预订中");
			// data.setTid(tid);
			// // orderService.add(data);
			// } else if (MyErpConstant.ACTION_EDIT.equals(actionType)) {
			// // orderService.update(data);
			// } else if (MyErpConstant.ACTION_DELETE.equals(actionType)) {
			// // orderService.add(data);
			// }
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	private List<OrderDetail> getSDetails(String oid) {
		Map<String, Object> s = ActionContext.getContext().getSession();
		List<OrderDetail> sDetails = (List<OrderDetail>) s
				.get(MyErpConstant.SESSION_ORDER_DETAIL_INFO + oid);
		if (sDetails == null) {
			sDetails = new ArrayList<OrderDetail>();
			s.put(MyErpConstant.SESSION_ORDER_DETAIL_INFO + oid, sDetails);
		}
		return sDetails;
	}

	private void setFCounts() {
		HttpServletRequest req = ServletActionContext.getRequest();
		List<OrderDetail> sDetails = getSDetails(uuid);
		for (OrderDetail detail : sDetails) {
			detail.setUnit(Integer.parseInt(req.getParameter("count"
					+ detail.getUuid())));
		}
	}

	public String delete() throws Exception {
		setErrormsg(null);
		try {
			orderService.delete(getUuids().split(","));
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
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
	 * @return the datas
	 */
	public List<Orders> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<Orders> datas) {
		this.datas = datas;
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

	/**
	 * @return the condition
	 */
	public SearchCondition getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(SearchCondition condition) {
		this.condition = condition;
	}

	/**
	 * @return the hotelService
	 */
	public FoodService getFoodService() {
		return foodService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setFoodService(FoodService hotelService) {
		this.foodService = hotelService;
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

	// public List<Food> getFoods() {
	// return foods;
	// }
	//
	// public void setFoods(List<Food> foods) {
	// this.foods = foods;
	// }

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

	public String getFids() {
		return fids;
	}

	public void setFids(String fids) {
		this.fids = fids;
	}
}
