package model;

import java.io.Serializable;

/**
 * The primary key class for the apply database table.
 * 
 */
public class ApplyPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private int userid;

    private int officeid;

    public ApplyPK() {
    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int useaid) {
        this.userid = useaid;
    }

    public int getOfficeid() {
        return this.officeid;
    }

    public void setOfficeid(int officeid) {
        this.officeid = officeid;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApplyPK)) {
            return false;
        }
        ApplyPK castOther = (ApplyPK) other;
        return (this.userid == castOther.userid)
                && (this.officeid == castOther.officeid);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.userid;
        hash = hash * prime + this.officeid;

        return hash;
    }
}