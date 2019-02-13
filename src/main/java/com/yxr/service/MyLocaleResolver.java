package com.yxr.service;

import com.sun.corba.se.spi.orbutil.closure.Closure;
import com.sun.corba.se.spi.resolver.LocalResolver;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Object;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Set;

/**
 * SpringMVC原理： LocaleChangeInterceptor 拦截器将locale信息转化为LocaleResolver对象，
 * 将其放入SessionLocaleResolver对象中，应用程序便可获取到信息
 *
 * SpringBoot原理：  实现的LocaleResolver接口，完成切换国际化
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String param=httpServletRequest.getParameter("locale");
        //获取默认的本地化信息
        Locale locale = Locale.getDefault();
        //获取国际化后缀
        if(StringUtils.isNotEmpty(param)){
            //截取字符串
            String[] srcs = param.split("_");
            locale = new Locale(srcs[0],srcs[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
