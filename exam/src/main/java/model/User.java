package model;

import java.io.Serializable;


/**
 * The persistent class for the user database table.
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private int birthdayMonth;

	private int birthdayYear;

	private String computerSkill;

	private String degree;

	private String description;

	private int graduateMonth;

	private String graduateSchool;

	private int graduateYear;

	private int height;

	private String homeTown;

	private String idCardNo;

	private String identity;

	private String languageSkill;

	private String major;

	private int married;

	private String name;

	private int nationCode;

	private String password;

	private int politicalCode;

	private int sex;

	private String telephone;

	private int workyears;

	private String trainingExp;
	private String workExp;
	private String socialRel;
    private byte[] photo;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBirthdayMonth() {
		return this.birthdayMonth;
	}

	public void setBirthdayMonth(int birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public int getBirthdayYear() {
		return this.birthdayYear;
	}

	public void setBirthdayYear(int birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public String getComputerSkill() {
		return this.computerSkill;
	}

	public void setComputerSkill(String computerSkill) {
		this.computerSkill = computerSkill;
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

	public int getGraduateMonth() {
		return this.graduateMonth;
	}

	public void setGraduateMonth(int graduateMonth) {
		this.graduateMonth = graduateMonth;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public int getGraduateYear() {
		return this.graduateYear;
	}

	public void setGraduateYear(int graduateYear) {
		this.graduateYear = graduateYear;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getHomeTown() {
		return this.homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getLanguageSkill() {
		return this.languageSkill;
	}

	public void setLanguageSkill(String languageSkill) {
		this.languageSkill = languageSkill;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getMarried() {
		return this.married;
	}

	public void setMarried(int married) {
		this.married = married;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNationCode() {
		return this.nationCode;
	}

	public void setNationCode(int nationCode) {
		this.nationCode = nationCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoliticalCode() {
		return this.politicalCode;
	}

	public void setPoliticalCode(int politicalCode) {
		this.politicalCode = politicalCode;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getWorkyears() {
		return this.workyears;
	}

	public void setWorkyears(int workyears) {
		this.workyears = workyears;
	}

    public String getTrainingExp() {
		return trainingExp;
	}

	public void setTrainingExp(String trainingExp) {
		this.trainingExp = trainingExp;
	}

	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public String getSocialRel() {
		return socialRel;
	}

	public void setSocialRel(String socialRel) {
		this.socialRel = socialRel;
	}

	/**
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

}