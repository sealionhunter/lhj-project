package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

@Service(value = "userAction")
public class UserAction extends AbstractAction<UserInfo> {

	private SearchCondition condition;

	@Autowired
	private UserService userService;
	private boolean createHotel;
	private String hname;

	protected List<UserInfo> doSearch() throws Exception {
		return userService.list(this.condition, paging);
	}

	protected int doCount() throws Exception {
		return userService.count(this.condition);
	}

	public String edit() {
		setErrormsg(null);
		data = null;
		hname = null;
		createHotel = false;
		try {
			if (uuid != null && !"".equals(uuid)) {
				data = userService.get(getUuid());
			} else {
				if ("profile".equals(actionType)) {
					LoginInfo l = getLoginInfo();
					data = userService.get(l.getUid());
				}
			}
		} catch (Exception e) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		if (data == null) {
			data = new UserInfo();
		}
		return Action.SUCCESS;
	}

	public String editOk() {
		setErrormsg(null);
		try {
			if (MyErpConstant.ACTION_NEW.equals(actionType)) {
				if (createHotel && StringUtils.isEmpty(hname)) {
					setErrormsg("请指定餐厅名称！");
					return Action.INPUT;
				}
				uuid = userService.add(data, createHotel, hname);
				if (!createHotel) {
					return "userHotelSelect";
				}
			} else {
				userService.update(data);
			}
		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
			return Action.INPUT;
		}
		if (!getLoginInfo().isAdmin()) {
			return "hotelProperty";
		}
		return Action.SUCCESS;
	}

	public String delete() {
		setErrormsg(null);
		try {
			if (!StringUtils.isEmpty(uuids)) {
				userService.delete(uuids.split(","));
			}
		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	/**
	 * @return the data
	 */
	public UserInfo getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(UserInfo data) {
		this.data = data;
	}

	/**
	 * @return the datas
	 */
	public List<UserInfo> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<UserInfo> datas) {
		this.datas = datas;
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
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isCreateHotel() {
		return createHotel;
	}

	public void setCreateHotel(boolean createHotel) {
		this.createHotel = createHotel;
	}

	/**
	 * @return the hname
	 */
	public String getHname() {
		return hname;
	}

	/**
	 * @param hname
	 *            the hname to set
	 */
	public void setHname(String hname) {
		this.hname = hname;
	}

}
