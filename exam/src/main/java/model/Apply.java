package model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the apply database table.
 * 
 */
public class Apply implements Serializable {
    private static final long serialVersionUID = 1L;

    private ApplyPK id;

    private Date createDate;

    private int state;

    private String reason;

    private Date udpateDate;

    private String idCardNo;

    private String applyUserName;
    
    private String applyUserHomeTown;

    private String applyOfficeName;

    private String applyOfficeCode;

    private String applyOfficeDescrip;

    private String applyOfficeMajor;

    private String applyOfficeDegree;

    private String applyDepartName;

    private String aplyUserPolitical;

    private Integer applyExamId;

    private String applyExamPosition;

    /**
     * @return the aplyUserPolitical
     */
    public String getAplyUserPolitical() {
        return aplyUserPolitical;
    }

    /**
     * @param aplyUserPolitical
     *            the aplyUserPolitical to set
     */
    public void setAplyUserPolitical(String aplyUserPolitical) {
        this.aplyUserPolitical = aplyUserPolitical;
    }

    public Apply() {
    }

    /**
     * @return the applyDepartName
     */
    public String getApplyDepartName() {
        return applyDepartName;
    }

    /**
     * @param applyDepartName
     *            the applyDepartName to set
     */
    public void setApplyDepartName(String applyDepartName) {
        this.applyDepartName = applyDepartName;
    }

    public ApplyPK getId() {
        return this.id;
    }

    public void setId(ApplyPK id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getUdpateDate() {
        return this.udpateDate;
    }

    public void setUdpateDate(Date udpateDate) {
        this.udpateDate = udpateDate;
    }

    /**
     * @return the idCardNo
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * @param idCardNo
     *            the idCardNo to set
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    /**
     * @return the applyUserName
     */
    public String getApplyUserName() {
        return applyUserName;
    }

    /**
     * @param applyUserName
     *            the applyUserName to set
     */
    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    /**
     * @return the applyOfficeName
     */
    public String getApplyOfficeName() {
        return applyOfficeName;
    }

    /**
     * @param applyOfficeName
     *            the applyOfficeName to set
     */
    public void setApplyOfficeName(String applyOfficeName) {
        this.applyOfficeName = applyOfficeName;
    }

    /**
     * @return the applyOfficeCode
     */
    public String getApplyOfficeCode() {
        return applyOfficeCode;
    }

    /**
     * @param applyOfficeCode
     *            the applyOfficeCode to set
     */
    public void setApplyOfficeCode(String applyOfficeCode) {
        this.applyOfficeCode = applyOfficeCode;
    }

    /**
     * @return the applyOfficeMajor
     */
    public String getApplyOfficeMajor() {
        return applyOfficeMajor;
    }

    /**
     * @param applyOfficeMajor
     *            the applyOfficeMajor to set
     */
    public void setApplyOfficeMajor(String applyOfficeMajor) {
        this.applyOfficeMajor = applyOfficeMajor;
    }

    /**
     * @return the applyOfficeDegree
     */
    public String getApplyOfficeDegree() {
        return applyOfficeDegree;
    }

    /**
     * @param applyOfficeDegree
     *            the applyOfficeDegree to set
     */
    public void setApplyOfficeDegree(String applyOfficeDegree) {
        this.applyOfficeDegree = applyOfficeDegree;
    }

    /**
     * @return the applyOfficeDescrip
     */
    public String getApplyOfficeDescrip() {
        return applyOfficeDescrip;
    }

    /**
     * @param applyOfficeDescrip
     *            the applyOfficeDescrip to set
     */
    public void setApplyOfficeDescrip(String applyOfficeDescrip) {
        this.applyOfficeDescrip = applyOfficeDescrip;
    }

    /**
     * @return the applyExamId
     */
    public Integer getApplyExamId() {
        return applyExamId;
    }

    /**
     * @param applyExamId
     *            the applyExamId to set
     */
    public void setApplyExamId(Integer applyExamId) {
        this.applyExamId = applyExamId;
    }

    /**
     * @return the applyExamPosition
     */
    public String getApplyExamPosition() {
        return applyExamPosition;
    }

    /**
     * @param applyExamPosition
     *            the applyExamPosition to set
     */
    public void setApplyExamPosition(String applyExamPosition) {
        this.applyExamPosition = applyExamPosition;
    }

    /**
     * @return the applyUserHomeTown
     */
    public String getApplyUserHomeTown() {
        return applyUserHomeTown;
    }

    /**
     * @param applyUserHomeTown the applyUserHomeTown to set
     */
    public void setApplyUserHomeTown(String applyUserHomeTown) {
        this.applyUserHomeTown = applyUserHomeTown;
    }
}