package com.zx.fastbackend.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyuqin
 * @create 2019-05-09 20:18
 **/
public class HttpUtils {
    public static void responseWrite(ResBean resBean, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, String> map = new HashMap<>();
        writer.write(JSONObject.toJSONString(resBean));
    }
}
