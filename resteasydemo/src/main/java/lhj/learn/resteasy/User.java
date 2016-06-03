package lhj.learn.resteasy;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class User implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -823517331873569087L;
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "uri")
    private String uri;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "last-modified")
    private Date lastModified;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public User setUri(String uri) {
        this.uri = uri;
        return this;
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
    public User setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public User setLastModified(Date lastModified) {
        this.lastModified = lastModified;
        return this;
    }
}
