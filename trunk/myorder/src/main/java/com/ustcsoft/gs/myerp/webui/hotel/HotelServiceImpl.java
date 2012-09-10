package com.ustcsoft.gs.myerp.webui.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "hotelService")
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;

	@Override
	public Hotel get(String hid) throws Exception {
		return hotelDao.get(hid);
	}

	public List<Hotel> list(SearchCondition condition, Paging paging)
			throws Exception {
		return hotelDao.list(condition, paging);
	}

	public int count(SearchCondition condition) throws Exception {
		return hotelDao.count(condition);
	}


	@Override
	@Transactional
	public String add(Hotel hotel) throws Exception {
		return hotelDao.add(hotel);
	}

	@Override
	@Transactional
	public void update(Hotel hotel) throws Exception {
		hotelDao.update(hotel);

	}

	@Override
	@Transactional
	public void delete(String[] id) throws Exception {
		hotelDao.delete(id);

	}

	/**
	 * @return the hotelDao
	 */
	public HotelDao getHotelDao() {
		return hotelDao;
	}

	/**
	 * @param hotelDao
	 *            the hotelDao to set
	 */
	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

}
