package com.web.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: DateUtil
 * @Description:TODO(时间转换工具类)
 * @author: zml
 * @date: 2014-4-18 上午10:20:40
 * 
 */
public class DateUtil {
	private final static SimpleDateFormat sddd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyyMMdd");
	// ~ Static fields/initializers
	// =============================================
	public static final String defaultTimestampPattern = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss.SSS";
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static final String datePattern = "yyyy-MM-dd";
	private static final String timePattern = "HH:mm:ss S";
	private static final String datePatternWithAllNumber = "yyyyMMdd";
	private static final String datetimePattern = "yyyyMMddHHmmss";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

	private static final String datetimetoPattern = "yyyy/MM/dd HH:mm:ss";

	private final static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdss = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private final static SimpleDateFormat sdee = new SimpleDateFormat("EEEE");

	// ~ Methods
	// ================================================================

	private DateUtil() {
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return datePattern;
	}

	/**
	 * 按照客户端日期格式转化日期，如：2005-11-07T16:21:27.123
	 * 
	 * @param dateObj
	 *            Object 日期对象
	 * @return String
	 * @version 0.1/2005-11-7
	 */
	public static String dateFormatForClient(Object dateObj) {
		return dateFormatForClient(dateObj, 0);
	}

	/**
	 * 按照客户端日期格式转化日期，如：2005-11-07T16:21:27.123
	 * 
	 * @param dateObj
	 *            Object 日期对象
	 * @param dateFormate
	 *            int 日期格式化模式 0：在日期和时间中间加个‘T’（C#） 1：不加‘T’（其他）
	 * @return String
	 * @version 0.1/2005-11-7
	 */
	public static String dateFormatForClient(Object dateObj, int dateFormatType) {
		String date = dateFormat(dateObj, defaultTimestampPattern);
		if (dateFormatType == 0) {
			return date == null ? null : date.replace(' ', 'T');
			// c#要求在日期和时间中间加个“T”
		} else {
			return date;
		}
	}

	/**
	 * 将日期对象格式化成字符串
	 * 
	 * @param dateObj
	 *            日期对象 Date/Calendar
	 * @return String 格式化的日期表示
	 */
	public static String dateFormat(Object dateObj) {
		return dateFormat(dateObj, null);
	}

