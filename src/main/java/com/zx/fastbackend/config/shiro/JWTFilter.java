package com.zx.fastbackend.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.zx.fastbackend.utils.ResBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyuqin
 * @create 2019-04-25 14:43
 **/

public class JWTFilter extends AuthenticatingFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            responseWrite(ResBean.fail("unauthorized request"), response);
            return false;
        } else {
            return executeLogin(request, response);
        }
    }

    private void responseWrite(ResBean resBean, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, String> map = new HashMap<>();
        writer.write(JSONObject.toJSONString(resBean));
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        JwtToken token = new JwtToken(httpRequest.getHeader("token"));
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        } else {
            try {
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                return true;
            } catch (Exception var) {
                System.out.println(var);
                try {
                    responseWrite(ResBean.fail("error token"), (HttpServletResponse) response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }
}
