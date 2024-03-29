package lhj.web.learn.springweb.domain;

import java.io.Serializable;

/**
 * 
 * @author sealion
 * 
 */
public class Product implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9136888459293123783L;
    /**  */
    private int id;
    private String description;
    private Double price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
        return buffer.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
