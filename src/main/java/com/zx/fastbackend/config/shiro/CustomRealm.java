package com.zx.fastbackend.config.shiro;

import com.zx.fastbackend.dao.TokenDao;
import com.zx.fastbackend.dao.UserDao;
import com.zx.fastbackend.entity.SysUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    UserDao userDao;

    @Autowired
    TokenDao tokenDao;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String userId = tokenDao.getUserIdByToken(token);
        if (userId == null) {
            throw new AuthenticationException("token error");
        }
        SysUser user = userDao.getUserById(userId);

        return new SimpleAuthenticationInfo(user,token, getName());
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