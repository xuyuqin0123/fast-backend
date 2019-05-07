package com.zx.fastbackend.config.dataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author xuyuqin
 * @create 2019-05-07 11:16
 **/

@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    @Qualifier("datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primayDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("second-datasource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @Qualifier("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Qualifier("secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("second-datasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
