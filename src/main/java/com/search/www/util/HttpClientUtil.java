package com.search.www.util;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class HttpClientUtil
{
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	public static String getForm(String url){
		try {
			Document document = Jsoup.connect(url).get();
			return document.toString();
		}catch (Exception e){
			logger.equals(e.getMessage());
		}
		return null;
	}
	public static String postForm(String url, List<NameValuePair> formparams)
	{
		try {
			Document document = Jsoup.connect(url).post();
			return document.toString();
		}catch (Exception e){
			logger.equals(e.getMessage());
		}
		return null;
	}
}
