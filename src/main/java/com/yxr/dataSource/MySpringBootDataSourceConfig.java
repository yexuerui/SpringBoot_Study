package com.yxr.dataSource;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.run.mapper.UserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置事务管理
 */
@Configuration
//扫描Mapper
@MapperScan(basePackages = "com.run.mapper",sqlSessionFactoryRef = "mySpringbootSqlSessionFactrory",
        sqlSessionTemplateRef = "mySpringbootSqlSessionTemplate")
public class MySpringBootDataSourceConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ConfigurationProperties(prefix = "spring.datasource.usr")
    @Bean("mySpringbootDataSource")
    public DruidDataSource getDruidDS() {
        logger.info("----------initMySpringbootDataSource-------");
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "root");
        initParams.put("allow", "");//默认就是允许所有访问
        initParams.put("deny", "192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean;
        bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    //@Qualifier()接口实现类有多个时，指定合格者
    //创建SqlSessionFactory
    @Bean("mySpringbootSqlSessionFactrory")
    public SqlSessionFactory myBatisSqlSessionFactory(@Qualifier("mySpringbootDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //手动配置代码：classpath:mapping2/*.xml
        bean.setMapperLocations(
                new
                        PathMatchingResourcePatternResolver()
                        .getResources("classpath:/mapping2/*.xml"));

        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //创建TransactionManager
    @Bean("mySpringbootDataSourceTransactionManager")
    public DataSourceTransactionManager myBatisDataSourceTransactionManager(@Qualifier("mySpringbootDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mySpringbootSqlSessionTemplate")
    public SqlSessionTemplate myBatisSqlSessionTemplate(@Qualifier("mySpringbootSqlSessionFactrory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }
}
