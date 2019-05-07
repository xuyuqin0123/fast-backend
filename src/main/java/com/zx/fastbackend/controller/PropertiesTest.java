package com.zx.fastbackend.controller;

import com.zx.fastbackend.utils.ResBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author xuyuqin
 * @create 2019-05-07 11:22
 **/
@RestController
public class PropertiesTest {
    @Autowired
    DataSource dataSource;
    @GetMapping(value = "properties/test")
    public ResBean propertiesTest(){
        return ResBean.success(dataSource.getClass().toString());
    }
}
