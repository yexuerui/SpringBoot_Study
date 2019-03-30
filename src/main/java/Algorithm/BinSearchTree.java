package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//二叉排序树，完成查找，新增、删除的操作
public class BinSearchTree {


    //层次遍历二叉树
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);  //根元素进队列
        //以队列驱动数量
        while (queue.size() != 0) {
            TreeNode treeNode = queue.poll();  //根元素先出队列
            if (treeNode == null) {
                sb.append("null,");
                continue;
            }
            sb.append(treeNode.val);
            sb.append(",");
            //将节点保存到队列中
            queue.add(treeNode.left);
            queue.add(treeNode.right);
        }
        String s = sb.toString();
        if (s.endsWith(",")) {
            s = sb.substring(0, s.length() - 1);
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] strings = data.split(",");
        //重建二叉树，借助queue(链表)
        ArrayList<TreeNode> link = new ArrayList<>();
        //将第一个元素放进去
        TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
        link.add(root);
        //判断该节点是不是左节点
        boolean isLeft = true;
        int index = 0;
        for (int i = 1; i < strings.length; i++) {
            if (!"null".equals(strings[i])) {
                //创建新节点
                TreeNode treeNode = new TreeNode(Integer.valueOf(strings[i]));
                if (isLeft) {
                    link.get(index).left = treeNode;
                } else {
                    link.get(index).right = treeNode;
                }
                link.add(treeNode);  //将新节点保存进去
            }
            if (isLeft == false) {
                index++;  //赋予完右节点，才开始下一个元素
            }
            isLeft = !isLeft;  //每寻找一个节点，那么标志变换一次
        }
        return root;
    }

