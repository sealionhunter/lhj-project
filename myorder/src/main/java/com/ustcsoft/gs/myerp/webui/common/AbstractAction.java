package com.ustcsoft.gs.myerp.webui.common;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

public class AbstractAction<T> {
	protected String actionType;
	protected String errormsg;

	protected Paging paging;
	protected List<T> datas;
	protected T data;
	protected String uuids;
	protected String uuid;

	public String list() {
		setErrormsg(null);
		try {
			preList();
		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
			return "preList";
		}
		try {
			if ("first".equals(actionType)) {
				getPaging().first();
			} else if ("last".equals(actionType)) {
				getPaging().last();
			} else if ("back".equals(actionType)) {
				getPaging().back();
			} else if ("next".equals(actionType)) {
				getPaging().next();
			} else if ("toPage".equals(actionType)) {
				getPaging().toPage();
			} else {
				getPaging().clear();
			}
			paging.setRcount(doCount());
			datas = doSearch();
		} catch (Exception ex) {
			ex.printStackTrace();
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	protected void preList() throws Exception {

	}

	protected int doCount() throws Exception {
		return 0;
	}

	public String next() {
		try {
			getPaging().next();
			paging.setRcount(doCount());
			datas = doSearch();

		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	public String back() {
		try {
			getPaging().back();
			paging.setRcount(doCount());
			datas = doSearch();

		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	public String first() {
		try {
			getPaging().clear();
			paging.setRcount(doCount());
			datas = doSearch();

		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	public String last() {
		try {
			getPaging().last();
			paging.setRcount(doCount());
			datas = doSearch();

		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	protected List<T> doSearch() throws Exception {
		return new ArrayList<T>();
	}

	/**
	 * @return the id
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the errormsg
	 */
	public String getErrormsg() {
		return errormsg;
	}

	/**
	 * @param errormsg
	 *            the errormsg to set
	 */
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	/**
	 * @return the paging
	 */
	public Paging getPaging() {
		if (paging == null) {
			paging = new Paging();
		}
		return paging;
	}

	/**
	 * @param paging
	 *            the paging to set
	 */
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	/**
	 * @return the uuids
	 */
	public String getUuids() {
		return uuids;
	}

	/**
	 * @param uuids
	 *            the uuids to set
	 */
	public void setUuids(String uuids) {
		this.uuids = uuids;
	}

	/**
	 * @return the datas
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	protected LoginInfo getLoginInfo() {
		return MyHotelUtils.getLoginInfo();
	}
}
