package com.ustcsoft.gs.myerp.webui.hotel;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

@Service(value = "hotelAction")
public class HotelAction extends AbstractAction<Hotel> {

	private String uuid;

	private File imgFile;
	private String imgFileFileName;
	private String imgFileContentType;

	@Autowired
	private HotelService hotelService;
	private SearchCondition condition;

	protected List<Hotel> doSearch() throws Exception {
		return hotelService.list(this.condition, paging);
	}

	protected int doCount() throws Exception {
		return hotelService.count(this.condition);
	}

	public String property() {
		setErrormsg(null);
		data = null;
		LoginInfo l = getLoginInfo();
		if (!StringUtils.isEmpty(l.getHid())) {
			try {
				data = hotelService.get(l.getHid());
			} catch (Exception e) {
				setErrormsg("发生了未知错误,请联系系统管理员");
			}
		}
		if (data == null) {
			setErrormsg("餐厅信息未指定！您不能使用本系统。请与管理员联系。");
			return "logout";
		}
		if (!l.isAdmin() && StringUtils.isEmpty(data.getName())) {
			setActionType(MyErpConstant.ACTION_EDIT);
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String edit() {
		imgFileFileName = null;
		setErrormsg(null);
		data = null;

		setActionType(MyErpConstant.ACTION_NEW);
		LoginInfo l = getLoginInfo();
		if (!l.isAdmin()) {
			uuid = l.getHid();
		}
		if (uuid != null && !"".equals(uuid)) {
			try {
				data = hotelService.get(getUuid());

			} catch (Exception e) {
				setErrormsg("发生了未知错误,请联系系统管理员");
			}
		}
		if (data == null) {
			setActionType(MyErpConstant.ACTION_NEW);
			// LoginInfo l = getLoginInfo();
			data = new Hotel();
		} else {
			setActionType(MyErpConstant.ACTION_EDIT);
		}
		return Action.SUCCESS;
	}

	public String editOk() {
		setErrormsg(null);
		try {
			if (!MyErpConstant.ACTION_DELETE.equals(actionType)
					&& imgFileFileName != null && !imgFileFileName.equals("")) {
				String path = ServletActionContext.getServletContext()
						.getRealPath(".");
				String imgUrl = "uploadedImages/"
						+ UUID.randomUUID().toString() + "/" + imgFileFileName;
				File destFile = new File(path, imgUrl);
				FileUtils.copyFile(imgFile, destFile);
				data.setImgUrl(imgUrl);
				imgFileFileName = null;
			}
			if (MyErpConstant.ACTION_NEW.equals(actionType)) {
				hotelService.add(data);
			} else if (MyErpConstant.ACTION_EDIT.equals(actionType)) {
				hotelService.update(data);
			}
		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
			return Action.INPUT;
		}

		LoginInfo l = getLoginInfo();
		if (!l.isAdmin()) {
			return "property";
		}
		return Action.SUCCESS;
	}

	public String delete() {
		setErrormsg(null);
		try {
			if (!StringUtils.isEmpty(uuids)) {
				hotelService.delete(uuids.split(","));
			}
		} catch (Exception ex) {
			setErrormsg("发生了未知错误,请联系系统管理员");
		}
		return Action.SUCCESS;
	}

	/**
	 * @return the hotel
	 */
	public Hotel getData() {
		return data;
	}

	/**
	 * @param hotel
	 *            the hotel to set
	 */
	public void setData(Hotel hotel) {
		this.data = hotel;
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
	 * @return the imgFile
	 */
	public File getImgFile() {
		return imgFile;
	}

	/**
	 * @param imgFile
	 *            the imgFile to set
	 */
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	/**
	 * @return the imgFileFileName
	 */
	public String getImgFileFileName() {
		return imgFileFileName;
	}

	/**
	 * @param imgFileFileName
	 *            the imgFileFileName to set
	 */
	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	/**
	 * @return the imgFileContentType
	 */
	public String getImgFileContentType() {
		return imgFileContentType;
	}

	/**
	 * @param imgFileContentType
	 *            the imgFileContentType to set
	 */
	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
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
}
