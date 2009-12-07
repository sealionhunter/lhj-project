package lhj.learn.spring.ejb;

import java.util.List;

import javax.ejb.EJB;

import lhj.learn.ejb3.facade.UserInfoFacade;
import lhj.learn.jpa.persistence.UserInfoEntity;

public class UserInfoManager {
    @EJB
    UserInfoFacade userInfoFacade;
    List<UserInfoEntity> userInfos;

    public List<UserInfoEntity> list() {
        return userInfoFacade.list();
    }

    public void add() {
        for (UserInfoEntity userInfo : userInfos) {
            userInfoFacade.add(userInfo);
        }
    }

    /**
     * @return the userInfos
     */
    public List<UserInfoEntity> getUserInfos() {
        return userInfos;
    }

    /**
     * @param userInfos
     *        the userInfos to set
     */
    public void setUserInfos(List<UserInfoEntity> userInfos) {
        this.userInfos = userInfos;
    }
}
