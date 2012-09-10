package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.hotel.Hotel;
import com.ustcsoft.gs.myerp.webui.hotel.HotelService;
import com.ustcsoft.gs.myerp.webui.hotel.SearchCondition;

@Service(value = "userHotelSelectAction")
public class UserHotelSelectAction extends AbstractAction<Hotel> {

	private Hotel hotel;
	private UserInfo user;

	private String uuid;
	private String uid;

	@Autowired
	private HotelService hotelService;
	@Autowired
	private UserService userService;
	private SearchCondition condition;

	protected List<Hotel> doSearch() throws Exception {
		return hotelService.list(this.condition, paging);
	}

	protected int doCount() throws Exception {
		return hotelService.count(this.condition);
	}
	
	protected void preList() throws Exception {
		setErrormsg(null);
		if (StringUtils.isEmpty(uid)) {
			throw new Exception("用户名为指定");
		}
		user = userService.get(uid);
		if (user == null) {
			throw new Exception("指定的用户不存在");
		}
	}

	public String selectOk() throws Exception {
		setErrormsg(null);
		try {
			if (StringUtils.isEmpty(uuid)) {
				setErrormsg("请选择酒店");
			}
			user = userService.get(uid);
			user.setHid(uuid);
			userService.update(user);
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * @param hotel
	 *            the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the hid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param hid
	 *            the hid to set
	 */
	public void setUuid(String hid) {
		this.uuid = hid;
	}

	/**
	 * @return the hotelService
	 */
	public HotelService getHotelService() {
		return hotelService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	/**
	 * @return the user
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the condition
	 */
	public SearchCondition getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(SearchCondition condition) {
		this.condition = condition;
	}
}
