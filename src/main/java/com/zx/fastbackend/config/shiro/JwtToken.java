package com.zx.fastbackend.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xuyuqin
 * @create 2019-04-27 21:49
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
