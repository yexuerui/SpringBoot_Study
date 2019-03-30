package Algorithm.Test;

import Algorithm.Search;

/**
 * 测试搜索算法
 */
public class TestSearch {
    public static void main(String[] args) {
        int[] arr = {0,2};
        boolean binSearch = Search.binSearch1(arr, 2);
        System.out.println(binSearch);
    }

}
