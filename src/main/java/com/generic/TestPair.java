package com.generic;

public class TestPair {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new OrderPair<>(1, "apple");
        Pair<Integer, String> p2 = new OrderPair<>(2, "pear");
        boolean compare = OrderPair.compare(p1, p2);
    }
}
