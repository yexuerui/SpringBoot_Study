package Algorithm;

/**
 * 获取一组数据的topN
 */
public class GetTopN {

    /**
     * 挖坑填数
     * 使用快排。倒序排序
     */
    private static int getModSort(int[] src, int l, int r) {
        int i = l;
        int j = r;
        int x = src[i];  //基数挖坑
        while (i < j) {
            //j 指针从右到左的循环
            while (i < j && src[j] < x)
                j--;
            if (i < j) {
                src[i] = src[j];
                i++;
            }
            while (i < j && src[i] > x) {
                i++;
            }
            if (i < j) {
                src[j] = src[i];
                j--;
            }
        }
        src[i] = x; //基数填坑
        return i;
    }

    /**
     * TOP N本质是取的是一个数组的[0 n-1]项数据，但是不保证有序
     * @param src   原始数组
     * @param l 初始位置
     * @param r 最大位置
     * @param desc 取最大的几个元素
     */

    private static void quick(int src[], int l, int r, int desc) {
        if (l < r) {
            int mod = getModSort(src, l, r);
            //最大值-中间值
          if(desc-1==mod)
                return;
          if(desc-1<mod)
              quick(src,l,mod-1,desc);
          if(desc-1>mod)
              quick(src, mod+1, r, desc);
        }
    }
    public static int[] getTopNByQuick(int[] src,int n){
        quick(src,0,src.length-1,n);
        return src;
    }


}
