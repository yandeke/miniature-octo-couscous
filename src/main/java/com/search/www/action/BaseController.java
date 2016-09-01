package com.search.www.action;

import com.search.www.util.Constance;
import com.search.www.util.CookieUtil;
import com.search.www.util.DesUtil;
import com.search.www.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    private static final String COOKIENAME = "SEARCH";

    public String readCookieForUsername(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String encode_str = CookieUtil.readCookie(request, response, "COOKIENAME");
        if(StringUtil.isEmpty(encode_str))
            return null;
        return DesUtil.decrypt(encode_str, Constance.KEY);
    }

    public int writeCookieForUsername(HttpServletRequest request,HttpServletResponse response, String username) throws Exception {
        String encode_str = DesUtil.encrypt(username, Constance.KEY);
        CookieUtil.writeCookie(request, response, COOKIENAME, encode_str, 60 * 30);
        return 0;
    }
}