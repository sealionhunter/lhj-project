package model;

import java.io.Serializable;

/**
 * The persistent class for the user database table.
 * 
 */
public class Admin implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1962520684392522668L;

    private Integer id;

    private String password;

    private String administratorId;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the administratorId
     */
    public String getAdministratorId() {
        return administratorId;
    }

    /**
     * @param administratorId the administratorId to set
     */
    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

}