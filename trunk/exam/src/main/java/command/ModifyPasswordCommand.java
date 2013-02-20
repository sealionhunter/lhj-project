package command;


public class ModifyPasswordCommand {
	private String idCardNo;
	private String idCardNoError;
	private String oldPassword;
	private String password;
	private String rePassword;
	private String rePasswordError;
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getIdCardNoError() {
		return idCardNoError;
	}
	public void setIdCardNoError(String idCardNoError) {
		this.idCardNoError = idCardNoError;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getRePasswordError() {
		return rePasswordError;
	}
	public void setRePasswordError(String rePasswordError) {
		this.rePasswordError = rePasswordError;
	}
}