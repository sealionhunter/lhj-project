package model;

import java.io.Serializable;

/**
 * The persistent class for the seat database table.
 * 
 */
public class Admission implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 705143622971646304L;

    private int userId;
    private String code;

    public Admission(int userId, String code) {
        super();
        this.userId = userId;
        this.code = code;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}