package Algorithm.mySort;

import java.util.HashMap;

/**
 * 排序算法
 */
public class Sort {

    //冒泡排序：
    public static void sort(int[] arr) {


        if (arr == null || arr.length == 0) {
            return;
        }
        //冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {  //控制排序的次数
            for (int j = 0; j < arr.length - 1 - i; j++) { //控制内部排序
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


    //1、归并算法
    //归并算法，分解-合并
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        //对元素的下标进行分解
        mergeSort(arr, 0, arr.length - 1);
    }

    //
    private static void mergeSort(int[] arr, int left, int right) {
        //递归出口，如果相等，不对数组进行操作性，原数组还是本身
        if (left >= right) {
            return;
        }
        //left和right是数组下标
        int mod = (left + right) / 2;
        mergeSort(arr, left, mod);
        mergeSort(arr, mod + 1, right);
        //当一个元素的时候，返回空，我拿到的left和right是2个元素
        mergeSort(arr, left, mod, right);
    }

    /**
     * 合并逻辑
     *
     * @param arr
     * @param left  数组A最左得知
     * @param mod   数组A最右下标
     * @param right 数组B的最右下标
     */
    private static void mergeSort(int[] arr, int left, int mod, int right) {
        int i = left;     //数组A指针
        int j = mod + 1;  //数组B指针
        int k = 0;
        int[] temp = new int[right - left + 1];  //数组初始化长度
        while (i <= mod && j <= right) {    //小于等于下标
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mod) {
            temp[k++] = arr[i++];
        }
        //分别将剩余元素放入到数组中
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //数据复制完毕
        for (int len = 0; len < temp.length; len++) {
            arr[left + len] = temp[len];
        }
    }

    //给定一个排序数组，你需要在原地删除重复出现的元素，
    // 使得每个元素只出现一次，返回移除后数组的新长度。修改前X个元素的值
    public static int removeDuplicates(int[] nums) {

        //双指针比较，当遇到第一个不同的值时，赋予给nums[2]
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[n] != nums[i]) {
                //第一个值必须保留！
                nums[++n] = nums[i];  //i的值赋予给j
            }
        }
        return n + 1;
    }


}
