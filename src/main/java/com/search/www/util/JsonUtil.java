package com.search.www.util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
/**
 * json数据
 * @author yandeke
 *
 */
public class JsonUtil {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 把一个对象转换成json字符�?
     * @param object
     * @return
     */
    public static String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * 把json字符串转换成�?个对�?
      * @param json
     * @param clazz 对象类型
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> clazz){
       return gson.fromJson(json,clazz);
    }

    /**
     * 支持列表类型转换 比如：TypeToken<List<Person>>(){}.getType()
     * @param json
     * @param type  new TypeToken<List<Person>>(){}.getType()
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Type type){
        return gson.fromJson(json,type);
    }


    public static void jsonResponse(HttpServletResponse response,int code,String msg, Object obj){
        jsonResponse(response,"utf-8",code,msg,obj);
    }

    public static void jsonResponse(HttpServletResponse response,String encode,int code,String msg, Object obj){
        response.setContentType("application/json");
        response.setCharacterEncoding(encode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", String.valueOf(code));
        map.put("msg", msg);
        map.put("data", obj);
        try {
            response.getWriter().write(JsonUtil.toJson(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
