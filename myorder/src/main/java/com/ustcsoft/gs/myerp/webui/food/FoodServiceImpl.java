package com.ustcsoft.gs.myerp.webui.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "foodService")
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodDao foodDao;

	@Override
	public List<Food> list(String hid, SearchCondition condition, Paging paging)
			throws Exception {
		return foodDao.list(hid, condition, paging);
	}

	public int count(String hid, SearchCondition condition) throws Exception {
		return foodDao.count(hid, condition);
	}

	@Override
	public Food get(String id) throws Exception {
		return foodDao.get(id);
	}

	@Override
	@Transactional
	public void add(Food table) throws Exception {
		foodDao.add(table);
	}

	@Override
	@Transactional
	public void update(Food table) throws Exception {
		foodDao.update(table);

	}

	@Override
	@Transactional
	public void delete(String[] id) throws Exception {
		foodDao.delete(id);

	}

	@Override
	public List<Food> gets(String[] ids) throws Exception {
		return foodDao.gets(ids);
	}

	/**
	 * @return the hotelDao
	 */
	public FoodDao getFoodDao() {
		return foodDao;
	}

	/**
	 * @param hotelDao
	 *            the hotelDao to set
	 */
	public void setFoodDao(FoodDao hotelDao) {
		this.foodDao = hotelDao;
	}

}
