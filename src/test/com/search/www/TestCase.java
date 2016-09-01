package com.search.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class TestCase {

    @Test
    public void testGetRequest() throws IllegalStateException, IOException {
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        // Create a method instance.
        GetMethod method = new GetMethod("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=monline_3_dg&wd=%E5%93%87%E5%93%87%E5%93%87%E5%93%87&rsv_pq=9a2e36da000123a7&rsv_t=bd65%2F9inC8Ic5v8JjYy2w%2Bl5VMs5l2rgRw5Pc3dAycgIY3J3fcgF1etcgvL%2FeHeXH6TX&rqlang=cn&rsv_enter=0&rsv_sug3=2&rsv_sug1=1&rsv_sug7=100&inputT=1151&rsv_sug4=1254");
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);
            System.out.println(statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
            } else {
                System.err.println("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
            if (ins != null) {
                ins.close();
            }
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testPostRequest() throws HttpException, IOException {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://www.baidu.com/getValue");
        method.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gb2312");
        NameValuePair[] param = { new NameValuePair("age", "11"),
                new NameValuePair("name", "jay"), };
        method.setRequestBody(param);
        int statusCode = client.executeMethod(method);
        System.out.println(statusCode);
        method.releaseConnection();
    }

    public static void test3() throws Exception{
        String key = "java"; //²éÑ¯¹Ø¼ü×Ö
        key = URLEncoder.encode(key, "gbk");
        URL u = new URL("http://www.baidu.com.cn/s?wd=" + key + "&cl=3");
        URLConnection conn = u.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "gbk"));
        String str = reader.readLine();
        while (str != null) {
            System.out.println(str);
            str = reader.readLine();
        }

        reader.close();
    }

    public static void main(String[] args) throws Exception{
//        try {
//            new TestCase().testGetRequest();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        test3();
    }
}