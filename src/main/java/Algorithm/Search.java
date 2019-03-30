package Algorithm;

/**
 * 经典查找算法
 */
public class Search {

    /**
     * 递归实现二分查找
     *
     * @param src
     * @param key
     * @return
     */
    private static boolean binSearch(int[] src, int left, int right, int key) {
        // l<1  和1 <= 1
        if (left <= right) {
            //二分c
            int mod = (left + right) / 2;
            //key大于最大的数，那么就在右边
            if (key > src[mod])
                return binSearch(src, mod + 1, right, key);
            else if (key < src[mod])
                return binSearch(src, left, mod - 1, key);
            else
                return true;
        }
        return false;
    }

    /**
     * 二分查找
     *
     * @param src 原始数组
     * @param key 需要查找的key
     * @return 最后返回的key
     */
    public static boolean binSearch(int[] src, int key) {
        return binSearch(src, 0, src.length - 1, key);
    }

    /**
     * 循环实现二分查找
     *
     * @param src
     * @param key
     * @return
     */
    public static boolean binSearch1(int[] src, int key) {
        int left = 0;
        int right = src.length - 1;
        while (left <= right) {
            int mod = (left + right) / 2;
            if (key < src[mod]) {
                right = mod - 1;
            } else if (key > src[mod]) {
                left = mod + 1;
            } else {
                return true;
            }
        }
        return false;
    }


}
