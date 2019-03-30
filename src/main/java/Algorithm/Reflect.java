package Algorithm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Reflect {
    public static void main(String[] args) {
        //创建map对象
        Map<String, Object> map = new HashMap();
        //创建class对象
        Class<? extends Map> aClass = map.getClass();
        try {
            //获取Fileld域信息
            Field min_treeify_capacity = aClass.getDeclaredField("MIN_TREEIFY_CAPACITY");
            System.out.println("反射代码：" + min_treeify_capacity);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}