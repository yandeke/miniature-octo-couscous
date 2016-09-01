package com.search.www.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class HttpRequestUtil {
	
	/**
	 * 
	 * @param request
	 * @param name 名字
	 * @param defaultValue 默认�?
	 * @return String
	 */
	public static String getStringParames(HttpServletRequest request,String name, String defaultValue){
		if(StringUtils.isNotEmpty(name)){
			String value = request.getParameter(name);
			if(StringUtils.isNotEmpty(value)){
				return value;
			}
		}
		return defaultValue;
	}
	
	/**
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return 
	 */
	public static Integer getIntParames(HttpServletRequest request,String name, Integer defaultValue){
		if(StringUtils.isNotEmpty(name)){
			String value = request.getParameter(name);
			if(StringUtils.isNotEmpty(value)){
				return Integer.parseInt(value);
			}
		}
		return defaultValue;
	}
	
	/**
	 * 
	 * @param request
	 * @param name 
	 * @param defaultValue 
	 * @return long
	 */
	public static long getLongParames(HttpServletRequest request,String name, long defaultValue){
		if(StringUtils.isNotEmpty(name)){
			String value = request.getParameter(name);
			if(StringUtils.isNotEmpty(value)){
				return Long.parseLong(value);
			}
		}
		return defaultValue;
	}
}
