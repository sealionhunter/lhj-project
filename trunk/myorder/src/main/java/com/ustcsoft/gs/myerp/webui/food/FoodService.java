/**
 * 
 */
package com.ustcsoft.gs.myerp.webui.food;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

/**
 * @author hjliang
 * 
 */
public interface FoodService {
	public List<Food> list(String hid, SearchCondition condition, Paging paging)
			throws Exception;

	public int count(String hid, SearchCondition condition) throws Exception;

	public Food get(String id) throws Exception;

	public void add(Food table) throws Exception;

	public void update(Food table) throws Exception;

	public void delete(String[] id) throws Exception;

	public List<Food> gets(String[] split) throws Exception;

}
