package command;

import java.util.List;

import model.City;
import model.Depart;
import model.Exam;
import model.Master;
import model.Office;

public class RegistCommand {
	private String editFlg = "0";
	private Integer userId;
	private String name;
	private String idCardNo;
	private String idCardNoError;
	private String reIdCardNo;
	private String password;
	private String rePassword;
	private String rePasswordError;
	private String sexCode;
	private String nationCode;
	private String homeTown;
	private int birthdayYear;
	private int birthdayMonth;
	private String politicalCode;
	private String isMarried;
	private String degree;
	private String identity;
	private String userSchool;
	private String userMajor;
	private String graduateYear;
	private String graduateMonth;
	private String workYears;
	private String computerSkill;
	private String languageSkill;
	private String height;
	private String startTime;
	private String endTime;
	

	private String trainingExp;
	private String workExp;
	private String socialRel;

	private String registExamCode;
	private String registExamName;
	private String registExamLocation;
	private String applyCityId;
	private String applyDeptId;
	private String applyDeptName;

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	private String applyPostId;
	private String applyPostName;
	private String applyPostCode;

	public String getApplyPostCode() {
		return applyPostCode;
	}

	public void setApplyPostCode(String applyPostCode) {
		this.applyPostCode = applyPostCode;
	}

	public String getApplyPostName() {
		return applyPostName;
	}

	public void setApplyPostName(String applyPostName) {
		this.applyPostName = applyPostName;
	}

	private String telephone;

	private List<Master> sexs;
	private List<Master> identities;
	private List<Master> politicalCodes;
	private List<Master> nationCodes;
	private List<Master> marriedStates;
	private List<Depart> departs;
	private List<Office> offices;
	private List<City> cities;
	private List<Exam> exams;
	private byte[] photo;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the idCardNoError
	 */
	public String getIdCardNoError() {
		return idCardNoError;
	}

	/**
	 * @param idCardNoError
	 *            the idCardNoError to set
	 */
	public void setIdCardNoError(String idCardNoError) {
		this.idCardNoError = idCardNoError;
	}

	/**
	 * @return the rePasswordError
	 */
	public String getRePasswordError() {
		return rePasswordError;
	}

	/**
	 * @param rePasswordError
	 *            the rePasswordError to set
	 */
	public void setRePasswordError(String rePasswordError) {
		this.rePasswordError = rePasswordError;
	}

	/**
	 * @return the graduateYear
	 */
	public String getGraduateYear() {
		return graduateYear;
	}

	/**
	 * @param graduateYear
	 *            the graduateYear to set
	 */
	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	/**
	 * @return the graduateMonth
	 */
	public String getGraduateMonth() {
		return graduateMonth;
	}

	/**
	 * @param graduateMonth
	 *            the graduateMonth to set
	 */
	public void setGraduateMonth(String graduateMonth) {
		this.graduateMonth = graduateMonth;
	}

	/**
	 * @return the workyears
	 */
	public String getWorkYears() {
		return workYears;
	}

