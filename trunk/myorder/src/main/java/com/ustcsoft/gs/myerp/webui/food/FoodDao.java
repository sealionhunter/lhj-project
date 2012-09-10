package com.ustcsoft.gs.myerp.webui.food;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

public interface FoodDao {
	public List<Food> list(String hid, SearchCondition condition, Paging paging)
			throws Exception;

	public int count(String hid, SearchCondition condition) throws Exception;

	public Food get(String id) throws Exception;

	public void add(Food table) throws Exception;

	public void delete(String[] id) throws Exception;

	public void update(Food table) throws Exception;

	public List<Food> gets(String[] ids) throws Exception;
}
