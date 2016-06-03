/*
 * USI Co., Ltd. 2015. All rights reserved.
 */
package lhj.java.xcp;

/**
 * @author hjliang
 *
 */
public class LoginFailedException extends UploadException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7111939190994844075L;

    /**
     * @param message
     */
    public LoginFailedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
