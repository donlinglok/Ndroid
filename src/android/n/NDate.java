package android.n;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.util.Log;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public class NDate {
	public static final String ISO_INSTANT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";
	public static final String FORMAT_TIME_NOS = "HH:mm";
	public static final String FORMAT_DATETIME = NString.add(FORMAT_DATE, " ", FORMAT_TIME);

	private static Date myTime;

	public static final void setDate(final Date date) {
		myTime = date;
	}

	public static final Date getDate() {
		return getCalendar().getTime();
	}

	public static final Calendar getCalendar() {
		final Calendar calendar = Calendar.getInstance();

		if (!It.isNull(myTime))
			calendar.setTime(myTime);

		return calendar;
	}

	public static final Date parse(final String form, final Object date) {
		return parse(new SimpleDateFormat(form, Locale.US), date);
	}

	public static final Date parse(final DateFormat formFormat, final Object date) {
		final Calendar calendar = getCalendar();
		try {
			calendar.setTime(formFormat.parse(NString.parse(date)));
		} catch (final ParseException e) {
			Log.e("", "", e);
		}
		return calendar.getTime();
	}

	public static final String toString(final String form, final String to, final Object date, final String offset) {
		return toString(form, new SimpleDateFormat(to, Locale.US), date, offset);
	}

	public static final String toString(final String form, final DateFormat toFormat, final Object date,
			final String offset) {
		final Calendar calendar = getCalendar();
		try {
			calendar.setTime(new SimpleDateFormat(form, Locale.US).parse(NString.parse(date)));
		} catch (final ParseException e) {
			Log.e("", "", e);
		}

		return format(toFormat, calendar.getTime(), offset);
	}

	public static final String format(final String to, final Date date, final String offset) {
		return format(new SimpleDateFormat(to, Locale.US), date, offset);
	}

	public static final String format(final DateFormat toFormat, final Date date, final String offset) {
		final Calendar calendar = getCalendar();
		calendar.setTime(date);
		toFormat.setTimeZone(TimeZone.getTimeZone("GMT" + offset));

		return toFormat.format(calendar.getTime());
	}

	public static final boolean isToday(final Date date) {
		return isToday(date, 0);
	}

	public static final boolean isToday(final Date date, final int allowance) {
		final Calendar calendar = getCalendar();
		calendar.add(Calendar.HOUR, allowance);

		return isSameDay(date, calendar.getTime());
	}

	public static final boolean isSameDay(final Date date1, final Date date2) {
		boolean result = false;
		if (null != date1 && null != date2) {
			final Calendar calendar1 = getCalendar();
			calendar1.setTime(date1);
			final Calendar calendar2 = getCalendar();
			calendar2.setTime(date2);
			result = calendar1.get(Calendar.ERA) == calendar2.get(Calendar.ERA)
					&& calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
					&& calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
		}
		return result;
	}

	public static final int nowHour() {
		final Calendar calendar = getCalendar();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}
