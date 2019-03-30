package Algorithm;

import java.util.HashMap;

/**
 * 数据结构--堆（数组实现的，完全二叉树，通常，父节点满足
 * floor((length-1)/2)，左边子节点满足2i+1，右边子节点满足2*i+2
 * 总体满足父节点大于左右节点---最大堆，父节点小于左右节点---最小堆，
 * 堆顶一定是整个数组的最大值/最大值）
 */
public class Heap {

    //擂台赛--每一小组选出最强者，参与擂主pk，
    // 胜利者获取下一次挑战机会，失败者提供被挑战的机会；

    //遍历方式（战略方向）---整个堆的最后一个节点往前遍历，直至根节点（从右到左，从下到上）
    //遍历方式（战术方向）---左右比较取出最大值和父节点比较，有能者居之
    public static int[] createMaxHeap(int[] arr) {
        //比赛开始-请各小组按顺序进行比赛（从排名最后一个小组开始）
        int length = arr.length;
        for (int i = (length - 2) / 2; i >= 0; i--) {
            doJustHeapMove(arr, i, length);
        }
        return arr;

    }
    //小组擂台开始
    private static void doJustHeapMove(int[] arr, int root, int length) {
        int temp = arr[root];  //擂主将自己的东西保存好

        //确定小组成员，确保每一个人都有机会挑战（最后元素:length-1）
        //若发起挑战，擂主失败，那么就会子节点挑战
        //每次开始，均获取到小组成员编号（而开始的root若是挑战失败，便成为了lChild）
        for (int lChild = 2 * root + 1; lChild < length; lChild = 2 * lChild + 1) {
            //判断左节点是否是最后一个元素（是否等于n-1）！小组只有自己，直接参与擂台赛
            //选出小组最强者，准备参与擂主pk，
            if (lChild < length - 1 && arr[lChild + 1] > arr[lChild]) {
                lChild++;
            }
            //擂台赛开始
            if (temp >= arr[lChild]) {
                break;  //小组擂台赛结束，开始下一小组的比赛
            } else {
                arr[root] = arr[lChild];  //成员升级（填坑）
                root = lChild;   //擂主降级
            }
        }
        //擂主降级赛结束，滚到新位置
        arr[root] = temp;
    }

}
