package Algorithm;


import java.util.*;

public
class Solution {
    //寻找数组中的不重复参数，要求空间复杂度是O(1)，时间复杂度是O(n)
    //[4,3,2,7,8,2,3,1]  返回是 [2,3]
    public static List<Integer> findDuplicates(int[] nums) {
        //    解题思路：遍历一遍，将每个数字对应成下标找到相应的位置，该位置置为相反数，
        //    因此只遍历过一次的是负数，遍历两次的是正数。注意下标有可能出现负数，所以要使用下标的绝对值
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;  //获取元素的值
            nums[index] = -nums[index];   //遍历两次是整数
            if (nums[index] > 0) {
                list.add(index + 1);
            }
        }
        return list;
    }


    //根据前序+中序 重建二叉树
    //递归思想：递归出口，递归参数，递归返回
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        return reConstructBinaryTree(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    //前序遍历：每次取得第一个元素作为根节点；中序遍历：以前序中第一个元素位置分割，获取节点作用两边的数据
    //中序数组中，找到父节点位置，父节点以左，均是左子树中序数组（1）获取中序遍历数组（2）获取数组长度
    //在截取对应长度的原前序数组（1）获取前序数组
    //以上是一个流程。那么可以确定，一直按这个小流程切分；
    //什么位置为递归出口呢？
    //当数组切分成一个元素时，他就是叶子节点（伪根节点，我们并不知道他是否是叶子节点，还是要继续切分的）。
    // 若继续切分的话，那么便可让其返回null，叶子节点左右两个子树挂null，结束循环。
    //startPre>endPre||startIn>endIn 返回null，结束递归
    //递归的返回值？
    //我们根据上面小流程可知。
    //每次递归都要返回小Root节点，然后从下往上一直拼接，直至根Root节点
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in,
                                                  int startPre, int endPre, int startIn, int endIn) {
        //1、递归出口
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        //2、递归返回值,新建返回值，前序数组第一个元素为Root节点的值
        TreeNode root = new TreeNode(pre[startPre]);
        //3、递归表达式
        //按照上述，我们首选要寻找中序数组的，Root元素所在的位置
        for (int i = startIn; i <= endIn; i++) {
            //中序数组已被成功切割，开始计算参数，前序数组和中序数组均要被重新计算
            //startPre已经是Root元素，
            if (pre[startPre] == in[i]) {
                //左子树
                //pre  startPre+1---startPre+1+(in-startIn)-1  起始位置+偏移位置-1【即：数组长度-1】
                //in    startIn ---i-1  左子树其实位置 --- Root节点前一个数据
                root.left = reConstructBinaryTree(pre, in, startPre + 1,
                        startPre + 1 + (i - startIn) - 1, startIn, i - 1);
                //右子树
                //前序数组，startPre+(i-startIn)+1---endPre  即开始位置+偏移量+1（第一个坐标）---endPre
                //中序数组，i+1 ---endIn  Root节点下一个---结束
                //递归代码，我们这个是一维重建二维，
                // 那么我们在确定好递归出口和递归返回值后，我们可以将第一次递归执行情况带入到代码中。
                root.right = reConstructBinaryTree(pre, in, startPre + (i - startIn) + 1, endPre, i + 1, endIn);
                //找到元素之后，后续就不可能相等了，那么break整个循环。
                break;
            }
        }
        //返回拼接好数据的Root节点，到上一级（可能是左海子节点也可能是右孩子节点）
        return root;
    }

    public static ArrayList<Integer> DLRTree(TreeNode root) {
        ArrayList list = new ArrayList();
        DLRTree(root, list);
        return list;
    }

    /**
     * 前序遍历树结构
     * 前序遍历（DLR）中序遍历（LDR）后序遍历（LRD）
     */
    private static ArrayList<Integer> DLRTree(TreeNode root, ArrayList<Integer> list) {
        //返回条件是null的情况下，递归出口，原样返回list数组
        if (root == null) {
            return list;
        }
        //递归开始【跟 左 右】
        //递归逻辑
        list.add(root.val);  //list获取数据
        DLRTree(root.left, list);  //遍历左子树
        DLRTree(root.right, list); //遍历右子树
        //递归返回式
        return list;
    }

    //中序遍历
    public static ArrayList<Integer> LDRTree(TreeNode root) {
        ArrayList list = new ArrayList();
        LDRTree(root, list);
        return list;
    }

    private static ArrayList<Integer> LDRTree(TreeNode root, ArrayList<Integer> list) {
        //返回条件是null的情况下，递归出口，原样返回list数组
        if (root == null) {
            return list;
        }
        //递归开始【左 根 右】
        //递归逻辑
        LDRTree(root.left, list);  //遍历左子树
        list.add(root.val);
        LDRTree(root.right, list); //遍历右子树
        //递归返回式
        return list;
    }

    //后续遍历
    public static ArrayList<Integer> LRDTree(TreeNode root) {
        ArrayList list = new ArrayList();
        LRDTree(root, list);
        return list;
    }

    private static ArrayList<Integer> LRDTree(TreeNode root, ArrayList<Integer> list) {
        //返回条件是null的情况下，递归出口，原样返回list数组
        if (root == null) {
            return list;
        }
        //递归开始【左 根 右】
        //递归逻辑
        LRDTree(root.left, list);  //遍历左子树
        LRDTree(root.right, list); //遍历右子树
        list.add(root.val);  //在
        //递归返回式
        return list;
    }
    //人字搜索，左右两开花

    //根为主体，人字搜索，左右两开花，--逆袭上位
    //我们通过根找到左右两节点，遍历方法的不同，在于根  打印时机的不同。

    //根 左  右  前序和后序我们走的是相同的路径，但为什么会有3种形式的遍历方式呢？
    //本质上，是最后"根"输出的时机
    //路径是，从根节点出发，像一个”人“形状，到达叶节点时，若左右都是null，那么回到上一个节点，再次遍历右子树

    //栈前序遍历二叉树(后进先出)
    public static void stackDLRTree(TreeNode root) {
        //借助栈，先序遍历，根 左 右  根在第一次访问时，获取数据
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            //根打印的时机是  NO.1
            while (root != null) {
                //第一次访问根节点，输出并放入栈
                System.out.println("前序遍历 ： " + root.val);
                stack.push(root);  //将root节点放到栈中
                root = root.left; //指针下移一位
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    //中序遍历（非递归）
    public static void stackLDRTree(TreeNode root) {
        //创建栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //打印时机为左节点遍历完毕之后
        //栈 Root元素第一个进去，若是出来的话，
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //人——“撇”数据开始压栈，直到最 左下 节点（不为null）。
                stack.push(root);
                root = root.left;
            }
            //人——“捺”回到再次根节点，准备访问右节点。
            //左节点访问完毕，此时，可以访问右节点
            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println("中序遍历：" + root.val);
                root = root.right;  //准备完成 捺，也是按照撇的条件执行
            }
        }
    }

    //后序遍历(根节点遍历的时机是访问完右节点之后)
    //一个栈计数，一个栈记录节点，栈顶元素记录为1，
    //先将左子树存到栈中，获取右子树之后，在将右子树压栈
    //我们最终是输出的根对象   输出的时机不同，

    public static void stackLRDTree(TreeNode root) {
        //节点栈
        Stack<TreeNode> stackNode = new Stack<TreeNode>();
        //计数栈
        Stack<Integer> stackInt = new Stack<Integer>();
        int count = 1;      //标志位
        //开始一撇
        while (root != null || !stackNode.empty()) {
            while (root != null) {
                //1. root第一次被访问，同步计数栈为0；
                stackNode.push(root);
                stackInt.push(0);
                root = root.left;
            }
            //栈元素不为null，并且计数栈栈顶元素此时为1，此时是第三次被访问到，
            while (!stackNode.empty() && stackInt.peek() == count) {
                //计数栈元素移除
                stackInt.pop();
                //获取遍历元素
                System.out.println("后序遍历" + stackNode.pop().val + " ");
            }

            //准备遍历右节点
            if (!stackNode.isEmpty()) {
                //2. root被pop时，是第二次被访问到，同步计数栈为1
                stackInt.pop();//移除元素0
                stackInt.push(1);
                //查看栈顶元素(但不移除)回到节点
                root = stackNode.peek();
                //准备一捺
                root = root.right;
            }
        }
    }

