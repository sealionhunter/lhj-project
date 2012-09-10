package com.ustcsoft.gs.myerp.webui.food;

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
import com.ustcsoft.gs.myerp.webui.common.MyHotelUtils;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

@Service(value = "foodAction")
public class FoodAction extends AbstractAction<Food> {

	private SearchCondition condition;

	private File imgFile;
	private String imgFileFileName;
	private String imgFileContentType;

	@Autowired
	private FoodService foodService;

	protected List<Food> doSearch() throws Exception {
		LoginInfo l = MyHotelUtils.getLoginInfo();
		return foodService.list(l.getHid(), this.condition, paging);
	}

	protected int doCount() throws Exception {
		LoginInfo l = MyHotelUtils.getLoginInfo();
		return foodService.count(l.getHid(), this.condition);
	}

	public String edit() throws Exception {
		imgFileFileName = null;
		setErrormsg(null);
		data = null;
		if (uuid != null && !"".equals(uuid)) {
			data = foodService.get(getUuid());
		}
		if (data == null) {
			setActionType(MyErpConstant.ACTION_NEW);
			LoginInfo l = MyHotelUtils.getLoginInfo();
			data = new Food();
			data.setHid(l.getHid());
		} else {
			setActionType(MyErpConstant.ACTION_EDIT);
		}
		return Action.SUCCESS;
	}

	public String editOk() throws Exception {
		setErrormsg(null);
		try {
			if (imgFileFileName != null && !imgFileFileName.equals("")) {
				String path = ServletActionContext.getServletContext()
						.getRealPath(".");
				String imgUrl = "uploadedImages/"
						+ UUID.randomUUID().toString() + "/" + imgFileFileName;
				File destFile = new File(path, imgUrl);
				FileUtils.copyFile(imgFile, destFile);
				data.setImgUrl(imgUrl);
			}
			if (MyErpConstant.ACTION_NEW.equals(actionType)) {
				foodService.add(data);
			} else if (MyErpConstant.ACTION_EDIT.equals(actionType)) {
				foodService.update(data);
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String delete() throws Exception {
		setErrormsg(null);
		try {
			if (!StringUtils.isEmpty(uuids)) {
				foodService.delete(uuids.split(","));
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
		}
		return Action.SUCCESS;
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
	public FoodService getFoodService() {
		return foodService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setFoodService(FoodService hotelService) {
		this.foodService = hotelService;
	}

	/**
	 * @return the condition
	 */
	public SearchCondition getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(SearchCondition condition) {
		this.condition = condition;
	}

	/**
	 * @return the data
	 */
	public Food getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Food data) {
		this.data = data;
	}
}
