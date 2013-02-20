package model;

import java.io.Serializable;

/**
 * The primary key class for the apply database table.
 * 
 */
public class MasterPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private int category;

    private int code;

    public MasterPK() {
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int useaid) {
        this.category = useaid;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int officeid) {
        this.code = officeid;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MasterPK)) {
            return false;
        }
        MasterPK castOther = (MasterPK) other;
        return (this.category == castOther.category)
                && (this.code == castOther.code);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.category;
        hash = hash * prime + this.code;

        return hash;
    }
}