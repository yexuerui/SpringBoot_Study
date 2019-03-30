package com.Interceptor;

import com.yxr.enums.RedisSessionResp;
import com.yxr.enums.RespStatusCode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * 定义Spring-mvc 拦截器
 * */
public class RedisSessionInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //获取Session请求
//        HttpSession session = request.getSession();
//        if (session.getAttribute("loginUserId") != null) {
//            //在Redis中查询是否包含和这个用户信息
//            try {
//                String loginSessionId = redisTemplate.opsForValue().get("loginUser:" +
//                        (long) session.getAttribute("loginUserId"));
//                if (session != null && loginSessionId.equals(session.getId())) {
//                    return true;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//
//    //返回错误页面
//    private void response401(HttpServletResponse response) {
//        //编码值
//        response.setCharacterEncoding("UTF-8");
//        //返回类型
//        response.setContentType("application/json; charset=utf-8");
////        RedisSessionResp.build(RespStatusCode.FAILED.getStatus(),RespStatusCode.FAILED.getMessage());
////        JSONObject a = new JSONObject();
//        JSONObject jsonObject = JSONObject.fromObject(RedisSessionResp.build(RespStatusCode.FAILED.getStatus(), RespStatusCode.FAILED.getMessage()));
//        try {
//            response.getWriter().println(jsonObject);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
