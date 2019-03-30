package Algorithm.Test;

import Algorithm.BinTree;

public class TestBinTree {


    public static void main(String[] args) {
        int[] datas = {5, 4, 6};
        //获取一颗二叉树
        BinTree binTree = new BinTree(datas);
        //完成插入操作

          binTree.LDRBinTree(); //中序输出（应该是有序的）
//        binTree.insertNode(8);
//        binTree.searchNode(6);
//        binTree.getMax();
    }
}
