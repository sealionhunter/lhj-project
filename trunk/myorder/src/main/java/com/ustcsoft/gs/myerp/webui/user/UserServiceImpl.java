package com.ustcsoft.gs.myerp.webui.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcsoft.gs.myerp.webui.common.MyHotelUtils;
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
		List<UserInfo> users = userDao.list(condition, paging);
		for (UserInfo user : users) {
			if (!StringUtils.isEmpty(user.getValidTo())) {
				user.setValidToDate(new Date(Long.parseLong(MyHotelUtils
						.decode(user.getValidTo()))));
			}
		}
		return users;
	}

	@Override
	public int count(SearchCondition condition) throws Exception {
		// TODO Auto-generated method stub
		return userDao.count(condition);
	}

	@Override
	public UserInfo get(String uuid) throws Exception {
		UserInfo user = userDao.get(uuid);
		if (user != null && !StringUtils.isEmpty(user.getValidTo())) {
			user.setValidToDate(new Date(Long.parseLong(MyHotelUtils
					.decode(user.getValidTo()))));
		}
		return user;
	}

	@Override
	public String add(UserInfo data, boolean createHotel, String hname)
			throws Exception {
		if (!StringUtils.isEmpty(data.getPlainPass())) {
			data.setPassword(MyHotelUtils.encrypt(data.getPlainPass()));
		}
		if (data.getValidToDate() != null) {
			data.setValidTo(MyHotelUtils.encode(String.valueOf(data
					.getValidToDate().getTime())));
		}
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
		if (!StringUtils.isEmpty(data.getPlainPass())) {
			data.setPassword(MyHotelUtils.encrypt(data.getPlainPass()));
		}
		if (data.getValidToDate() != null) {
			data.setValidTo(MyHotelUtils.encode(String.valueOf(data
					.getValidToDate().getTime())));
		}
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
