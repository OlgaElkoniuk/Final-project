package com.olga.couponsPhase3.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	public static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static String dateToStringConverter(Date date) {
		return sdf.format(date);
	}
	public static Date StringToDateConverter(String date) throws ParseException {
		return sdf.parse(date);
	}
}
