package com.ustcsoft.gs.myerp.webui.login;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcsoft.gs.myerp.webui.common.MyHotelUtils;
import com.ustcsoft.gs.myerp.webui.hotel.Hotel;
import com.ustcsoft.gs.myerp.webui.hotel.HotelDao;
import com.ustcsoft.gs.myerp.webui.user.UserDao;
import com.ustcsoft.gs.myerp.webui.user.UserInfo;

@Service(value = "LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private HotelDao hotelDao;

	@Override
	public LoginInfo login(String uid, String password) throws Exception {
		if (StringUtils.isEmpty(uid)) {
			throw new Exception("请输入用户名!");
		}
		if (StringUtils.isEmpty(password)) {
			throw new Exception("请输入密码!");
		}
		UserInfo u = userDao.get(uid);
		if (u == null
				|| !MyHotelUtils.encrypt(password).equals(u.getPassword())) {
			throw new Exception("用户名或密码错误");
		}

		Date now = new Date();
		if (!u.isAdmin()
				&& (u.getValidTo() == null || now.getTime() > Long
						.parseLong(MyHotelUtils.decode(u.getValidTo())))) {
			throw new Exception("您的有效期限已过，请与管理员联系！");
		}
		LoginInfo l = new LoginInfo(u.getUid(), u.getUname());
		l.setAdmin(u.isAdmin());

		String hid = u.getHid();
		if (!StringUtils.isEmpty(hid)) {
			Hotel h = hotelDao.get(hid);
			if (h != null) {
				l.setHid(h.getUuid());
				l.setHname(h.getName());
			}
		}
		return l;
	}

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
