package com.ustcsoft.gs.myerp.webui.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "dinningTableService")
public class DinningTableServiceImpl implements DinningTableService {

	@Autowired
	private DinningTableDao dinningTableDao;

	@Override
	public List<DinningTable> list(String hid, SearchCondition condition,
			Paging paging) throws Exception {
		return dinningTableDao.list(hid, condition, paging);
	}

	public int count(String hid, SearchCondition condition) throws Exception {

		return dinningTableDao.count(hid, condition);
	}

	@Override
	public DinningTable get(String id) throws Exception {
		return dinningTableDao.get(id);
	}

	@Override
	@Transactional
	public void add(DinningTable table) throws Exception {
		dinningTableDao.add(table);
	}

	@Override
	@Transactional
	public void update(DinningTable table) throws Exception {
		dinningTableDao.update(table);

	}

	@Override
	@Transactional
	public void delete(String[] id) throws Exception {
		dinningTableDao.delete(id);

	}

	/**
	 * @return the hotelDao
	 */
	public DinningTableDao getDinningTableDao() {
		return dinningTableDao;
	}

	/**
	 * @param hotelDao
	 *            the hotelDao to set
	 */
	public void setDinningTableDao(DinningTableDao hotelDao) {
		this.dinningTableDao = hotelDao;
	}

}
