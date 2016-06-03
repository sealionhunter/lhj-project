/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

/**
 * @author hjliang
 *
 */
public class UploadException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3883015290537829089L;

    /**
     * @param message
     */
    public UploadException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public UploadException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