//    //根据前序遍历拆分二叉树
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        String s1 = sb.toString();
//        if (s1.endsWith(",")) {
//            s1 = s1.substring(0, s1.length() - 1);
//        }
//        return s1;
//    }
//
//    //前序遍历二叉树，将其保存到字符串中
//    public void serialize(TreeNode root, StringBuilder str) {
//        if (root == null) {
//            return;
//        }
//        //第一次获取根
//        str.append(root.val);
//        str.append(",");
//        serialize(root.left, str);
//        serialize(root.right, str);
//    }
//
//
//    //【根据前序遍历】重建二叉树
//    public TreeNode deserialize(String data) {
//        //String类型数据
//        if (data == null || data.length() == 0) {
//            return null;
//        }
//        //将字符串转换为String数组
//        String[] nodes = data.split(",");
//        //重建二叉树
//        return deserialize(nodes, 0, nodes.length - 1);
//    }
//
//    //递归调用(根据一个前序数组重建二叉树)
//    public TreeNode deserialize(String[] nodes, int left, int right) {
//        if (left > right) {
//            return null;  //左边大于右边，直接返回null
//        }
//        //最左为根节点
//        TreeNode root = new TreeNode(Integer.valueOf(nodes[left]));
//        //左右子树长度确定，数组元素直到大于根
//        System.out.println("->" + (left + 1) + ":" + right);
//        boolean flag = false;
//        int count = left + 1;
//        //right是下标，而不是数组长度(易错点01)
//        for (int i = count; i < right + 1; i++) {
//            //没有考虑--不会进这个判断的情况。
//            if (Integer.valueOf(nodes[i]) > Integer.valueOf(nodes[left])) {
//                count = i;
//                flag = true;
//                break;
//            }
//        }
//        if (flag) {
//            //找到元素
//            root.left = deserialize(nodes, left + 1, count - 1);
//            root.right = deserialize(nodes, count, right);
//        } else {
//            //未找到右子树
//            root.left = deserialize(nodes, left + 1, right);
//        }
//        return root;
//    }
//    //递归调用(根据一个前序数组重建二叉树)
//    public TreeNode deserialize(String[] nodes, int left, int right) {
//        if (left > right) {
//            return null;  //左边大于右边，直接返回null
//        }
//        //最左为根节点
//        TreeNode root = new TreeNode(Integer.valueOf(nodes[left]));
//        //左右子树长度确定，数组元素直到大于根
//        System.out.println("->" + (left + 1) + ":" + right);
//        for (int i = left+1; i < right + 1; i++) {  //right是下标，而不是数组长度
//            if (Integer.valueOf(nodes[i]) > Integer.valueOf(nodes[left])) {
//                root.left = deserialize(nodes, left + 1, i - 1);
//                root.right = deserialize(nodes, i, right);
//                break;
//            }
//        }
//        return root;
//    }


    /**
     * 二叉排序树   插入算法
     * root：我比左子树最右的字段都要大，比右子树最左的字段都要大
     * key：我比你小，我要去你左边。
     * root：我知道你们要做出修改，我不关心你到底修改了啥。但是，你把最新地址给我下吧。
     * left：好哒
     * right：好哒
     *
     * @param root
     * @param key
     * @return
     */
    public static TreeNode insNode(TreeNode root, int key) {
        //极端情况
        //root就是指向4的
        if (root == null) {
            //找到最后为null的情况下。开始拼接
            TreeNode treeNode = new TreeNode(key);
            return treeNode;
        }
        if (key < root.val) {
            //我不管这个地址，一定会是新地址。反正你就返回新地址
            root.left = insNode(root.left, key);   //其实返回的的是root.left对象，此时不能直接return
        } else {
            root.right = insNode(root.right, key);
        }
        return root;
    }


    //我想key这个对象，最后，你将这个对象返回给我就可以了(  返回节点对象)
    public static TreeNode searchNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            return searchNode(root.left, key);   //我不管，这个方法是返回是否找到，找到给我对象，找不到给我null。
        } else if (key > root.val) {
            return searchNode(root.right, key);
        } else {
            //若是相等，则找到
            return root;
        }
    }

    //无子树时直接删除，
    // 有一个子树的情况下，指向孙子节点，‘
    // 2个子树的情况下，先去寻找左子树的最大元素，和删除的元素交换位置后，直接执行方法2
    //我不管，你无论是新增还是删除，对父节点没有影响3，我只是要指向你的新位置
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        //操作左子树
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            //找到的情况,第1,2种情况
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                //此时，要删除的元素含有左右孩子
                //1、找到左子树的最右节点
                TreeNode tempMode = root;
                root = root.left;   //找到最右节点||该节点的一定没有右孩子
                while (root.right != null) {
                    root = root.right;
                }

                //这个节点就是root；root移动到删除的节点处，我们要做的，就是删除这个root
                tempMode.val = root.val;  //根节点的替换
                //节点的迁移
                //问题？root tempNode的左子树中，（一棵树中，删除节点key）
                tempMode.left = deleteNode(tempMode.left, root.val);
                root = tempMode;
            }
        }
        return root;
    }


    //中序输出
    public static void LDRBinSearchTree(TreeNode root) {
        if (root == null) {
            return;
        }
        LDRBinSearchTree(root.left);
        System.out.println("中序输出 ： " + root.val);
        LDRBinSearchTree(root.right);
    }

    //插入节点
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //极致情况
        if (root == null) {
            return new TreeNode(val);
        }
        //开始逻辑，想要插入到那个节点里面
        //左子树(执行方法的后果：结构将被修改，但是我并不关心，我只要后序节点给我新的值。)
        if (val < root.val) {
            //
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    //搜索第k小的元素（5）
    //你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
    public static int kthSmallest(TreeNode root, int k) {
        //中序遍历有序的，倒数第K个元素，即第几次完成中序遍历（栈）
        //入栈是第一次操作元素，将数据入栈
        Stack<TreeNode> stack = new Stack<>();
        int data = -1;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                //第一次操作对象
                root = root.left;
            }

            //若是栈中有数据
            if (!stack.empty()) {
                root = stack.pop();
                //第二次操作对象
                k--;
                if (k == 0) {
                    data = root.val;
                    break;
                }
                root = root.right;  //取出最左元素
            }
        }
        return data;
    }


//返回值怎么处理，参数我可以使用引用对象，每次传递的是引用，每次均-1

    //参数传递引用对象时
    //方法A  k=1   传递到了A1   k=1  完成逻辑， 回到方法A  此时方法已经是1。
    //导致数据冲突


    //元素查找，我为什么不喜欢用递归？
    //因为，在我查找到之后，我并不知道，要怎么结束递归，或者说，怎么把正确的值返回给用户？