//    //层级遍历（使用队列）
//    public static void levelTree(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        TreeNode treeNode = null;
//        //创建队列
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);  //将根节点保存到队列中
//        //输出数据(队列元素不空的情况下)
//        while (queue.size() != 0) {
//            treeNode = queue.poll();   //中间元素，保存信息
//            System.out.println("层级遍历 ： " + treeNode.val + " ");
//            if (treeNode.left != null) {
//                //将指定的元素插入此队列
//                queue.offer(treeNode.left);
//            }
//            if (treeNode.right != null) {
//                queue.offer(treeNode.right);
//            }
//        }
//    }

    /**
     * 从左到右打印二叉树
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        //TreeNode  头结点，层级遍历，队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点保存到队列中
        queue.add(root);
        while (queue.size() != 0) {
            //取出节点信息
            TreeNode poll = queue.poll();
            //将根节点元素保存起来
            list.add(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return list;
    }

    /*
    *        8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
    * */
//    //镜像二叉树，左右两节点交换位置
//    public void Mirror(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() != 0) {
//            //小世界
//            TreeNode pollNode = queue.poll();
//            TreeNode lRoot = pollNode.left;
//            pollNode.left = pollNode.right;
//            pollNode.right = lRoot;
//            if (pollNode.left != null)
//                queue.offer(pollNode.left); //左边保存
//            if (pollNode.right != null)
//                queue.offer(pollNode.right); //右边保存
//        }
//    }

    //如何使用递归实现？
    //镜像二叉树，左右两节点交换位置
    //若只递归一次，则结束
    public void Mirror(TreeNode root) {
        //递归出口
        if (root == null) {
            return;
        }
        //递归逻辑(交换)
        //先统一处理左子树，在统一处理右子树，在处理右子树。
        Mirror(root.left);
        Mirror(root.right);
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
    }

    /**
     * 求树的深度
     * 后序遍历，我们可以，树的高度就是：左右子树最大高度+1，
     * 需要先将左右子树遍历完毕，输出最大高度。后序遍历的思想。
     *
     * @param root
     * @return
     */
