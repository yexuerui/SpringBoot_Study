package com.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Aspect
@Component
public class ControllerLog {
    //日志处理
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //设置切入点(拦截被RestController注解修饰的类)
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void myPointcut() {

    }

    @Around("myPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        long beginTime = System.currentTimeMillis();  //开始时间
        //[ˈhəʊldə(r)]  在隐藏的上下文中，获取RequestContext对象
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String uri = requestAttr.getRequest().getRequestURI();
        logger.info("当前请求的uri : " + uri);
        logger.info("开始计时: {}  URI: {}", new Date(), uri);
        //获取上下文参数
        Object[] args = joinPoint.getArgs();
        //获取方法名
        String name = joinPoint.getSignature().getName();
        logger.info("请求方法：{}，请求参数：{}" + name, args);
        //可能在反向代理请求进来时，获取的IP存在不正确行 这里直接摘抄一段来自网上获取ip的代码
        logger.info("请求ip：{}", getIpAddr(requestAttr.getRequest()));
        //方法注解
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            logger.error("暂不支持非方法注解 : " + signature);
            throw new IllegalArgumentException("暂不支持非方法注解");
        }
        //调用实际方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取执行的方法
        MethodSignature methodSign = (MethodSignature) signature;
        Method method = methodSign.getMethod();

        //判断是否需要打印日志
        Log logAnno = AnnotationUtils.getAnnotation(method, Log.class);
        if (logAnno != null && logAnno.ignore()) {
            return proceed;
        }
        if (logAnno != null) {
            logger.info("log注解描述：{}", logAnno.desc());
            long endTime = System.currentTimeMillis();
            logger.info("计时结束：{}，URI:{}，耗时:{}", new Date(), uri, endTime - beginTime);
        }
        return proceed;
    }

    /**
     * 转至：https://my.oschina.net/u/994081/blog/185982
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        logger.error("获取ip异常：{}", e.getMessage());
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

}
