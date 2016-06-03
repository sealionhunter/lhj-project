package com.ustcsoft.generalsolution.dmat.webui.dmat.integration;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ustcsoft.generalsolution.dmat.webui.dmat.DmatCalendar;
import com.ustcsoft.generalsolution.dmat.webui.dmat.DmatDetail;
import com.ustcsoft.generalsolution.dmat.webui.dmat.DmatService;

@Service
@Repository
public class DefaultDmatService implements DmatService {
	private SqlMapClient sqlMapClient;

	@Inject
	public DefaultDmatService(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DmatCalendar getDmatDetailsForDay(LocalDate day) throws SQLException {
		DmatCalendar calendar = new DmatCalendar(day);
		List<DmatDetail> details = sqlMapClient.queryForList("dmat.getDetails", day.toDate());
		// List<DmatDetail> details = new ArrayList<DmatDetail>();
		calendar.setDetails(details);
		return calendar;
	}

	@Override
	public Long addDmatDetail(DmatDetail detail) throws SQLException {
		detail = (DmatDetail)sqlMapClient.insert("dmat.addDetail", detail);
		return detail.getDmatId();
	}

	@Override
	public void deleteDmatDetail(Long id) throws SQLException {
		sqlMapClient.delete("dmat.deleteDetail", id);
	}

	@Override
	public void updateDmatDetail(DmatDetail detail) throws SQLException {
		sqlMapClient.update("dmat.updateDetail", detail);
	}
}
