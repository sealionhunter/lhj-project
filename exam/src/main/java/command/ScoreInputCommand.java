package command;

public class ScoreInputCommand {
    private String idCardNo;
    private String admissionId;
    private String score;
    private String submitFlag;

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
     * @return the admissionId
     */
    public String getAdmissionId() {
        return admissionId;
    }

    /**
     * @param admissionId
     *            the admissionId to set
     */
    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    /**
     * @return the score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * @return the submitFlag
     */
    public String getSubmitFlag() {
        return submitFlag;
    }

    /**
     * @param submitFlag
     *            the submitFlag to set
     */
    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }
}
