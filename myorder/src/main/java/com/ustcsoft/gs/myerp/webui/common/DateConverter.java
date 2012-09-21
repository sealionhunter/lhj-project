package com.ustcsoft.gs.myerp.webui.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static final String FORMATDATE = "yyyy/MM/dd";

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0) {
			return null;
		}
		Date date = null;
		String dateString = values[0];
		if (dateString != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATDATE);
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				date = null;
			}
		}
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATDATE);
			return sdf.format((Date) o);
		}
		return "";
	}

}