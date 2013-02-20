package model;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the city database table.
 * 
 */
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String description;

    private String name;

    private List<Depart> departs;

    public City() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Depart> getDeparts() {
        return this.departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

}