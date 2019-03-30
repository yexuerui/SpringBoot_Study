package Algorithm.Test;

import java.util.ArrayList;
import java.util.List;
public class TestJVM {
    static class OOMObject {
        private String name;
        public OOMObject(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject("小胖同学"));
        }
    }
}

