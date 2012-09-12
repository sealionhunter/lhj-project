package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcsoft.gs.myerp.webui.common.Paging;
import com.ustcsoft.gs.myerp.webui.hotel.Hotel;
import com.ustcsoft.gs.myerp.webui.hotel.HotelDao;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private HotelDao hotelDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserInfo> list(SearchCondition condition, Paging paging)
			throws Exception {
		// TODO Auto-generated method stub
		return userDao.list(condition, paging);
	}

	@Override
	public int count(SearchCondition condition) throws Exception {
		// TODO Auto-generated method stub
		return userDao.count(condition);
	}

	@Override
	public UserInfo get(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return userDao.get(uuid);
	}

	@Override
	public String add(UserInfo data, boolean createHotel,String hname) throws Exception {
		if (userDao.get(data.getUid()) != null) {
			throw new Exception("指定的用户已存在！");
		}
		if (createHotel) {
			Hotel h = new Hotel();
			h.setName(hname);
			String hid = hotelDao.add(h);
			data.setHid(hid);
		}
		return userDao.add(data);
	}

	@Override
	public void update(UserInfo data) throws Exception {
		userDao.update(data);
	}

	@Override
	public void delete(String[] split) throws Exception {
		userDao.delete(split);

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
