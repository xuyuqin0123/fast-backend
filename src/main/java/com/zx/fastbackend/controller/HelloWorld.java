package com.zx.fastbackend.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xuyuqin
 * @create 2019-04-24 22:18
 **/
@RestController
@RequestMapping("/")
public class HelloWorld {
    @RequestMapping(value = "/helloworld",method = RequestMethod.GET)
//    @RequiresAuthentication
    public String say(){
        return "hello world";
    }

    @RequestMapping(value = "/helloworld2",method = RequestMethod.GET)
//    @RequiresRoles("user")
    public String say2(){
        Subject subject=SecurityUtils.getSubject();
        PrincipalCollection principalCollection= subject.getPrincipals();
        System.out.println();
        return "hello world";
    }
}
