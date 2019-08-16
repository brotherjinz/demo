package com.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class DateTools {
	
	public static Date stringToDate(String str)
    {
        int year = Integer.valueOf(str.substring(0, 4)).intValue();
        int month = Integer.valueOf(str.substring(4, 6)).intValue();
        int day = Integer.valueOf(str.substring(6)).intValue();
        return date(year, month, day);
    }
	
	public static Date stringToDateTime(String str) {
		int year = Integer.valueOf(str.substring(0, 4)).intValue();
		int month = Integer.valueOf(str.substring(4, 6)).intValue();
		int day = Integer.valueOf(str.substring(6, 8)).intValue();
		int hour = Integer.valueOf(str.substring(9, 11)).intValue();
		int minute = Integer.valueOf(str.substring(11, 13)).intValue();
		
		return dateTime(year, month, day, hour, minute);
	}
	
	public static Date dateTime(int year, int month, int day, int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, 0);
		return calendar.getTime();
	}
	
     public static Date date(int year, int month, int day)
    { 
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar.getTime();
    }

     public static Date getNowDate(){
    	 return  new Date();
     }
	 public static String getNowDate(String format) {//yyyy-MM-dd或者yyyyMMdd
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c=Calendar.getInstance();
			String s_date=sdf.format(c.getTime());
			return s_date;
		}
	 
	 public static String getNextDate(String format) {//yyyy-MM-dd或者yyyyMMdd
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c=Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			String s_date=sdf.format(c.getTime());
			return s_date;
		}
	 public static Date getNextDate() {//yyyy-MM-dd或者yyyyMMdd
			Calendar c=Calendar.getInstance();
			c.setTime(getNowDate());
			c.add(Calendar.DATE, 1);
			Date dateNew = c.getTime();
			return dateNew;
	 }
	 
	 public static String getNextSomeDate(String format,int n) {//yyyy-MM-dd或者yyyyMMdd
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c=Calendar.getInstance();
			c.add(Calendar.DATE, n);
			String s_date=sdf.format(c.getTime());
			return s_date;
		}
	 
	 public static String getLastDay(int year,int month){
	    	String lastday ="";
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				lastday = "31";
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				lastday = "30";
				break;
			case 2:
				if (isLeapYear(year))
					lastday = "29";
				else
					lastday = "28";
				break;

			}
			return lastday;
	    }
	 
	 public static boolean isLeapYear(int year)
	    {
	        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	    }
	 public static String getNextMonth(String format){
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c=Calendar.getInstance();
			c.add(Calendar.MONTH, 1);
			String s_date=sdf.format(c.getTime());
			return s_date;
	    }
	 
	 public static String getNextYear(String format){
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c=Calendar.getInstance();
			c.add(Calendar.YEAR, 1);
			String s_date=sdf.format(c.getTime());
			return s_date;
	    }
	 
	 public static Date getNextNDays(Date date, int days) {
		 if(date == null)	return null;
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, days);
			Date dateNew = c.getTime();
			return dateNew;
		}
	 
}
