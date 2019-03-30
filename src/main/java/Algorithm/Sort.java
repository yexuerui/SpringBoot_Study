package Algorithm;

/**
 * 经典排序算法
 *
 * @param <quickSort>
 */
public class Sort<quickSort> {
    /**
     * 冒泡排序：相邻的两个数比较，交换
     * n个数 要排序n-1次（最后一个数不排序），每次排序要比较n-i次,；
     */
    public static void BubbleSort(int[] src) {
        if (src == null || src.length == 0) {
            throw new RuntimeException("数组不能为null或者空");
        }
        int length = src.length;
        int temp = 0;
        //外层掌握排序的循环次数，内层掌握每次排序比较的次数
        //坑，在代码中，使用j++，运算，导致的是原始值，
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                //比较交换（CAS）
                if (src[j] > src[j + 1]) {
                    temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 挖坑填数
     * （1）对数组进行排序，左边都比基数小，右边都比基数大，返回基数坐标
     *
     * @param src 原始数组
     * @param l   最小值
     * @param r   最大值
     * @return 排序后的数组
     */
    private static int AdjustArray(int[] src, int l, int r) {
        int i = l, j = r;
        int x = src[i];   //将基数挖出
        while (i < j) {
            while (i < j && src[j] > x)
                j--;
            if (i < j) {   //走到这个分支一定是src[j]<x
                src[i] = src[j];
            }
            while (i < j && src[i] < x)
                i++;
            if (i < j) {
                src[j] = src[i];
            }
        }
        //退出时，将基数填入最后的坑；
        src[i] = x;
        return i;   //对数组排序完成，返回中间值
    }

    /**
     * 分治法
     * 每次对数组进行排序;不对i进行排序
     *
     * @param src
     * @param l
     * @param r
     */
    private static void quickSort1(int src[], int l, int r) {
        if (l < r) {
            int i = AdjustArray(src, l, r); //不对i进行排序
            quickSort1(src, l, i - 1);
            quickSort1(src, i + 1, r);
        }
    }

    public static void quickSort(int[] src) {
        quickSort1(src, 0, src.length - 1);
    }

    /**
     * 堆排序
     *
     * @param src 待排序的array
     */
    public static void heapSort(int src[]) {
        //i最后一个非叶子节点
        for (int i = src.length / 2 - 1; i >= 0; i++) {
            adjustHeap(src, i, src.length);
        }
    }

    private static void adjustHeap(int[] src, int i, int length) {
        //将该节点数据，因为当前元素可能一直要移动
        int temp = src[i];  //此时i-3。2i+1 左节点；2i+2 右节点；
        //k是7
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //k子节点中最大的节点
            if (k + 1 < length && src[k] < src[k + 1]) {
                k++;
            }
            //如果子节点更大，则进行值的交换
            if (src[k] > temp) {
                //i 是父节点，k是较大的叶子节点
                swap(src, i, k);
                //若是子节点更换了，那么以子节点为根的子树会不会受到影响
                //所以，还要开启循环，对改子树进行判断
                i = k;  //此时要考虑该位置的子节点对子树的影响；
            } else {
                //如果不用交换，那么直接终止循环
                break;
            }
        }
    }

    /**
     * 将a[i]和a[j]交换位置
     *
     * @param src 原数组
     * @param i
     * @param j
     */
    private static void swap(int[] src, int i, int j) {
        int temp = src[i];
        src[i] = src[j];
        src[j] = temp;
    }

    /**
     * 选择排序：选择出最小元素(min是最小元素的坐标)，放在左边；
     * 找到最小元素的坐标，然后交换
     *
     * @param src
     */
    public static void selectSort(int[] src) {
        int length = src.length;  //数组长度
        int min;                //最小数的的坐标
        int temp;
        for (int i = 0; i < length - 1; i++) {
            min = i;            //需要排序的位置
            for (int j = i + 1; j < length; j++) {
                if (src[j] < src[min]) {
                    min = j;
                }
            }
            //开始交换（和i进行交换）
            if (i != min) {
                temp = src[i];
                src[i] = src[min];
                src[min] = temp;
            }
        }
    }

    /**
     * 插入排序：左边维护一个有序数组，每次在右边取一个数据来进行比较（从小到大）
     * 1、判断和上一个元素的关系，要是小，则往前移动一位，直至大于前面的数据；
     * 2、左边的数组长度，就是 i 的位置，通过i将数组划分成两部分，一部分无序，一部分有序；
     *
     * @param src
     */
    public static void insertSort(int[] src) {
        //从右边取数，开始是 1 ，直至n-1个
        for (int i = 1; i < src.length; i++) {
            int j = i;   //左边的数组的长度，就是 i 的位置
            //while循环这种形式，就是条件和执行语句一起去判断，满足j>0的前提下，才会执行比较操作
            // 1、src[j]要是比前一个大，那么结束循环，开启下次循环i++
            // 2、若是比前一个数小，那么移动数据，同时下标-1，直到j>0或者符合条件
            while (j > 0 && src[j] < src[j - 1]) {
                int temp = src[j];
                src[j] = src[j - 1];
                src[j - 1] = temp;
                j--;
            }
        }
    }

    /**
     * 归并排序 4 5 6 7 1 2 3 8
     * (治)
     *
     * @param src
     * @param left
     * @param mod   mod怎么划分
     * @param right
     * @param temp
     */
    private static void mergeSort(int[] src, int left, int mod, int right, int[] temp) {
        int i = left;
        int j = mod + 1;
        int k = 0; //临时数组指针从0开始
        while (i <= mod && j <= right) {  //mod 和 right 都是 n-1
            if (src[i] < src[j])
                temp[k++] = src[i++];
            else
                temp[k++] = src[j++];
        }
        //谁的容器未满，继续填充
        while (i <= mod) {
            temp[k++] = src[i++];
        }
        while (j <= right) {
            temp[k++] = src[j++];
        }
        //填充完毕，临时数组导入原数组中
//        for (int z = 0; z <= (right-left); z++) {
//            src[left++] = temp[z];
//        }
        k = 0;
        while (left <= right) {
            src[left++] = temp[k++];
        }
    }

    private static void mSort(int[] src, int left, int right, int[] temp) {
        //左边坐标小于右边坐标一直拆分
        if (left < right) { //当最后一次为0,1时，在往下划分就是0,0，此时就是递归出口，然后调用合并方法
            int mod = (left + right) / 2; //mod和right都是n-1
            mSort(src, left, mod, temp);
            mSort(src, mod + 1, right, temp);
            mergeSort(src, left, mod, right, temp);
        }
    }

    /**
     * 归并排序入库
     *
     * @param src
     */
    public static void merge(int[] src) {
        if (src == null && src.length == 0) {
            throw new RuntimeException("数组不能为空！");
        }
        int[] temp = new int[src.length];
        mSort(src, 0, src.length - 1, temp);
    }


    //mergeSort  归并排序了
    //如何实现数据的归并排序？
    //先递归，后合并[对两个有序数组进行合并]

    //将两个有序数组合并为一个有序数组
    private static void meSort(int[] arr, int start, int mod, int end) {
        //合并数组
        //创建一个新数组，存放元素
        //两个数组start-mod      mod+1-end
        int i = start;
        int j = mod + 1;
        int[] temp = new int[end - start + 1];
        int k = 0;
        //两个条件都满足的情况下
        while (i <= mod && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mod) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        //数据迁移
        for (int t = 0; t < (end - start + 1); t++) {
            arr[start + t] = temp[t];
        }
    }

    //讲一个数组进行拆分
    private static void meSort(int[] arr, int start, int end) {
        //拆分的目的是将数组拆分为一个元素
        if (end > start) {
            int mod = (start + end) / 2;
            meSort(arr, start, mod);  //先分割，在处理，分割不要丢文件
            meSort(arr, mod + 1, end);
            //调用合并方法
            meSort(arr, start, mod, end);
        }
    }

    public static void meSort(int[] arr) {
        meSort(arr, 0, arr.length - 1);
    }

    //1、首先，处理的是元素下标；[0-arr.length-1]，
    // 不是对数组长度进行操作的，而是对下标进行处理的。
    //2、递归是一直切分下标。不是切分长度。
    // 递归终点：右下标小于左下标。
    // 递归返回值：无返回值，但是start已经确认，就是[0,1]
    // 3、然后对其合并（判断左右两子树，小的进临时数组），注意左右是长度，而不是下标


}
