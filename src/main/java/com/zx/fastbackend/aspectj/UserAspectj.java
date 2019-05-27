package com.zx.fastbackend.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuyuqin
 * @create 2019-05-13 23:31
 **/
@Aspect
@Configuration
public class UserAspectj {

    @Pointcut("execution(* com.zx.fastbackend.controller.*.*(..))")
    private void pointcut(
    ){
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("user aspect before");
    }


}
