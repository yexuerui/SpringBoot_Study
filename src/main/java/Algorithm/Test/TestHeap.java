package Algorithm.Test;

import Algorithm.Heap;

public class TestHeap {
    public static void main(String[] args) {
        int[] arr = {87,45,78,32,17,65,53,9,122,1};
        System.out.println("length ："+arr.length);
        int[] maxHeap = Heap.createMaxHeap(arr);
        for (int i = 0; i <maxHeap.length ; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
