package com.javdev.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String YYYY_MM_DD = "yyyy/MM/dd";
	public static String HH12_MM_SS = "hh:mm:ss";
	public static String HH24_MM_SS = "HH:mm:ss";

	public static void main(String[] args) {
		try {
			System.out.println(DateUtil.formatDate(new Date(), DateUtil.HH12_MM_SS));
			System.out.println(DateUtil.formatDate(new Date(), DateUtil.HH24_MM_SS));

		} catch (Exception e) {

		}

	}

	/** @author MTorres 7/01/2016 2:41:52 p. m.f */
	public static String formatDate(Date date, String format) throws Exception {
		try {
			if (date != null && format != null) {
				SimpleDateFormat df = new SimpleDateFormat(format);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				return df.format(c.getTime());
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 11/01/2016 10:27:49 p. m. */
	public static String getCurrentStringDate() throws Exception {
		try {
			return DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYY_MM_DD);
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 11/01/2016 10:29:24 p. m. */
	public static String getCurrentStringHour() throws Exception {
		try {
			return DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.HH24_MM_SS);
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 7/01/2016 6:06:14 p. m. */
	public static Date getCurrentDate() throws Exception{
		try {
			return Calendar.getInstance().getTime();
		} catch (Exception e) {
			throw e;
		}
	}
}
