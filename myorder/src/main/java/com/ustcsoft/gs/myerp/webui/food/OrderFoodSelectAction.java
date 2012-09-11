package com.ustcsoft.gs.myerp.webui.food;

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
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;
import com.ustcsoft.gs.myerp.webui.order.OrderDetail;
import com.ustcsoft.gs.myerp.webui.order.OrderService;

@Service(value = "orderFoodSelectAction")
public class OrderFoodSelectAction extends AbstractAction<Food> {
	private SearchCondition condition;

	private String oid;
	private List<OrderDetail> details;
	private String fids;

	@Autowired
	private OrderService orderService;
	@Autowired
	private FoodService foodService;

	protected List<Food> doSearch() throws Exception {
		LoginInfo l = getLoginInfo();
		return foodService.list(l.getHid(), this.condition, paging);
	}

	protected int doCount() throws Exception {
		LoginInfo l = getLoginInfo();
		return foodService.count(l.getHid(), this.condition);
	}

	protected void preList() throws Exception {
		setErrormsg(null);
		if (StringUtils.isEmpty(oid)) {
			throw new Exception("请选择订单");
		}
		if ("add".equals(actionType)) {
			add();
		} else if ("remove".equals(actionType)) {
			remove();
			// } else if ("remove".equals(actionType)) {
			// remove();
		} else if (StringUtils.isEmpty(actionType)) {
			List<OrderDetail> dbDetails = orderService.getDetail(oid);
			setSDetails(dbDetails);
			details = dbDetails;
		} else if (StringUtils.isEmpty(actionType)) {
			// first last next back
			details = getSDetails();
		}
	}

	private void setSDetails(List<OrderDetail> dbDetails) {
		Map<String, Object> s = ActionContext.getContext().getSession();
		s.put(MyErpConstant.SESSION_ORDER_DETAIL_INFO + oid, dbDetails);
	}

	@SuppressWarnings("unchecked")
	private List<OrderDetail> getSDetails() {
		Map<String, Object> s = ActionContext.getContext().getSession();
		List<OrderDetail> sDetails = (List<OrderDetail>) s
				.get(MyErpConstant.SESSION_ORDER_DETAIL_INFO + oid);
		if (sDetails == null) {
			sDetails = new ArrayList<OrderDetail>();
			s.put(MyErpConstant.SESSION_ORDER_DETAIL_INFO + oid, sDetails);
		}
		return sDetails;
	}

	public void add() throws Exception {
		if (StringUtils.isEmpty(fids)) {
			throw new Exception("请选择菜品");
		}
		List<Food> selectFoods = foodService.gets(fids.split(","));
		List<OrderDetail> sDetails = getSDetails();
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
				detail.setOid(getOid());
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
		details = sDetails;
		// return Action.SUCCESS;
	}

	public String remove() throws Exception {
		if (StringUtils.isEmpty(uuids)) {
			setErrormsg("请选择菜品");
			return Action.INPUT;
		}
		List<OrderDetail> sDetails = getSDetails();
		for (String fid : uuids.split(",")) {
			int index = 0;
			for (; index < sDetails.size(); index++) {
				if (sDetails.get(index).getUuid().equals(fid)) {
					sDetails.remove(index);
					break;
				}
			}
		}
		details = sDetails;
		return Action.SUCCESS;
	}

	public String selectOK() throws Exception {
		if (StringUtils.isEmpty(fids)) {
			setErrormsg("请选择菜品");
			return Action.INPUT;
		}
		HttpServletRequest req = ServletActionContext.getRequest();
		List<OrderDetail> sDetails = getSDetails();
		for (OrderDetail detail : sDetails) {
			detail.setUnit(Integer.parseInt(req.getParameter("count"
					+ detail.getUuid())));
		}
		return Action.SUCCESS;
	}

	public String foodSelect() throws Exception {

		return Action.SUCCESS;
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

	public String getOid() {
		return oid;
	}

	public void setOid(String tid) {
		this.oid = tid;
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
