package lhj.learn.ejb3.facade;

import java.util.List;

import javax.ejb.Remote;

import lhj.learn.jpa.persistence.UserInfoEntity;

@Remote
public interface UserInfoFacade {
    List<UserInfoEntity> list() ;

    void add(UserInfoEntity userinfo);

    void remove(long id) ;

//    void update(UserInfoEntity userinfo);
}
