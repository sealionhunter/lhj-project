package com.ustcsoft.generalsolution.dmat.webui.dmat;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class DmatDetail {

	private Long dmatId;

	@NotNull
	private Date startDate;

	@NotNull
	private BigDecimal duration;

	@NotNull
	private String guestDeptId;

	private String guestDeptName;

	@NotNull
	private String projectId;

	private String projectName;

	@NotNull
	private String categoryId;

	private String categoryName;

	@NotNull
	private String subcategoryId;

	private String subcategoryName;

	@NotNull
	@Max(1024)
	private String details;

	@NotNull
	private String userId;

	private String userName;

	private Date updateDate;

	private String updateUserId;

	private String updateUserName;

	public LocalDate getLocalStartDate() {
		return new LocalDate(this.getStartDate().getTime());
	}
}
