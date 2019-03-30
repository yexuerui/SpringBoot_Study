package Algorithm.Test;

import Algorithm.Solution;

import java.util.List;

public class TestSort {

    //参数条件：1 ≤ a[i] ≤ n （n为数组长度）
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 190, 2, 3, 1};

        List<Integer> list = Solution.findDuplicates(arr);
        for (Integer i : list) {
            System.out.println(i);
        }

//        Sort.meSort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
    }
}
