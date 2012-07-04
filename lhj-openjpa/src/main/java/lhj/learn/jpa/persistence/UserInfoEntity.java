package lhj.learn.jpa.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: UserInfoEntity
 */
@Entity
@Table(name = "USER_INFO")
@NamedQueries({
    @NamedQuery(name = "UserInfoEntity.list", query = "select u from UserInfoEntity u")
})
public class UserInfoEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1489271554222108290L;
    @Id
    @Column(updatable = false, insertable = false, name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "USER_ID", nullable = false, length = 256)
    private String userId;
    @Column(name = "USER_NAME", nullable = false, length = 256)
    private String name;
    @Column(name = "SEX", nullable = false, length = 1)
    private String sex;
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;

    public UserInfoEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserInfoEntity)) {
            return false;
        }
        final UserInfoEntity other = (UserInfoEntity) obj;
        if (birthday == null) {
            if (other.birthday != null) {
                return false;
            }
        } else if (!birthday.equals(other.birthday)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (sex == null) {
            if (other.sex != null) {
                return false;
            }
        } else if (!sex.equals(other.sex)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("userId:").append(this.getUserId());
        sb.append("|name:").append(this.getName());
        sb.append("|sex:").append(this.getSex());
        sb.append("|birthday:").append(this.getBirthday());
        return sb.toString();
    }
}
