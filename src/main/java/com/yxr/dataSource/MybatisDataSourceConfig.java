package com.yxr.dataSource;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * //4、将Spring和mybatis整合（自动扫描对象映射关系）
 * <bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
 *   <property name="mapperInterface" value="org.mybatis.spring.sample.mapper.UserMapper" />
 *   <property name="sqlSessionFactory" ref="sqlSessionFactory" />
 * </bean>
 */
@Configuration
@MapperScan(basePackages = "com.winter.mapper",sqlSessionFactoryRef = "mybatisSqlSessionFactrory")
public class MybatisDataSourceConfig {

    //开启日志文件
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //配置数据源
    //读取配置文件中，以spring.datasource.emp为前缀的配置信息
    @ConfigurationProperties(prefix = "spring.datasource.emp")
    @Primary  //标记为主数据库，
    @Bean("mybatisDataSource")
    public DruidDataSource getDruidDS() {
        logger.info("----------initMybatisDataSource-------");
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
    //按照Spring+mybatis整合流程，其实就是声明sqlSessionFactory对象

    //3、创建SqlSessionFactory

    /**
     *     创建会话工厂（管理数据源，sql映射文件，mybatis的配置文件）
     *  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     *      <property name="dataSource" ref="datasource"></property>
     *      <property name="typeAliasesPackage" value="com.zhangguo.bookstore.entities"></property>
     *      <property name="mapperLocations" value="classpath*:com/zhangguo/bookstore/mapper/*Mapper.xml"></property>
     *  </bean>
     */
    @Bean("mybatisSqlSessionFactrory")
    @Primary   //声明为主库
    public SqlSessionFactory myBatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //扫描Mapper.xml文件
        bean.setMapperLocations(
                new
                        PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapping/*.xml"));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //2、声明事务管理（注解方法）
//    @Bean("myBatisDataSourceTransactionManager")
//    @Primary
//    public DataSourceTransactionManager myBatisDataSourceTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }


    /**
     * 声明sqlSessionTemplate模板类代码
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "myBatisSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate myBatisSqlSessionTemplate(@Qualifier("mybatisSqlSessionFactrory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }
}
