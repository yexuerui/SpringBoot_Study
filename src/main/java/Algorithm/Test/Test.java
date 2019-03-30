package Algorithm.Test;

public class Test {
    //类的常量
    private final String str = new String();
    //类的静态变量
    private static String a = new String();
    


    public static void main(String[] args) {
        Integer integer = new Integer(1);
        Test.testInterger(integer);
        System.out.println(integer);

        Double aDouble = new Double(1);
        Test.testDouble(aDouble);
        System.out.println(aDouble);

    }

    public static void testInterger(Integer i) {
        i--;
    }

    public static void testDouble(Double i) {
        i--;
    }

}
