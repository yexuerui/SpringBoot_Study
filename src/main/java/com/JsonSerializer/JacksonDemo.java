package com.JsonSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JacksonDemo {

    //测试代码
    public static void main(String[] args) {
        //创建用户对象
        User user = new User();
        user.setName("小胖");
        List sports = new ArrayList<>();
        sports.add("足球");
        sports.add("游泳");
        user.setSports(sports);
        user.setDate(LocalDate.now());
        //创建对象
        //Json序列化和反序列化
        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        mapper.registerModule(new JavaTimeModule());
        //User转化为Json
        try {
            //对象转化为Json数据
            String s = mapper.writeValueAsString(user);
            System.out.println("s：" + s);
            //Json转化为对象
            User user1 = mapper.readValue(s, User.class);
            System.out.println(user1.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
