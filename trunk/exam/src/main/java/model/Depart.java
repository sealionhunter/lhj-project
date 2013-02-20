package model;

import java.io.Serializable;

/**
 * The persistent class for the depart database table.
 * 
 */
public class Depart implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String description;

    private String name;

    private Integer cityId;

    private String cityName;

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     *            the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Depart() {
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

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer city) {
        this.cityId = city;
    }

}