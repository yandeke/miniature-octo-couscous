package com.search.www.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 将字符串按照指定的格式转换为日期
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
	 * 将日期边为指定格式的字符�?
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formateDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * �ж�date�Ƿ��ڵ�ǰʱ��֮ǰ
	 * @param date
	 * @return
	 */
	public static boolean compareDate(Date date){
		return compareDate(date, new Date());
	}

	/**
	 * �Ƚϴ�С date1 ��date2֮ǰ������true
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {
		return date1.after(date2);
	}
}
