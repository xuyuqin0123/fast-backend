package com.zx.fastbackend.config.shiro;


import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xuyuqin
 * @create 2019-04-24 22:36
 **/
@Configuration
public class ShiroConfig {


    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JWTFilter());

        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> urlFilter = new LinkedHashMap<>();
        urlFilter.put("/user/signin", "anon");
        urlFilter.put("/user/register", "anon");
        urlFilter.put("/helloworld","anon" );
//        urlFilter.put("/**", "jwt");
        urlFilter.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlFilter);
        return shiroFilterFactoryBean;
    }

}
