package com.ustcsoft.generalsolution.dmat.webui.dmat;

import java.sql.SQLException;

import org.joda.time.LocalDate;

public interface DmatService {
	public DmatCalendar getDmatDetailsForDay(LocalDate date) throws SQLException;

	public Long addDmatDetail(DmatDetail detail) throws SQLException;

	public void deleteDmatDetail(Long id) throws SQLException;

	public void updateDmatDetail(DmatDetail detail) throws SQLException;
}
