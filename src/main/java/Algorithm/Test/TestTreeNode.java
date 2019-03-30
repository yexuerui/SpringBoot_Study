package Algorithm.Test;

import Algorithm.Solution;
import Algorithm.TreeNode;

import java.util.ArrayList;

public class TestTreeNode {
    public static void main(String[] args) {
        int[] pre = {1, 2, 3, 4};   //前序数组
        int[] in = {2, 3, 1, 4};    //中序数组
        TreeNode node = Solution.reConstructBinaryTree(pre, in); //树节点
        //前序 遍历树节点
        ArrayList<Integer> preList = Solution.DLRTree(node);
        for (Integer i : preList) {
            System.out.print(i+" ");
        }
        System.out.println();
        //中序遍历
        ArrayList<Integer> inList = Solution.LDRTree(node);
        for (Integer a : inList) {
            System.out.print(a+" ");
        }
        System.out.println();
        //后序遍历
        ArrayList<Integer> nextList = Solution.LRDTree(node);
        for (Integer v : nextList) {
            System.out.print(v+" ");
        }
        System.out.println();
        Solution.stackDLRTree(node);
        Solution.stackLDRTree(node);
        Solution.stackLRDTree(node);
//        Solution.levelTree(node);
    }
}
