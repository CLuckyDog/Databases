package com.databases.databases.config;

import com.databases.databases.DatabasesApplication;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.databases.databases.dao.two", sqlSessionFactoryRef = "twoSqlSessionFactory", nameGenerator = DatabasesApplication.SpringBeanNameGenerator.class)
public class TwoDataSourceConfig {
    @Autowired
    @Qualifier("dataSourceTwo")
    private DataSource dataSourceTwo;

    @Bean(name = "twoTransactionManager")
    public DataSourceTransactionManager twoTransactionManager() {
        return new DataSourceTransactionManager(dataSourceTwo);
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory twoSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceTwo);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/two/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.databases.databases.domain.two");

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否分页合理化
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{interceptor});

        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate twoSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(twoSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }
}
