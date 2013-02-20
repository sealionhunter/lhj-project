package model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the apply database table.
 * 
 */
public class Apply implements Serializable {
    private static final long serialVersionUID = 1L;

    private ApplyPK id;

    private Date createDate;

    private int state;

    private String reason;

    private Date udpateDate;

    public Apply() {
    }

    public ApplyPK getId() {
        return this.id;
    }

    public void setId(ApplyPK id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getUdpateDate() {
        return this.udpateDate;
    }

    public void setUdpateDate(Date udpateDate) {
        this.udpateDate = udpateDate;
    }

}