package com.zx.fastbackend.config.shiro;

import com.zx.fastbackend.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yorke
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token= (JwtToken) authenticationToken;
        String username = "xuyuqin";
        String password = "123456";
        SysUser user=new SysUser();
        user.setUsername(username);
        user.setMobile("13026086597");
        return new SimpleAuthenticationInfo(user,token.getPrincipal(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("user");
        authorizationInfo.setRoles(roles);
        authorizationInfo.addStringPermissions(roles);
        return authorizationInfo;
    }
}