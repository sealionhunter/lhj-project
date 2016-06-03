package com.ustcsoft.generalsolution.dmat.webui.notice.integration;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ustcsoft.generalsolution.dmat.webui.notice.Notice;
import com.ustcsoft.generalsolution.dmat.webui.notice.NoticeService;

@Service
public class DefaultNoticeService implements NoticeService {

	private SqlMapClient sqlMapClient;

	@Inject
	public DefaultNoticeService(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> find() throws SQLException {
		List<Notice> noticeList = sqlMapClient.queryForList("notice.find");

		return noticeList;
	}

}
