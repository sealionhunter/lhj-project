package com.ustcsoft.generalsolution.dmat.webui.notice;

import java.io.Serializable;
import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class Notice implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7975135355792528887L;

	private String id;

	private String title;

	private String comment;

	private Date createDate;

	private String createId;

	private String updateId;

	private Date updateDate;

	private String updateUserName;

}
