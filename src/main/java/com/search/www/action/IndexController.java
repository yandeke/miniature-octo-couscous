package com.search.www.action;

import com.search.www.util.Constance;
import com.search.www.util.HttpClientUtil;
import com.search.www.util.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yandeke on 2016/7/31.
 */
@Controller
public class IndexController extends BaseController
{
    Logger logger = Logger.getLogger(IndexController.class);
    @RequestMapping(value="index")
    public String toLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        return "index";
    }

    @RequestMapping("searchContent")
    @ResponseBody
    public void searchContent(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        String keyWords = HttpRequestUtil.getStringParames(request,"keyWords",null);
        if(StringUtils.isNotEmpty(keyWords)){
            String str = HttpClientUtil.getForm(Constance.BAIDU_URL+keyWords);
            System.out.println(str);
            String str2 = HttpClientUtil.getForm(Constance.GUGE_URL+keyWords);
            System.out.println(str2);
        }
//        JsonUtil.jsonResponse();
    }
}