	/**
	 * 将日期对象格式化
	 * 
	 * @param dateObj
	 *            日期对象 Date/Calendar
	 * @param pattern
	 *            日期和时间的表示格式
	 * @return String 格式化的日期表示
	 */
	public static String dateFormat(Object dateObj, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? defaultDatePattern : pattern, Locale.CHINA);
		if (dateObj == null) {
			return null;
		} else if (dateObj instanceof java.util.Calendar) { // Calendar
			java.util.Date date = ((java.util.Calendar) dateObj).getTime();
			return sdf.format(date);
		} else if (dateObj instanceof java.sql.Date) {
			java.util.Date date = new Date(((java.sql.Date) dateObj).getTime());
			return sdf.format(date);
		} else if (dateObj instanceof java.sql.Time) {
			java.util.Date date = new Date(((java.sql.Time) dateObj).getTime());
			return sdf.format(date);
		} else if (dateObj instanceof Timestamp) {
			java.util.Date date = new Date(((java.sql.Timestamp) dateObj).getTime());
			sdf = new SimpleDateFormat((pattern == null) ? defaultTimestampPattern : pattern, Locale.CHINA);
			return sdf.format(date);
		} else if (dateObj instanceof java.util.Date) { // date
			return sdf.format((java.util.Date) dateObj);
		} else {
			return null;
		}
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.debug("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.debug("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + datePattern);
			}

			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			log.debug("Could not convert '" + strDate + "' to a date, throwing exception");
			// pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * This method generates a string representation of a date based on the System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateTimeToString(Date aDate) {
		return getDateTime(datetimePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDateTime(String strDate) throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + datetimePattern);
			}

			aDate = convertStringToDate(datetimePattern, strDate);
		} catch (ParseException pe) {
			log.debug("Could not convert '" + strDate + "' to a date, throwing exception");
			// pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	public static Date expiredToDate(int day) {
		Date obj = new Date();
		int date = obj.getDate();
		obj.setDate(date + day);
		return obj;
	}

	public static Date getDates(String year, String type, String value) {
		int month = 1;
		if (type.equals("1")) {
			month = Integer.parseInt(value, 10);
		} else if (type.equals("2")) {
			if (value.equals("1")) {
				month = 3;
			} else if (value.equals("2")) {
				month = 6;
			} else if (value.equals("3")) {
				month = 9;
			} else if (value.equals("4")) {
				month = 12;
			}
		} else if (type.equals("3")) {
			if (value.equals("1")) {
				month = 6;
			} else if (value.equals("2")) {
				month = 12;
			}
		} else if (type.equals("4")) {
			month = 12;
		}

		Date obj = new Date(Integer.parseInt(year, 10) - 1900, month, 1);
		System.out.println(obj.toString());
		int date = obj.getDate();
		obj.setDate(date - 1);
		return obj;
	}

	/**
	 * 将时间戳转化成ORACLE的日期格式
	 * 
	 * @param timestamp
	 *            String 日期字符串 2005-01-01 12:10:11.123
	 * @return String ORACLE格式日期 TO_TIMESTAMP('2005-01-01 12:10:11.123','yyyy-mm-dd hh24:mi:ssxff')
	 * @version 0.2/2005-12-20
	 */
	public static String formatTimestamp(String timestamp) {
		if (timestamp == null)
			return null;
		else if (timestamp.indexOf('.') > 0) { // timestamp
			return "TO_TIMESTAMP('" + timestamp + "','yyyy-mm-dd hh24:mi:ssxff')";
		} else
			// date
			return "TO_DATE('" + timestamp + "','yyyy-mm-dd hh24:mi:ss')";
	}

	public static Date toDate(String dateStr) {
		return toDate(dateStr, null);
	}

	/**
	 * 根据输入的日期字符串构造日期对象，并返回
	 * 
	 * @param dateStr
	 *            String 日期字符串，如：2005-01-01 12:00:00.000
	 * @param pattern
	 *            String 日期格式
	 * @return Date
	 */
	public static Date toDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? defaultDatePattern : pattern, Locale.CHINA);
		Date dateReturn = null;
		try {
			dateReturn = sdf.parse(dateStr);
		} catch (ParseException e) {

		}
		return dateReturn;
	}

	/**
	 * 根据输入的日期字符串按照默认日期格式（yyyy-MM-dd HH:mm:ss），构造日历对象（Calendar）并返回
	 * 
	 * @param dateStr
	 *            String 日期字符串，如：2005-01-01 12:00:00.000
	 * @return Calendar
	 */
	public static Calendar toCalendar(String dateStr) {
		return toCalendar(dateStr, null);
	}

	/**
	 * 根据输入的日期字符串构造日历对象（Calendar）并返回
	 * 
	 * @param dateStr
	 *            String 日期字符串，如：2005-01-01 12:00:00.000
	 * @param pattern
	 *            String 日期格式
	 * @return
	 */
	public static Calendar toCalendar(String dateStr, String pattern) {
		Date date = toDate(dateStr, pattern);
		Calendar calendarReturn = Calendar.getInstance();
		calendarReturn.setTime(date);
		return calendarReturn;
	}

	/**
	 * 将日期转换为8位数字的字符串，格式为yyyyMMdd，如：20100421
	 */
	public static String getDateStringOfAllNumber(Date date) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(datePatternWithAllNumber, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * 将日期转换为指定格式的字符串，格式为yyyyMMdd，如：20100421
	 */
	public static String getDateString(Date date, String str) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(str, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * @param void
	 * @return 当前年
	 */
	public static final String getCurrentYear() {
		String dy = "";
		dy = dateFormat.format(new Date());
		dy = dy.substring(0, 4);
		return dy;
	}

	/**
	 * @param void
	 * @return 当前年
	 */
	public static final String getCurrentShortYear() {
		String dy = "";
		dy = dateFormat.format(new Date());
		dy = dy.substring(2, 4);
		return dy;
	}

	/**
	 * @param void
	 * @return 当前月
	 */
	public static final String getCurrentMonth() {
		String dm = "";
		dm = dateFormat.format(new Date());
		dm = dm.substring(5, 7);
		return dm;
	}

	/**
	 * @param void
	 * @return 当前日
	 */
	public static final String getCurrentDay() {
		String dd = "";
		dd = dateFormat.format(new Date());
		dd = dd.substring(8, 10);
		return dd;
	}
	public static Date addDays(Date srcDate, int days) {
		if (srcDate == null)
			return null;
		Date newDate = (Date) srcDate.clone();
		newDate.setTime(srcDate.getTime() + (long) days * 24 * 3600000);
		return newDate;
	}

	public static Date moveMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int newMonth = calendar.get(calendar.MONTH) + offset;
		calendar.set(Calendar.MONTH, newMonth);
		return calendar.getTime();
	}

	public static boolean isLeapYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		if (year % 100 == 0) {
			if (year % 400 == 0) {
				return true;
			}
		} else {
			if (year % 4 == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 两个日期相差天数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 得到两个日期相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12 + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth - 1;
	}

	public static void main(String[] args) throws ParseException {
		String mm = addSecond("2017-08-01 12:51:36", 168);
		System.out.println(mm);
	}

	/**
	 * 获取默认日期
	 * 
	 * @param iDay
	 *            距今天的天数 负数为前，正数为后
	 * @param strFormat
	 *            时间格式串
	 * @return
	 */
	public static String getDefautDate(int iDay, String strFormat) throws Exception {
		String strDefautDate = "";
		java.util.Calendar c = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(strFormat);
		c.add(Calendar.DATE, iDay);
		strDefautDate = f.format(c.getTime());
		return strDefautDate;
	}

	/**
	 * 获取默认日期 ，默认时间格式串为yyyy-MM-dd
	 * 
	 * @param iDay
	 *            距今天的天数 负数为前，正数为后
	 * @return
	 */
	public static String getDefautDate(int iDay) throws Exception {
		String strDefautDate = "";
		strDefautDate = DateUtil.getDefautDate(iDay, "yyyy-MM-dd");
		return strDefautDate;
	}

	/**
	 * 获取某月最后一天
	 */
	public static Date getLastDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
	}

	/**
	 * 获取某月第一天
	 */
	public static Date getFirstDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int lastDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
	}

	/**
	 * 将分钟转为时：分的时间格式，如70分种转为1：10
	 */
	public static String getDatebyMinute(Long lMinute) {
		String strDate = "";
		if (lMinute < 0) {
			strDate = "-";
			lMinute = lMinute * -1;
		}
		Long iDay = lMinute / 1440;
		if (iDay != 0) {
			strDate = strDate + Long.toString(iDay) + "天";
		}
		strDate = strDate + Long.toString((lMinute % 1440) / 60);
		Long iMi = lMinute % 60;
		if (iMi < 10) {
			strDate = strDate + ":0" + Long.toString(iMi);
		} else {
			strDate = strDate + ":" + Long.toString(iMi);
		}
		return strDate;
	}

	/**
	 * 两个时间相差的分钟数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getMinutes(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static Date StringToDate(String dateString, String formatString) {
		try {
			DateFormat fm = new SimpleDateFormat(formatString);
			Date date = null;
			try {
				date = fm.parse(dateString);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				return null;
			} // Thu Jan 18 00:00:00 CST 2007
			return date;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static String DateToString(Date date, String formatString) {
		try {
			DateFormat fm = new SimpleDateFormat(formatString);
			String str = new String();
			str = fm.format(date);
			return str;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static String penaltyString(int second) {

		int sec = second % 60;
		second = second / 60;
		int min = second % 60;
		int hour = second = second / 60;

		String nowTime = new String();

		if (hour < 10) {
			if (hour == 0)
				nowTime = "00";
			nowTime = "0" + hour;
		} else {
			nowTime = Integer.toString(hour);
		}

		if (min < 10) {
			nowTime = nowTime + ":0" + min;
		} else {
			nowTime = nowTime + ":" + min;
		}

		if (sec < 10) {
			nowTime = nowTime + ":0" + sec;
		} else {
			nowTime = nowTime + ":" + sec;
		}

		return nowTime;
	}

	public static String secondToString(long second) {

		long sec = second % 60;
		second = second / 60;
		long min = second % 60;
		long hour = second = second / 60;

		String nowTime = new String();

		if (hour < 10) {
			if (hour == 0)
				nowTime = "00";
			nowTime = "0" + hour;
		} else {
			nowTime = Long.toString(hour);
		}

		if (min < 10) {
			nowTime = nowTime + ":0" + min;
		} else {
			nowTime = nowTime + ":" + min;
		}

		if (sec < 10) {
			nowTime = nowTime + ":0" + sec;
		} else {
			nowTime = nowTime + ":" + sec;
		}

		return nowTime;
	}

	public static String toFriendlyDate(Date time) {
		if (time == null)
			return "unknown";
		int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
		if (ct < 3600)
			return Math.max(ct / 60, 1) + " minutes ago";
		if (ct >= 3600 && ct < 172800) // 48小时
			return ct / 3600 + " hours ago";
		if (ct >= 172800 && ct < 2592000) { // 86400 * 30
			int day = ct / 86400;
			return day + " days ago";
		}
		if (ct >= 2592000 && ct < 62208000) // 到24个月
			return ct / 2592000 + " months ago";
		return ct / 31104000 + " years ago";
	}

	// 返回年份
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	// 返回月份
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	// 返回天
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	// 返回秒钟
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	// 返回小时
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	// 返回分钟
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	// 返回毫秒
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	// 日期相减
	// 相差天数：long day=difference/(3600*24*1000)
	// 相差小时：long hour=difference/(3600*1000)
	// 相差分钟：long minute=difference/(60*1000)
	// 相差秒： long second=difference/1000
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (3600 * 1000));
	}

	public static String getTodayString() {
		Date d = new Date();
		return sdf.format(d);
	}

	/**
	 * 根据传入的zprq，格式化成 yyyy-MM-dd 格式
	 * 
	 * @param zprq
	 * @return returnStr
	 */
	public static String getFormetZprq(String zprq) {
		String returnStr = "";
		if (zprq != null) {
			returnStr = zprq.substring(0, 4) + "-" + zprq.substring(4, 6) + "-" + zprq.substring(6);
		}
		return returnStr;
	}

	/**
	 * 根据传入的zpsj，格式化成 HH:mm:ss格式
	 * 
	 * @param zpsj
	 * @return returnStr
	 */
	public static String getFormetZpsj(String zpsj) {
		String returnStr = "";
		if (zpsj != null) {
			returnStr = zpsj.substring(0, 2) + ":" + zpsj.substring(2) + ":00";
		}
		return returnStr;
	}

	/**
	 * 给定时间增加1小时
	 * 
	 * @author yangyw
	 * @param day
	 * @param x
	 *            小时
	 * @return
	 */
	public static String addHour(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}

	/**
	 * 给定时间增加1秒
	 * 
	 * @author yangyw
	 * @param day
	 * @param x
	 *            小时
	 * @return
	 */
	public static String addSecond(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, x);
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	/**
	 * 获取当前时间戳 自定义的日期格式:yyyy-MM-dd HH:mm:ss
	 */
	public static String getSystemTime() {
		return sdf1.format(new Date());
	}

	/**
	 * 获取当前时间戳 自定义的日期格式:yyyyMMdd
	 */
	public static String getSystemDate() {
		return sdf5.format(new Date());
	}

	/**
	 * 自定义的日期格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            java.util.Date格式的日期
	 * @return yyyy-MM-dd HH:mm:ss格式的字符串
	 */
	public static String getDateFormat(Date date) {
		return sdf1.format(date);
	}

	/**
	 * 自定义的日期格式:星期几
	 * 
	 * @param date
	 *            java.util.Date格式的日期
	 * @return 星期几:EEEE
	 */
	public static String getDateFormatEE(Date date) {
		return sdee.format(date);
	}

	/**
	 * 自定义的日期格式
	 * 
	 * @param date
	 *            java.util.Date格式的日期
	 * @param format
	 *            自定义字符
	 * @return 自定义格式的日期字符
	 */
	public static String convertUserDefinedDateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间戳 自定义的日期格式:yyyyMMddHHmmssSSS
	 * 
	 * @return yyyyMMddHHmmssSSS格式的字符串,例如：“20170822110545354”
	 */
	public static String getSystemTimeSS() {
		return sdss.format(new Date());
	}

	/**
	 * 将字符串格式的日期转换为java.util.Date类型
	 * 
	 * @param format
	 *            字符串格式的日期的日期格式，例如：yyyy/MM/dd HH:mm
	 * @param dateString
	 *            字符串格式的日期
	 * @return java.util.Date类型的时间
	 * @throws Exception
	 */
	public static java.util.Date string2Date(String format, String dateString) throws Exception {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		// java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
		return timeDate;
	}

	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param nowTime
	 *            需要判断的时间
	 * @param beginTime
	 *            时间段的起始
	 * @param endTime
	 *            时间段的末尾
	 * @return true表示在时间段内，false表示不在
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		return nowTime.getTime() >= beginTime.getTime() && nowTime.getTime() <= endTime.getTime();
	}

	/**
	 * 根据起始日期以及结束日期计算该时间段内每一天对应在该时间段内的第几周
	 * 
	 * @param startDate
	 *            起始时间字符串，必须以“2017-9-1”这种格式
	 * @param endDate
	 *            结束时间字符串，必须以“2017-9-1”这种格式
	 * @return 存放Map<"xxxx-xx-xx","第几周">的集合
	 */
	public static Map<String, String> getHowManyWeeks(String startDate, String endDate) {

		Calendar c_begin = new GregorianCalendar();
		Calendar c_end = new GregorianCalendar();
		String[] startDates = startDate.split("-");
		String[] endDates = endDate.split("-");

		c_begin.set(Integer.valueOf(startDates[0]), Integer.valueOf(startDates[1]) - 1, Integer.valueOf(startDates[2])); // Calendar的月从0-11，所以4月是3.
		c_end.set(Integer.valueOf(endDates[0]), Integer.valueOf(endDates[1]) - 1, Integer.valueOf(endDates[2])); // Calendar的月从0-11，所以5月是4.

		int count = 1;
		c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天

		StringBuilder weekString = new StringBuilder();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		while (c_begin.before(c_end)) {
			String shdate = convertUserDefinedDateFormat(new Date(c_begin.getTime().getTime()), "yyyy-MM-dd");
			map.put(shdate, count);
			if (weekString == null || weekString.length() == 0) {
				weekString.append("第" + count + "周(" + shdate + "至");
			}
			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				weekString.append(shdate + ")");
				map1.put(count, weekString.toString());
				weekString.setLength(0);
				count++;
			}
			c_begin.add(Calendar.DAY_OF_YEAR, 1);
		}

		Map<String, String> endMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Integer> entry = entries.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			if (map1.containsKey(value)) {
				endMap.put(key, map1.get(value));
			} else if (count == value) {
				String value1 = map1.get((count - 1));
				String beg = value1.substring(15, 25);
				endMap.put(key, "第" + value + "周(" + beg + "至" + endDate + ")");
			}
		}
		return endMap;
	}

	public static String getyyyyMMdd(String str) {
		// 将String转换成Date
		try {
			Date date = sdf.parse(str);
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获取当月的 天数
	 * */
	public static int getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 * */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据日期转换成对应的星期几Calendar方式
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 根据日期 找到对应日期的 星期
	 */
	public static String getDayOfWeekByDate(String date) {
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("E");
			String str = formatter.format(myDate);
			dayOfweek = str;

		} catch (Exception e) {
			System.out.println("错误!");
		}
		return dayOfweek;
	}

	/**
	 * 获取本周第一天至最后一天的日期
	 * 
	 * @param dayNum
	 *            : 参数为想要获取的第几天的数字，如想获取周一的日期就是getFirsToLasOfThisWeek(1)。必须为1-7的数字，其它数字将返回null
	 * @return 'yyyy-MM-dd'格式字符串
	 */
	public static String getFirsToLasOfThisWeek(Integer dayNum) {
		// Map<String,String> map = new HashMap<String,String>();

		// 获取本周的第一天和最后一天
		Date InputDate = new Date();
		Calendar cDate = Calendar.getInstance();
		cDate.setFirstDayOfWeek(Calendar.MONDAY);
		cDate.setTime(InputDate);

		Calendar firstDate = Calendar.getInstance();

		firstDate.setFirstDayOfWeek(Calendar.MONDAY);
		firstDate.setTime(InputDate);

		Calendar lastDate = Calendar.getInstance();
		lastDate.setFirstDayOfWeek(Calendar.MONDAY);
		lastDate.setTime(InputDate);

		if (cDate.get(Calendar.WEEK_OF_YEAR) == 1 && cDate.get(Calendar.MONTH) == 11) {
			firstDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
			lastDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
		}

		int typeNum = cDate.get(Calendar.WEEK_OF_YEAR);
		// System.out.println(typeNum);

		// firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		// firstDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// String first= sddd.format(firstDate.getTime());
		// lastDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		// lastDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// String last= sddd.format(lastDate.getTime());
		// map.put("first", first);
		// map.put("last", last);
		// return map;

		String resultDate = null;
		switch (dayNum) {
		case 1:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 2:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 3:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 4:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 5:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 6:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		case 7:
			firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
			firstDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			resultDate = sddd.format(firstDate.getTime());
			break;
		default:
			break;
		}
		return resultDate;
	}

	/**
	 * 获取本周第一天和最后一天的日期暨周一和周日的日期
	 * 
	 * @return Map集合：first:周一'yyyy-MM-dd'格式字符串；last:周日'yyyy-MM-dd'格式字符串
	 */
	public static Map<String, String> getFirsAndLasOfThisWeek() {
		Map<String, String> map = new HashMap<String, String>();

		// 获取本周的第一天和最后一天
		Date InputDate = new Date();
		Calendar cDate = Calendar.getInstance();
		cDate.setFirstDayOfWeek(Calendar.MONDAY);
		cDate.setTime(InputDate);

		Calendar firstDate = Calendar.getInstance();

		firstDate.setFirstDayOfWeek(Calendar.MONDAY);
		firstDate.setTime(InputDate);

		Calendar lastDate = Calendar.getInstance();
		lastDate.setFirstDayOfWeek(Calendar.MONDAY);
		lastDate.setTime(InputDate);

		if (cDate.get(Calendar.WEEK_OF_YEAR) == 1 && cDate.get(Calendar.MONTH) == 11) {
			firstDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
			lastDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
		}

		int typeNum = cDate.get(Calendar.WEEK_OF_YEAR);
		// System.out.println(typeNum);

		firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		firstDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String first = sddd.format(firstDate.getTime());
		lastDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		lastDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String last = sddd.format(lastDate.getTime());
		map.put("first", first);
		map.put("last", last);
		return map;
	}
}