//    public int TreeDepth(TreeNode root) {
//        //递归出口
//        if (root == null) {
//            return 0;
//        }
//        //逻辑运算
//        int leftDepth = TreeDepth(root.left);  //左子树高度
//        int rightDepth = TreeDepth(root.right);  //右子树高度
//        return 1+Math.max(leftDepth, rightDepth);   //返回左右子树最大值
//    }

    //非递归遍历，求树的深度---（层次遍历）
    public int TreeDepth(TreeNode root) {
        //层级遍历，每一层遍历完毕，在遍历下一层；
        //在每一层最右元素的位置时，队列里面保存的是下一层所有节点数,
        // 每遍历一个元素count++，若是最后一个元素nextCount++，此时depthCount++
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0; //指针位置[0-curCount]
        int curCount = 1; //当前节点总个数
        int depthCount = 0; //层级深度
        while (queue.size() != 0) {
            count++;  //没运行一次，指针加一
            TreeNode treeNode = queue.poll(); //取出头结点
            if (treeNode.left != null)
                queue.offer(treeNode.left);
            if (treeNode.right != null)
                queue.offer(treeNode.right);
            //当层级-最右元素poll出来之后，
            if (count == curCount) {
                count = 0;  //指针恢复出厂设置
                curCount = queue.size(); //当前层级设置为下一级
                depthCount++;  //深度+1
            }
        }
        return depthCount;
    }

}














