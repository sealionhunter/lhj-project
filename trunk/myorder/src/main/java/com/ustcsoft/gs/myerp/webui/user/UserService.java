package com.ustcsoft.gs.myerp.webui.user;

import java.util.List;

import com.ustcsoft.gs.myerp.webui.common.Paging;

public interface UserService {
	public List<UserInfo> list(SearchCondition condition, Paging paging)
			throws Exception;

	public int count(SearchCondition condition) throws Exception;

	public UserInfo get(String uuid) throws Exception;

	public void add(UserInfo data) throws Exception;

	public void update(UserInfo data) throws Exception;

	public void delete(String[] split) throws Exception;
}
