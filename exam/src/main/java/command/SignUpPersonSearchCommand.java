package command;

public class SignUpPersonSearchCommand {
    private String deptId = "-1";
    private String postId = "-1";
    private String verifyUserId;

    /**
     * @return the deptId
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the postId
     */
    public String getPostId() {
        return postId;
    }

    /**
     * @param postId
     *            the postId to set
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(String verifyUserId) {
        this.verifyUserId = verifyUserId;
    }
}
