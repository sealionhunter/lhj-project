package model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the Exam database table.
 * 
 */
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date applyBeginDate;
	private Date applyDeadDate;

	private Date examDate;

	private String examPosition;

	private String examTime;
	private String name;

	private int recruits;

	public Exam() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getApplyBeginDate() {
		return applyBeginDate;
	}

	public void setApplyBeginDate(Date applyBeginDate) {
		this.applyBeginDate = applyBeginDate;
	}

	public Date getApplyDeadDate() {
		return applyDeadDate;
	}

	public void setApplyDeadDate(Date applyDeadDate) {
		this.applyDeadDate = applyDeadDate;
	}

	public Date getExamDate() {
		return this.examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamPosition() {
		return this.examPosition;
	}

	public void setExamPosition(String examPosition) {
		this.examPosition = examPosition;
	}

	public String getExamTime() {
		return this.examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRecruits() {
		return this.recruits;
	}

	public void setRecruits(int recruits) {
		this.recruits = recruits;
	}

}