	/**
	 * @param workyears
	 *            the workyears to set
	 */
	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}

	/**
	 * @return the applyDeptId
	 */
	public String getApplyDeptId() {
		return applyDeptId;
	}

	/**
	 * @param applyDeptId
	 *            the applyDeptId to set
	 */
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}

	/**
	 * @return the applyCityId
	 */
	public String getApplyCityId() {
		return applyCityId;
	}

	/**
	 * @param applyCityId
	 *            the applyCityId to set
	 */
	public void setApplyCityId(String applyCityId) {
		this.applyCityId = applyCityId;
	}

	/**
	 * @return the applyPostId
	 */
	public String getApplyPostId() {
		return applyPostId;
	}

	/**
	 * @param applyPostId
	 *            the applyPostId to set
	 */
	public void setApplyPostId(String applyPostId) {
		this.applyPostId = applyPostId;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the registExamName
	 */
	public String getRegistExamName() {
		return registExamName;
	}

	/**
	 * @param registExamName
	 *            the registExamName to set
	 */
	public void setRegistExamName(String registExamName) {
		this.registExamName = registExamName;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the reIdCardNo
	 */
	public String getReIdCardNo() {
		return reIdCardNo;
	}

	/**
	 * @param reIdCardNo
	 *            the reIdCardNo to set
	 */
	public void setReIdCardNo(String reIdCardNo) {
		this.reIdCardNo = reIdCardNo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}

	/**
	 * @param rePassword
	 *            the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	/**
	 * @return the sexCode
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 * @param sexCode
	 *            the sexCode to set
	 */
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 * @return the nationCode
	 */
	public String getNationCode() {
		return nationCode;
	}

	/**
	 * @param nationCode
	 *            the nationCode to set
	 */
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	/**
	 * @return the homeTown
	 */
	public String getHomeTown() {
		return homeTown;
	}

	/**
	 * @param homeTown
	 *            the homeTown to set
	 */
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	/**
	 * @return the birthdayYear
	 */
	public int getBirthdayYear() {
		return birthdayYear;
	}

	/**
	 * @param birthdayYear
	 *            the birthdayYear to set
	 */
	public void setBirthdayYear(int birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	/**
	 * @return the birthdayMonth
	 */
	public int getBirthdayMonth() {
		return birthdayMonth;
	}

	/**
	 * @param birthdayMonth
	 *            the birthdayMonth to set
	 */
	public void setBirthdayMonth(int birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	/**
	 * @return the politicalCode
	 */
	public String getPoliticalCode() {
		return politicalCode;
	}

	/**
	 * @param politicalCode
	 *            the politicalCode to set
	 */
	public void setPoliticalCode(String politicalCode) {
		this.politicalCode = politicalCode;
	}

	/**
	 * @return the isMarried
	 */
	public String getIsMarried() {
		return isMarried;
	}

	/**
	 * @param isMarried
	 *            the isMarried to set
	 */
	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the userSchool
	 */
	public String getUserSchool() {
		return userSchool;
	}

	/**
	 * @param userSchool
	 *            the userSchool to set
	 */
	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}

	/**
	 * @return the userMajor
	 */
	public String getUserMajor() {
		return userMajor;
	}

	/**
	 * @param userMajor
	 *            the userMajor to set
	 */
	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}

	/**
	 * @return the computerSkill
	 */
	public String getComputerSkill() {
		return computerSkill;
	}

	/**
	 * @param computerSkill
	 *            the computerSkill to set
	 */
	public void setComputerSkill(String computerSkill) {
		this.computerSkill = computerSkill;
	}

	/**
	 * @return the languageSkill
	 */
	public String getLanguageSkill() {
		return languageSkill;
	}

	/**
	 * @param languageSkill
	 *            the languageSkill to set
	 */
	public void setLanguageSkill(String languageSkill) {
		this.languageSkill = languageSkill;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
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
	 * @return the registExamCode
	 */
	public String getRegistExamCode() {
		return registExamCode;
	}

	/**
	 * @param registExamCode
	 *            the registExamCode to set
	 */
	public void setRegistExamCode(String registExamCode) {
		this.registExamCode = registExamCode;
	}

	public String getRegistExamLocation() {
		return registExamLocation;
	}

	public void setRegistExamLocation(String registExamLocation) {
		this.registExamLocation = registExamLocation;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the sexs
	 */
	public List<Master> getSexs() {
		return sexs;
	}

	/**
	 * @param sexs
	 *            the sexs to set
	 */
	public void setSexs(List<Master> sexs) {
		this.sexs = sexs;
	}

	/**
	 * @return the identities
	 */
	public List<Master> getIdentities() {
		return identities;
	}

	/**
	 * @param identities
	 *            the identities to set
	 */
	public void setIdentities(List<Master> identities) {
		this.identities = identities;
	}

	/**
	 * @return the politicalCodes
	 */
	public List<Master> getPoliticalCodes() {
		return politicalCodes;
	}

	/**
	 * @param politicalCodes
	 *            the politicalCodes to set
	 */
	public void setPoliticalCodes(List<Master> politicalCodes) {
		this.politicalCodes = politicalCodes;
	}

	/**
	 * @return the nationCodes
	 */
	public List<Master> getNationCodes() {
		return nationCodes;
	}

	/**
	 * @param nationCodes
	 *            the nationCodes to set
	 */
	public void setNationCodes(List<Master> nationCodes) {
		this.nationCodes = nationCodes;
	}

	/**
	 * @return the marriedStates
	 */
	public List<Master> getMarriedStates() {
		return marriedStates;
	}

	/**
	 * @param marriedStates
	 *            the marriedStates to set
	 */
	public void setMarriedStates(List<Master> marriedStates) {
		this.marriedStates = marriedStates;
	}

	/**
	 * @return the departs
	 */
	public List<Depart> getDeparts() {
		return departs;
	}

	/**
	 * @param departs
	 *            the departs to set
	 */
	public void setDeparts(List<Depart> departs) {
		this.departs = departs;
	}

	/**
	 * @return the offices
	 */
	public List<Office> getOffices() {
		return offices;
	}

	/**
	 * @param offices
	 *            the offices to set
	 */
	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * @param cities
	 *            the cities to set
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public String getEditFlg() {
		// TODO Auto-generated method stub
		return editFlg;
	}

	public void setEditFlg(String editFlg) {
		this.editFlg = editFlg;
	}
}