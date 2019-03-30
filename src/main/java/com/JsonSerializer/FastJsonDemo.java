package com.JsonSerializer;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FastJsonDemo {

    private static final String OBJ_JSON = "{\"studentName\":\"lily\",\"studentAge\":12}";
    private static final String ARR_JSON = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    private static final String COMPLEX_JSON = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    public static void main(String[] args) {

        User user = new User();
        user.setName("小胖");
        List sports = new ArrayList<>();
        sports.add("皮球");
        sports.add("游泳");
        sports.add("篮球");
        user.setSports(sports);
        user.setDate(LocalDate.now());

        String toJSONString = JSONObject.toJSONString(user);
        System.out.println(toJSONString);


//        Student stu = new Student();
//        stu.setStudentAge(null);
//        stu.setStudentName("黎明");
//        String toJSONString = JSONObject.toJSONString(stu,
//                SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteNullStringAsEmpty,  //如果字符串是空，那么返回""，而非不返回
//                SerializerFeature.WriteNullNumberAsZero,  //如果字符串是空，那么返回0，而不是不返回、
//                SerializerFeature.WriteClassName,     //序列化时写入类型信息，反序列化时用到
//                SerializerFeature.DisableCircularReferenceDetect); //消除对同一对象循环引用问题。
//        System.out.println(toJSONString);


//        Type type = new TypeReference<ArrayList<Student>>() {
//        }.getType();
//        List<Student> list = JSONObject.parseObject(ARR_JSON, type);
//        System.out.println(list);

//        List<Student> students = JSONObject.parseArray(ARR_JSON, Student.class);
//        System.out.println(students);
//
//        JSONObject.toJSONString(students, SerializerFeature.PrettyFormat);

//        Object[] o = new Object[]{"1", 2, (short) 3, 4l, 5.0d, 6.0f, 0.3d, 0.3f};
        //Object转换为JSON
//        String s = JSONObject.toJSONString(o);
//        String s1 = JSONObject.toJSONString(o, SerializerFeature.BeanToArray);
//        System.out.println("普通转换后的JSON：" + s);
//        System.out.println("模式转换后的JSON：" + s1);

        //        JSONObject jsonObject = JSONObject.parseObject(ARR_JSON);


        //为什么使用TypeReference类型，因为是需要将泛型也序列化
//        Type type = new TypeReference<Teacher<String>>() {
//        }.getType();
//        Teacher<String> teacher = JSONObject.parseObject(COMPLEX_JSON, type);
//
//        Teacher teacher = JSONObject.parseObject(COMPLEX_JSON, Teacher.class);
//        System.out.println(teacher);
//        Object[] o = new Object[]{"1", 2, (short) 3, 4l, 5.0d, 6.0f, 0.3d, 0.3f};
//        System.out.println("转换之前...");
//        for (Object obj : o) {
//            System.out.println(obj.getClass().getName());
//        }
//        String s = JSONObject.toJSONString(o, SerializerFeature.WriteClassName);
//        System.out.println(s);
//        JSON parse = (JSON) JSONObject.parse(s);
//        System.out.println("转换之后...");
//        System.out.println(parse);
//        for (Object obj : parse) {
//            System.out.println(parse.getClass().getName());
//        }

    }
}
