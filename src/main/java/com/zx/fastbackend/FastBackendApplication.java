package com.zx.fastbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zx.fastbackend.dao"})
public class FastBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastBackendApplication.class, args);
    }

}
