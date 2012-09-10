package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcsoft.gs.myerp.webui.common.Paging;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserInfo> list(SearchCondition condition, Paging paging)
			throws Exception {
		// TODO Auto-generated method stub
		return userDao.list(condition, paging);
	}

	@Override
	public int count(SearchCondition condition) throws Exception {
		// TODO Auto-generated method stub
		return userDao.count(condition);
	}

	@Override
	public UserInfo get(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return userDao.get(uuid);
	}

	@Override
	public void add(UserInfo data) throws Exception {
		userDao.add(data);
	}

	@Override
	public void update(UserInfo data) throws Exception {
		userDao.update(data);
	}

	@Override
	public void delete(String[] split) throws Exception {
		userDao.delete(split);

	}

}
