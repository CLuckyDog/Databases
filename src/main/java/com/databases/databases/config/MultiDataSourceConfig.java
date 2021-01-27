package com.databases.databases.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {
    @Primary
//    @Bean(initMethod = "init",name="dataSourceOne")
    @Bean(name="dataSourceOne")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean(initMethod = "init",name="dataSourceTwo")
    @Bean(name="dataSourceTwo")
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name="dataSourceThree")
    @ConfigurationProperties("spring.datasource.druid.three")
    public DataSource dataSourceThree(){
        return DruidDataSourceBuilder.create().build();
    }
}
