package com.search.www.action;

import com.search.www.util.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    /*    session.removeAttribute("username");// 清除session中的对象
        try {
            CookieUtil.clearCookie(request, response);//清除客户端cookie
        } catch (Exception e) {
            logger.error("清除cookie失败" +e.getMessage());
        }*/
        return "/index";
    }
}
