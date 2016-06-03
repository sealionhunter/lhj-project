package com.ustcsoft.generalsolution.dmat.webui.dmat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault = false)
@RooToString
public class DmatCalendar {
	private final LocalDate day;

	private final List<DmatDetail> details = new ArrayList<DmatDetail>();;

	public DmatCalendar(final LocalDate day) {
		this.day = day;
	}

	public void setDetails(final List<DmatDetail> details) {
		this.details.clear();
		this.details.addAll(details);
	}

	public void addDetail(final DmatDetail detail) {
		details.add(detail);
	}

	public Long getDayMillis() {
		return day.toDateTimeAtStartOfDay().getMillis();
	}

	public LocalDate getDay() {
		return day;
	}

	public LocalDate getPreviousDay() {
		return day.minusDays(1);
	}

	public LocalDate getNextDay() {
		return day.plusDays(1);
	}
}
