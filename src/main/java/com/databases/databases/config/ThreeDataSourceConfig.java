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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.databases.databases.dao.three", sqlSessionFactoryRef = "threeSqlSessionFactory",nameGenerator = DatabasesApplication.SpringBeanNameGenerator.class)
public class ThreeDataSourceConfig {

    @Autowired
    @Qualifier("dataSourceThree")
    private DataSource dataSourceThree;

    @Bean(name = "threeTransactionManager")
    @Primary
    public DataSourceTransactionManager threeTransactionManager() {
        return new DataSourceTransactionManager(dataSourceThree);
    }

    @Bean(name = "threeSqlSessionFactory")
    @Primary
    public SqlSessionFactory threeSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceThree);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/three/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.databases.databases.domain.three");

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        /*设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL,sqlserver*/
        properties.setProperty("helperDialect", "sqlserver");
        //是否分页合理化
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{interceptor});

        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate threeSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(threeSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }

}