//    返回值会在各个方法中传递

    //或者是参数条件，我也不是很清楚如何将  参数值 精确的传递到第n个方法中

    //返回第n个数据是否存在
    public static TreeNode getTreeNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        //递归返回值，也不是无根之水，递归出口，就是在极端情况下的返回值，我们需要做的，
        // 就是让他在某一处返回我们需要的值，然后被上一个方法处理，或者直接返回给上上方法。
        //作为用户来说，我并不关注你们底层的逻辑，我调用一次，我就想获取到最终结果。
        if (key < root.val) {
            return getTreeNode(root.left, key);
        } else if (key > root.val) {
            return getTreeNode(root.right, key);
        } else {
            //相等时；
            return root;
        }
    }

    //对树的操作，一般基于树的遍历！第二次拿到树节点的时候，进行操作的吧。
    //我们在拿到根节点的时候，左子树使用右指针，右子树使用左指针

    //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
    // 要求不能创建任何新的结点，只能调整树中结点指针的指向。
//    public static TreeNode Convert(TreeNode pRootOfTree) {
//        if (pRootOfTree == null) {
//            return null;
//        }
//        //使用栈
//        Stack<TreeNode> stack = new Stack<>();
//        //创建节点：new 操作，只是修改引用；
//        TreeNode root = null;    //循环链表的头节点
//        //是否是第一次进入循环链表
//        boolean isFirst = true;
//        //保存循环链表的前一个元素。尾插法
//        TreeNode preNode = null;
//        //进栈，第一次操作
//        while (pRootOfTree != null || !stack.empty()) {
//            //疯狂遍历左子树
//            while (pRootOfTree != null) {
//                //跟节点进栈
//                stack.push(pRootOfTree);
//                //遍历左子树
//                pRootOfTree = pRootOfTree.left;
//            }
//            //使得一个右子树出栈，栈里面有数据
//            if (!stack.empty()) {
//                TreeNode node = stack.pop();//定位到右节点
//                //判断是否是第一个
//                if (isFirst) {
//                    root = node;
//                    preNode = root;
//                    isFirst = false;
//                } else {
//                    //每一次获取根元素之后，将其加入到新的链表中。
//                    //当前节点和上节点的关系
//                    preNode.right = node;
//                    node.left = preNode;
//                    //后一个指针前进一位
//                    preNode = node;
//                }
//                //逻辑操作。
//                pRootOfTree = node.right;
//            }
//        }
//        return root;
//    }

    //二叉树合并为循环链表(中序遍历，返回的一定是一个有序循环链表--)
//    public static TreeNode Convert(TreeNode pRootOfTree) {
//        if (pRootOfTree == null) {
//            return null;
//        }
//        Convert1(pRootOfTree);
//        return headNode;
//    }
//
//
//    private static TreeNode headNode;
//
//    private static TreeNode preNode;

//    //想操作头结点
//    private static void Convert1(TreeNode pRootOfTree) {
//        if (pRootOfTree == null) {
//            return;
//        }
//        //单纯中序遍历
//        Convert(pRootOfTree.left);
//        //第一次获取头节点
//        if (headNode == null) {
//            headNode = pRootOfTree; //获取节点信息
//            preNode = pRootOfTree;  //前一节点
//        } else {
//            preNode.right = pRootOfTree;
//            pRootOfTree.left = preNode;
//            //指针前移
//            preNode = pRootOfTree;
//        }
//        Convert(pRootOfTree.right);
//
//    }


//     后序遍历
//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> it = new ArrayList<>();
//        if (root == null) {
//            return it;
//        }
//        //使用栈
//        Stack<TreeNode> stack = new Stack<>();
//        Stack<Integer> integers = new Stack<>();
//        //进栈，第一次操作
//        while (root != null || !stack.empty()) {
//            //疯狂遍历左子树
//            while (root != null) {
//                //跟节点进栈
//                stack.push(root);
//                integers.push(0);
//                //遍历左子树
//                root = root.left;
//            }
//            //判断是否是第三次遍历根元素，栈顶元素是1
//            while (!integers.empty() && integers.peek() == 1) {
//                integers.pop();  //弹出栈顶元素
//                //数据操作，第三次拿到根节点
//                TreeNode node = stack.pop();//栈顶元素弹出
//                it.add(node.val);
//            }
//
//            //使得一个右子树出栈，栈里面有数据
//            if (!integers.empty() && !stack.empty()) {
//                integers.pop();
//                integers.push(1); //修改栈顶元素值
//                TreeNode treeNode = stack.peek();//查看栈顶元素但不出栈
//                //逻辑操作
//                root = treeNode.right;  //定位到右节点
//            }
//        }
//        return it;
//    }


    /**
     * 例如，给定二叉树:
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * 将其展开为：
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     */

    //二叉树转化为链表
    //把左子树看做右子树，
    // 把原右子树（temp）拼接到现右子树的最右端
