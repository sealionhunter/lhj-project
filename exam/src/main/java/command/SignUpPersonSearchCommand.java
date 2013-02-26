package command;

public class SignUpPersonSearchCommand {
    private Integer deptId = -1;
    private Integer postId = -1;
    private String verifyUserId;
    private Integer state = -1;

    private Integer sex = -1;
    private String homeTown;
    private Integer politicalCode = -1;

    /**
     * @return the deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the postId
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * @param postId
     *            the postId to set
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(String verifyUserId) {
        this.verifyUserId = verifyUserId;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the homeTown
     */
    public String getHomeTown() {
        return homeTown;
    }

    /**
     * @param homeTown the homeTown to set
     */
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    /**
     * @return the politicalCode
     */
    public Integer getPoliticalCode() {
        return politicalCode;
    }

    /**
     * @param politicalCode the politicalCode to set
     */
    public void setPoliticalCode(Integer politicalCode) {
        this.politicalCode = politicalCode;
    }
}
