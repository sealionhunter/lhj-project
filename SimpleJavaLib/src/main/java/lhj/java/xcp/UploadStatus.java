/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

/**
 * @author hjliang
 *
 */
public class UploadStatus {
    public static enum Status {
        INIT,
        UPLOADING,
        COMPLETE,
        COMPLETE_WITH_ERROR,
        COMPLETE_WITH_WARN,
        CANCEL
    }
    private Status status = Status.INIT;
    private int retryCount = 0;
    private Throwable error;

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the retryCount
     */
    public int getRetryCount() {
        return retryCount;
    }

    /**
     * @param retryCount
     *            the retryCount to set
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * @return the error
     */
    public Throwable getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(Throwable error) {
        this.error = error;
    }
}
