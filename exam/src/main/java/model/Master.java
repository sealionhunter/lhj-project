package model;

import java.io.Serializable;

/**
 * The persistent class for the apply database table.
 * 
 */
public class Master implements Serializable {
    private static final long serialVersionUID = 1L;

    private MasterPK id;

    private String name;

    /**
     * @return the id
     */
    public MasterPK getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(MasterPK id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}