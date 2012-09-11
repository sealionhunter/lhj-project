package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;
import com.ustcsoft.gs.myerp.webui.common.MyHotelUtils;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

@Service(value = "userAction")
public class UserAction extends AbstractAction<UserInfo> {

	private SearchCondition condition;

	@Autowired
	private UserService userService;

	protected List<UserInfo> doSearch() throws Exception {
		return userService.list(this.condition, paging);
	}

	protected int doCount() throws Exception {
		return userService.count(this.condition);
	}

	public String edit() throws Exception {
		setErrormsg(null);
		data = null;
		if (uuid != null && !"".equals(uuid)) {
			data = userService.get(getUuid());
		} else {
			if ("profile".equals(actionType)) {
				LoginInfo l = MyHotelUtils.getLoginInfo();
				data = userService.get(l.getUid());
			}
		}
		if (data == null) {
			data = new UserInfo();
		}
		return Action.SUCCESS;
	}

	public String editOk() throws Exception {
		setErrormsg(null);
		try {
			if (MyErpConstant.ACTION_NEW.equals(actionType)) {
				userService.add(data);
			} else {
				userService.update(data);
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String delete() throws Exception {
		setErrormsg(null);
		try {
			if (!StringUtils.isEmpty(uuids)) {
				userService.delete(uuids.split(","));
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
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

}
