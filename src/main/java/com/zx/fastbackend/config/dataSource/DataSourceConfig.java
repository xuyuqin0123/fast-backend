package com.zx.fastbackend.config.dataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author xuyuqin
 * @create 2019-05-07 11:16
 **/

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @Qualifier("primayProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties primayProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("secondProperties")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSourceProperties secondProperties(){
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    @Qualifier("datasource")
    public DataSource primayDataSource(DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("second-datasource")
    public DataSource secondDataSource(@Qualifier("secondProperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
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
