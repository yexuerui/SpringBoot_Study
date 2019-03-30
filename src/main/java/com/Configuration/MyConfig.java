package com.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    /**
     * 增加视图控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("form").setViewName("form");
    }

    /**
     * 自定义格式化器
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public String print(Date date, Locale locale) {
                return null;
            }

            @Override
            public Date parse(String s, Locale locale) throws ParseException {
                return new SimpleDateFormat("yyyy-MM-dd").parse(s);
            }
        });
    }

    //注册localResolver
    //@Configuration
//    @Bean
//    public LocaleResolver localeResolver() {
//        return new MyLocaleResolver();
//    }

    //修改区域拦截器,SpringBoot中的方法名相当于bean的name，也有固定格式
    @Bean
    public SessionLocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        //设置默认的区域
        sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
        return sessionLocaleResolver;
    }
    //注册拦截器
    @Bean
    public LocaleChangeInterceptor LocaleChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        System.out.println("----->"+localeChangeInterceptor.getParamName());
        localeChangeInterceptor.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        return localeChangeInterceptor;
    }
    //加入到自定义拦截器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LocaleChangeInterceptor());
    }


}
