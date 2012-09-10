package com.ustcsoft.gs.myerp.webui.order;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

public interface OrderDao {
	public List<Orders> list(String hid, SearchCondition condition,
			Paging paging) throws Exception;

	public Orders get(String id) throws Exception;

	public String add(Orders table) throws Exception;

	public void delete(String id) throws Exception;

	public void update(Orders table) throws Exception;

	public int count(String tid, SearchCondition condition) throws Exception;

	public List<OrderDetail> getDetail(String oid) throws Exception;

	public void setDetail(String oid, List<OrderDetail> sDetails)
			throws Exception;
}
