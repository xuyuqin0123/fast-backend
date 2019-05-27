package com.zx.fastbackend.config.shiro;

import com.zx.fastbackend.utils.HttpUtils;
import com.zx.fastbackend.utils.ResBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            HttpUtils.responseWrite(ResBean.fail("unauthorized request"), response);
            return false;
        } else {
            return executeLogin(request, response);
        }
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String tokenCode=httpRequest.getHeader("token");
        JwtToken token = new JwtToken(tokenCode);
        if (tokenCode==null) {
            String msg = "";
            throw new AuthenticationException("no token");
        } else {
            try {
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                return true;
            } catch (Exception var) {
                System.out.println(var);
                try {
                    HttpUtils.responseWrite(ResBean.fail("error token"), (HttpServletResponse) response);
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
