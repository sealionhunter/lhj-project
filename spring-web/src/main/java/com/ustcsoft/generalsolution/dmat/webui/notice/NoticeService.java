package com.ustcsoft.generalsolution.dmat.webui.notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeService {
	public List<Notice> find() throws SQLException;
}
