package com.zx.fastbackend.service;

import com.zx.fastbackend.entity.SysUser;
import com.zx.fastbackend.exception.CustomException;
import com.zx.fastbackend.utils.TokenGenerater;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xuyuqin
 * @create 2019-04-28 16:42
 **/
@Service
public class UserService {

    @Autowired
//    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private static final String salt="dskjdskdjsldksldksljdksdjks";

    public String getToken(String username, String password) throws CustomException {
        SysUser user= getUserByUsername(username);
        if(user==null){
            throw new CustomException("login info error");
        }
        String encryptPassword=new Md5Hash(password,user.getSalt() ).toBase64();
        if(user.getPassword()!=null&&user.getPassword().equals(encryptPassword)){
             return TokenGenerater.generateToken();
        }else{
            throw new CustomException("login info error");
        }
    }

    public SysUser getUserByUsername(String username) {
        SysUser user = jdbcTemplate.query("select*from sys_user where username='" + username+"'", preparedStatement -> {
            SysUser userTemp = new SysUser();
            if(preparedStatement.next()){
                userTemp.setUsername(preparedStatement.getString("username"));
                userTemp.setPassword(preparedStatement.getString("password"));
                userTemp.setSalt(preparedStatement.getString("salt"));
                return userTemp;
            }else{
                return null;
            }

        });
        return user;
    }

    public SysUser save(SysUser user) throws CustomException {
        if(getUserByUsername(user.getUsername())!=null){
            throw new CustomException("already register");
        }else{
            String password=new Md5Hash(user.getPassword(),salt).toBase64();
            System.out.println("insert into sys_user(username,password,salt) values ('"+user.getUsername()+"','"+password+"','"+salt+"')");
           jdbcTemplate.update("insert into sys_user(username,password,salt) values ('"+user.getUsername()+"','"+password+"','"+salt+"')");
        }
        return user;
    }

}
