/**
 * 
 */
package com.ustcsoft.gs.myerp.webui.table;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

/**
 * @author hjliang
 * 
 */
public interface DinningTableService {
	public List<DinningTable> list(String hid, SearchCondition condition, Paging paging) throws Exception;

	public DinningTable get(String id) throws Exception;

	public void add(DinningTable table) throws Exception;

	public void update(DinningTable table) throws Exception;

	public void delete(String[] id) throws Exception;

	public int count(String hid, SearchCondition condition) throws Exception;

}
