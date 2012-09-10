package com.ustcsoft.gs.myerp.webui.hotel;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

public interface HotelDao {
	public Hotel get(String hid) throws Exception;

	public String add(Hotel hotel) throws Exception;

	public void delete(String[] ids) throws Exception;

	public void update(Hotel hotel) throws Exception;

	public List<Hotel> list(SearchCondition condition, Paging paging)
			throws Exception;

	public int count(SearchCondition condition) throws Exception;
}
