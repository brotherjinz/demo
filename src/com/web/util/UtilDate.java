package com.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {
	
	public String date(String date) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt =df.parse(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = simpleDateFormat.format(dt);
		return date;
	}
	public String stringdate(String date,String format){
		
		return date;
	}
	/*SimpleDateFormat df = new SimpleDateFormat("yyyymmddhhmmss");
	String dd = df.format(new Date());
	System.out.println(dd);
	Date date =df.parse(dd);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	String dr = simpleDateFormat.format(date);
	System.err.println(dr);*/
	
}
