package com.search.www.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 灏嗗瓧绗︿覆鎸夌収鎸囧畾鐨勬牸寮忚浆鎹负鏃ユ湡
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStr(String str,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(str);
	}
	
	/**
	 * 灏嗘棩鏈熻竟涓烘寚瀹氭牸寮忕殑瀛楃涓?
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formateDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 判断date是否在当前时间之前
	 * @param date
	 * @return
	 */
	public static boolean compareDate(Date date){
		return compareDate(date, new Date());
	}

	/**
	 * 比较大小 date1 在date2之前，返回true
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {
		return date1.after(date2);
	}
}
