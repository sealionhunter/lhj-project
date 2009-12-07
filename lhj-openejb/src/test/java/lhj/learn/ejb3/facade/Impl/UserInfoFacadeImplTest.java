package lhj.learn.ejb3.facade.Impl;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import lhj.learn.ejb3.facade.UserInfoFacade;
import lhj.learn.jpa.persistence.UserInfoEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInfoFacadeImplTest {

    Context context;
    UserInfoFacade facade ;
    @Before
    public void setUp() throws Exception {
        context = new InitialContext();
        facade = (UserInfoFacade) context.lookup("UserInfoFacade");
    }
    @After
    public void tearDown() throws Exception {
        List<UserInfoEntity> list = facade.list();
        for (UserInfoEntity userinfo : list) {
            facade.remove(userinfo.getId());
        }
    }
    @Test
    public void testList() throws Exception {
        List<UserInfoEntity> list = facade.list();
        assertEquals(0, list.size());
    }

    @Test
    public void testAdd() throws Exception {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId("hjliang");
        userInfo.setName("Liang Haijin");
        userInfo.setSex("1");
        userInfo.setBirthday(Calendar.getInstance().getTime());

        facade.add(userInfo);

        assertEquals(1, facade.list().size());
    }

}
