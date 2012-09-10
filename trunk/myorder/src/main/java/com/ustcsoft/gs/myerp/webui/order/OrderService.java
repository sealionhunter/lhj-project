/**
 * 
 */
package com.ustcsoft.gs.myerp.webui.order;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

/**
 * @author hjliang
 * 
 */
public interface OrderService {
	public List<Orders> list(String hid, SearchCondition condition,
			Paging paging) throws Exception;

	public int count(String tid, SearchCondition condition) throws Exception;

	public Orders get(String id) throws Exception;

	public void save(Orders table, List<OrderDetail> details) throws Exception;

	public void add(Orders table, List<OrderDetail> details) throws Exception;

	public void update(Orders table, List<OrderDetail> details)
			throws Exception;

	public void delete(String[] id) throws Exception;

	public List<OrderDetail> getDetail(String oid) throws Exception;
	//
	// public void setDetail(String oid, List<OrderDetail> sDetails)
	// throws Exception;

}
