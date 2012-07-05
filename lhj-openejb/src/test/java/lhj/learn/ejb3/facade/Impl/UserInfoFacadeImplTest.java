package lhj.learn.ejb3.facade.Impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    Context        context;
    UserInfoFacade facade;

    @Before
    public void setUp() throws Exception {
        context = new InitialContext();
        facade = (UserInfoFacade) context.lookup("UserInfoFacade");
    }

    @After
    public void tearDown() throws Exception {
        final List<UserInfoEntity> list = facade.list();
        for (final UserInfoEntity userinfo : list) {
            facade.remove(userinfo.getId());
        }
    }

    @Test
    public void testList() throws Exception {
        final List<UserInfoEntity> list = facade.list();
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
        final List<UserInfoEntity> list = facade.list();

        assertNotNull(list);
        assertEquals(1, list.size());
        userInfo = list.get(0);
        assertEquals("hjliang", userInfo.getUserId());
        assertEquals("Liang Haijin", userInfo.getName());
        assertEquals("1", userInfo.getSex());
    }

}
