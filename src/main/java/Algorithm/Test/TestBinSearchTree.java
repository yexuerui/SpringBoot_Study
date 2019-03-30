package Algorithm.Test;

import Algorithm.BinSearchTree;
import Algorithm.TreeNode;

public class TestBinSearchTree {

    //主函数
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        BinSearchTree.insNode(root, 2);
        BinSearchTree.insNode(root, 3);
        BinSearchTree.insNode(root, 7);
        BinSearchTree.insNode(root, 5);

//        BinSearchTree bin = new BinSearchTree();
//        String serialize = bin.Serialize(root);
//        System.out.println("serialize：" + serialize);
//        TreeNode node = bin.deserialize(bin.Serialize(root));
//        System.out.println(node);
//        String s = bin.serialize(root);
//        System.out.println("s：" + s);
//        TreeNode treeNode = bin.deserialize(s);
//        System.out.println("root："+treeNode);

//        bin.flatten(root);
//        BinSearchTree.Convert(root);
//        System.out.println(BinSearchTree.Convert(root));

//        BinSearchTree.insNode(root, 4);
//        BinSearchTree.insNode(root, 7);
//        System.out.println(BinSearchTree.kthSmallest(root,1));
//        System.out.println(BinSearchTree.getTreeNode(root,8).toString());

        //中序遍历
//        BinSearchTree.LDRBinSearchTree(root);

//        //查找是否得到新的对象
//        TreeNode treeNode = BinSearchTree.searchNode(root, 2);
//        if (treeNode != null) {
//            System.out.println(treeNode.toString());
//        } else {
//            System.out.println("查无此数");
//        }
//        //删除节点`
//        TreeNode treeNode1 = BinSearchTree.deleteNode(root, 3);
//        BinSearchTree.LDRBinSearchTree(treeNode1);
    }

}
