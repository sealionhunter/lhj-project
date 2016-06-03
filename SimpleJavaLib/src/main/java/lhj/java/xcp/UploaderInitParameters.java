/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

/**
 * @author hjliang
 *
 */
public class UploaderInitParameters {
    private String tempPath;
    private boolean multipleUpload;
    private int maxRetryCount = 0;
    private FtpParameters ftpParameters;

    /**
     * @return the tempPath
     */
    public String getTempPath() {
        return tempPath;
    }

    /**
     * @param tempPath
     *            the tempPath to set
     */
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    /**
     * @return the multipleUpload
     */
    public boolean isMultipleUpload() {
        return multipleUpload;
    }

    /**
     * @param multipleUpload
     *            the multipleUpload to set
     */
    public void setMultipleUpload(boolean multipleUpload) {
        this.multipleUpload = multipleUpload;
    }

    /**
     * @return the maxRetryCount
     */
    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    /**
     * @param maxRetryCount
     *            the maxRetryCount to set
     */
    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    /**
     * @return the ftpParameters
     */
    public FtpParameters getFtpParameters() {
        return ftpParameters;
    }

    /**
     * @param ftpParameters
     *            the ftpParameters to set
     */
    public void setFtpParameters(FtpParameters ftpParameters) {
        this.ftpParameters = ftpParameters;
    }
}
