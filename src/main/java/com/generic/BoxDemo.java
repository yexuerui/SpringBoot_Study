package com.generic;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo {

    public  BoxDemo() {
    }

    //定义了一个名为U的类型参数、通常，Java编译期可以推断
    public static <U> void addBox(U u, List<TestGenerics.Box<U>> boxes) {
        TestGenerics gen = new TestGenerics();
        TestGenerics.Box<U> box = gen.new Box<>();
        box.setT(u);
        boxes.add(box);
    }
    public static <U> void outputBoxes(List<TestGenerics.Box<U>> boxes) {
        int count = 0;
        for (TestGenerics.Box<U> box : boxes) {
            U t = box.getT();
            System.out.println("Box #" + count + " contains [" + t.toString() + "]");
            count++;
        }
    }
    //测试方法
    public static void main(String[] args) {
        List<TestGenerics.Box<Integer>> listOfIntegerBoxes = new ArrayList<TestGenerics.Box<Integer>>();
        //首先这个才是泛型方法的完全体
        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);
    }
}

