package command;

import model.Apply;
import model.User;

public class StatusSearchCommand {
    private String idCardNo;
    private String password;

    private User user;

    private Apply apply;
    
    private boolean showDetail = false;

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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the apply
     */
    public Apply getApply() {
        return apply;
    }

    /**
     * @param apply
     *            the apply to set
     */
    public void setApply(Apply apply) {
        this.apply = apply;
    }

    /**
     * @return the showDetail
     */
    public boolean isShowDetail() {
        return showDetail;
    }

    /**
     * @param showDetail the showDetail to set
     */
    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }
}
