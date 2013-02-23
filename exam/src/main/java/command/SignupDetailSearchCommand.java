package command;

public class SignupDetailSearchCommand {
    private Integer deptId = -1;
    private Integer postId = -1;
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
     * @return the politicalCode
     */
    public Integer getPoliticalCode() {
        return politicalCode;
    }

    /**
     * @param politicalCode
     *            the politicalCode to set
     */
    public void setPoliticalCode(Integer politicalCode) {
        this.politicalCode = politicalCode;
    }
}