//    public void flatten(TreeNode root) {
//        //第三次操作根的时候，开始设计，后序遍历
//        if (root == null) {
//            return;
//        }
//        flatten(root.left);
//        flatten(root.right);
//        //第三次操作根
//        if (root.left != null) {
//            //左子树放到右子树上
//            // （1）原右子树保留
//            TreeNode treeNode = root.right;
//            // （2）ROOT--(Right)--左子树，建立关系之后，需要将左子树清空
//            root.right = root.left;   //左子树放在右子树上
//            // （3）将原右子树放到现右子树右边
////            root.right.right = treeNode;   //1-2-3-4    . right  =    5-6       1.2.5.6
//            TreeNode r = root.right;
//            //找到右子树的最右节点
//            while (r.right != null) {
//                r = r.right;  //1-2-3-4
//            }
//            r.right = treeNode;
//            root.left = null;   //清空左子树
//        }
//    }


//    //二叉树原地转化为链表
//    public static void flatten(TreeNode root) {
//        //递归出口
//        if (root == null) {
//            return;
//        }
//        //后序遍历，操作根
//        flatten(root.left);
//        flatten(root.right);
//        //第三次拿到根之后。
//        //判断根节点是否含有左子树
//        if (root.left != null) {
//            //1、保存原右子树节点
//            TreeNode temp = root.right;
//            //2、root的左子树移到右子树上
//            root.right = root.left;
//            //3、左子树置空
//            root.left = null;
//            //4、将原右子树拼接到新右子树的最后一个节点上
//            TreeNode newRight = root.right;
//            while (newRight.right != null) {
//                newRight = newRight.right;
//            }
//            //5、将原右子树拼接到新右子树上
//            newRight.right = temp;
//        }
//    }


//    //先序遍历转换
//    public void flatten(TreeNode root) {
//        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
//        list = getDLR(root, list);  //此时list保存值
//        //链表数据转化为链表（保存头结点）
//        TreeNode head = null;
//        TreeNode preHead = null;
//        for (TreeNode node : list) {
//            if (head == null) {
//                head = preHead = node;
//            } else {
//                preHead.right = node;
//                preHead.left = null;
//                preHead = node;  //先前节点移动
//            }
//        }
//    }
//
//    //存储DLR
//    private ArrayList<TreeNode> getDLR(TreeNode root, ArrayList<TreeNode> list) {
//        if (root == null) {
//            return list;
//        }
//        list.add(root);  //Root节点
//        getDLR(root.left, list);
//        getDLR(root.right, list);
//        return list;
//    }


    //二叉搜索树---》有序循环列表（中序遍历，栈实现吧）
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        //声明栈
        Stack<TreeNode> stack = new Stack<>();
        //声明引用
        TreeNode head = null;  //头指针
        TreeNode tail = null;   //尾指针

        while (pRootOfTree != null || !stack.empty()) {
            //左子树入栈
            while (pRootOfTree != null) {
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            //出栈，循环链表
            if (!stack.empty()) {
                //根节点出栈
                TreeNode node = stack.pop(); //创造一个新的引用  4
                if (head == null) {
                    head = tail = node;  //当前节赋值给头结点和尾节点
                } else {
                    //我们也是破坏这个树结构吗？
//                    tail.right = node; //正确代码
//                    node.left = tail;   //正确代码
                    tail.left = node;  //错误代码
                    node.right = tail;  //错误代码
                    //尾指针前进一位
                    tail = node;
                }
                pRootOfTree = node.right;
            }
        }
        return head;
    }

    //有序数组重建二叉树【前序+中序 重建二叉树没有什么同】
    //每一次，将中间元素作为根节点，进行重建
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode node = sortedArrayToBST(nums, 0, nums.length - 1);
        return node;
    }

    //记录数据的前后位置
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        //递归条件
        if (start > end) {
            return null;
        }
        //寻找中间元素，充当头结点（先重建左子树）
        int mod = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mod]);
        root.left = sortedArrayToBST(nums, start, mod - 1);  //调用该方法的目的就是生成了一个左子树；
        root.right = sortedArrayToBST(nums, mod + 1, end);//该方法返回的是右子树
        return root;
    }


}