package model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the office database table.
 * 
 */
public class Office implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Date applyYear;

    private String code;

    private String degree;

    private String description;

    private Integer examId;

    private String examName;

    private int limitAge;

    private String major;

    private String name;

    private int recruits;

    private Integer departId;

    private String departName;

    private String cityName;

    private int applyCount;

    private int validataCount;

    private int scale;

    public Office() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getApplyYear() {
        return this.applyYear;
    }

    public void setApplyYear(Date applyYear) {
        this.applyYear = applyYear;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExamId() {
        return this.examId;
    }

    public void setExamId(Integer examPosition) {
        this.examId = examPosition;
    }

    public int getLimitAge() {
        return this.limitAge;
    }

    public void setLimitAge(int limitAge) {
        this.limitAge = limitAge;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public Integer getDepartId() {
        return this.departId;
    }

    public void setDepartId(Integer depart) {
        this.departId = depart;
    }

    /**
     * @return the departName
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * @param departName
     *            the departName to set
     */
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     *            the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the applyCount
     */
    public int getApplyCount() {
        return applyCount;
    }

    /**
     * @param applyCount
     *            the applyCount to set
     */
    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    /**
     * @return the validataCount
     */
    public int getValidataCount() {
        return validataCount;
    }

    /**
     * @param validataCount
     *            the validataCount to set
     */
    public void setValidataCount(int validataCount) {
        this.validataCount = validataCount;
    }

    /**
     * @return the scale
     */
    public int getScale() {
        return scale;
    }

    /**
     * @param scale
     *            the scale to set
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

}