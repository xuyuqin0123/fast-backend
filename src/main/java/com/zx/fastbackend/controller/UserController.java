package com.zx.fastbackend.controller;

import com.zx.fastbackend.dao.TokenDao;
import com.zx.fastbackend.dao.UserDao;
import com.zx.fastbackend.entity.SysToken;
import com.zx.fastbackend.entity.SysUser;
import com.zx.fastbackend.exception.CustomException;
import com.zx.fastbackend.service.UserService;
import com.zx.fastbackend.utils.ResBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyuqin
 * @create 2019-04-25 11:46
 **/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @Autowired
    TokenDao tokenDao;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResBean login(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");
        try {
            String token = userService.getToken(username, password);
            Map<String, String> content = new HashMap<>();
            content.put("token", token);
            return ResBean.success("200", "login success", content);
        } catch (CustomException e) {
            return ResBean.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResBean register(@RequestBody SysUser user) {
        try {
            SysUser userTemp = userService.save(user);
            return ResBean.success(userTemp);
        } catch (CustomException e) {
            return ResBean.fail("register info error");
        }
    }


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResBean getUser(@PathVariable String username) {
        SysUser user = userDao.getUserByUsername(username);
        return ResBean.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        Subject user = SecurityUtils.getSubject();
        user.logout();
        return "success";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String token() {
        SysToken sysToken = new SysToken();
        sysToken.setToken("xxxxxx");
        sysToken.setUserId("xxxxxx");
        tokenDao.post(sysToken);
        return "success";
    }

}
