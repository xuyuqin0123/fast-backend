package com.zx.fastbackend.service;

import com.zx.fastbackend.dao.TokenDao;
import com.zx.fastbackend.dao.UserDao;
import com.zx.fastbackend.entity.SysToken;
import com.zx.fastbackend.entity.SysUser;
import com.zx.fastbackend.exception.CustomException;
import com.zx.fastbackend.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xuyuqin
 * @create 2019-04-28 16:42
 **/
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TokenDao tokenDao;


    public String getToken(String username, String password) throws CustomException {
        SysUser user = getUserByUsername(username);
        if (user == null) {
            throw new CustomException("login info error");
        }

        if (user.getPassword() != null && user.getPassword().equals(JWTUtils.encrypt(password, user.getSalt()))) {
            String token = JWTUtils.generateToken();
            SysToken sysToken = new SysToken();
            sysToken.setUserId(user.getId());
            sysToken.setToken(token);
            tokenDao.post(sysToken);
            return token;
        } else {
            throw new CustomException("login info error");
        }
    }

    public SysUser getUserByUsername(String username) {
        SysUser user = jdbcTemplate.query("select*from sys_user where username='" + username + "'", preparedStatement -> {
            SysUser userTemp = new SysUser();
            if (preparedStatement.next()) {
                userTemp.setId(preparedStatement.getString("id"));
                userTemp.setUsername(preparedStatement.getString("username"));
                userTemp.setPassword(preparedStatement.getString("password"));
                userTemp.setSalt(preparedStatement.getString("salt"));
                return userTemp;
            } else {
                return null;
            }

        });
        return user;
    }

    public SysUser save(SysUser user) throws CustomException {
        if (getUserByUsername(user.getUsername()) != null) {
            throw new CustomException("already register");
        } else {
            String salt = JWTUtils.generateSalt();
            String password = JWTUtils.encrypt(user.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(password);
            userDao.post(user);
        }
        return user;
    }

}
