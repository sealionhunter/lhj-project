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
    @Id
    @Column(nullable = false, unique = true, updatable = false, insertable = false, name = "ID")
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
    private static final long serialVersionUID = 1L;

    public UserInfoEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("userId:").append(this.getUserId());
        sb.append("|name:").append(this.getName());
        sb.append("|sex:").append(this.getSex());
        sb.append("|birthday:").append(this.getBirthday());
        return sb.toString();
    }
}
