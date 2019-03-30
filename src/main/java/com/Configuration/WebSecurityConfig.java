package com.Configuration;

import com.Interceptor.RedisSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    //将拦截器加入到Spring容器中
    @Bean
    public RedisSessionInterceptor getSessionInterception() {
        return new RedisSessionInterceptor();
    }

    //新增拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //所有以api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，
        // 并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        // 必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(getSessionInterception()).addPathPatterns("/api/**").excludePathPatterns("/api/user/login");

    }
}
