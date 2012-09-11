package com.ustcsoft.gs.myerp.webui.table;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Action;
import com.ustcsoft.gs.myerp.webui.common.AbstractAction;
import com.ustcsoft.gs.myerp.webui.common.MyErpConstant;
import com.ustcsoft.gs.myerp.webui.login.LoginInfo;

@Service(value = "dinningTableAction")
public class DinningTableAction extends AbstractAction<DinningTable> {

	private SearchCondition condition;

	private File imgFile;
	private String imgFileFileName;
	private String imgFileContentType;

	@Autowired
	private DinningTableService dinningTableService;

	protected List<DinningTable> doSearch() throws Exception {
		LoginInfo l = getLoginInfo();
		return dinningTableService.list(l.getHid(), this.condition, paging);
	}

	protected int doCount() throws Exception {
		LoginInfo l = getLoginInfo();
		return dinningTableService.count(l.getHid(), this.condition);
	}

	// public String list() throws Exception {
	// setErrormsg(null);
	// try {
	// Map<String, Object> session = ActionContext.getContext()
	// .getSession();
	// LoginInfo loginInfo = (LoginInfo) session
	// .get(MyErpConstant.SESSION_LOGIN_INFO);
	// setTables(dinningTableService.list(loginInfo.getHid(), condition));
	// } catch (Exception ex) {
	// setErrormsg(ex.getMessage());
	// return Action.ERROR;
	// }
	// return Action.SUCCESS;
	// }

	public String edit() throws Exception {
		imgFileFileName = null;
		setErrormsg(null);
		try {
			data = null;
			if (uuid != null && !"".equals(uuid)) {
				data = dinningTableService.get(getUuid());
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
		}
		if (data == null) {
			setActionType(MyErpConstant.ACTION_NEW);
			LoginInfo l = getLoginInfo();
			data = new DinningTable();
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
				data.setState("0");
				dinningTableService.add(data);
			} else if (MyErpConstant.ACTION_EDIT.equals(actionType)) {
				dinningTableService.update(data);
			}
		} catch (Exception ex) {
			setErrormsg(ex.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String delete() throws Exception {
		if (uuids == null || uuids.length() == 0) {
			setErrormsg("请选择餐桌！");
		} else {
			try {
				dinningTableService.delete(uuids.split(","));
			} catch (Exception ex) {
				setErrormsg(ex.getMessage());
			}
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
	 * @return the hotelService
	 */
	public DinningTableService getDinningTableService() {
		return dinningTableService;
	}

	/**
	 * @param hotelService
	 *            the hotelService to set
	 */
	public void setDinningTableService(DinningTableService hotelService) {
		this.dinningTableService = hotelService;
	}

	/**
	 * @return the data
	 */
	public DinningTable getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(DinningTable data) {
		this.data = data;
	}
}
