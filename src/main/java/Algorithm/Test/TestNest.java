package Algorithm.Test;

public class TestNest {
    private String abc;
    public String getAbc() {
        return abc;
    }
    public static String nestSta() {
        TestNest testNest = new TestNest();
        return testNest.getAbc();
    }
}
