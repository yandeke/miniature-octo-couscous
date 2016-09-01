package com.search.www.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

public class HttpClientUtil
{
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	public static String getForm(String url){
		BufferedReader in = null;
		String content = null;
		try{
			HttpClient client = HttpClients.createDefault();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		}catch (Exception e){
			logger.error(e.getMessage());
		}finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		return content;
	}
	public static String postForm(String url, List<NameValuePair> formparams)
	{
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		String result = null;
		try
		{
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try
			{
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}
			finally
			{
				response.close();
			}
		}
		catch (ClientProtocolException e)
		{
			logger.error(e.getMessage());
		}
		catch (UnsupportedEncodingException e1)
		{
			logger.error(e1.getMessage());
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			try
			{
				httpclient.close();
			}
			catch (IOException e)
			{
				logger.error(e.getMessage());
			}
		}
		return result;
	}



}
