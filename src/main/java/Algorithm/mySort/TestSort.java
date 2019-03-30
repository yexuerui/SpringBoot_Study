package Algorithm.mySort;


public class TestSort {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 5, 2, 7, 0, 4};

        Sort.sort(arr);
//        Sort.removeDuplicates(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }


    }


}
