package Algorithm;

public class BinTree {
    //定义树节点
    Node root = null;

    //内部类
    private class Node {
        Node leftNode = null;
        Node rightNode = null;
        int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    ", data=" + data +
                    '}';
        }
    }

    //二叉树的最近公共祖先
    //遇到二叉树的问题不要慌，首先想明白，要使用什么遍历手段？
    //这道题的含义就是求左右子树的根节点，那么我们需要在遍历完左右子树之后，才能确定根节点到底是哪个？
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果根节点是null，那么直接返回null
        if (root == null) {
            return null;
        }
        //若是找到p或者q节点后，直接返回
        if (root == q || root == p) {
            return root;
        }
        //后序遍历，左右根，若是左右子树均找到，那么直接返回根节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //看left或者right是否为null还是有值
        if (left != null || right != null) {
            return root;
        } else if (left != null) {
            return left;  //若没找到右子树，那么直接返回左子树；
        } else if (right != null) {
            return right; //若没找到左子树，那么直接返回右子树；
        } else {
            return null;  //没有找到任一子树，直接return
        }
    }
    //    //序列化搜索二叉树(层次遍历)
//    public String Serialize(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);  //根元素进队列
//        //以队列驱动数量
//        while (queue.size() != 0) {
//            TreeNode treeNode = queue.poll();  //根元素先出队列
//            sb.append(treeNode.val);
//            sb.append(",");
//            if (treeNode == null) {
//                sb.append("null");
//            }
//            //将节点保存到队列中
//            queue.add(treeNode.left);
//            queue.add(treeNode.right);
//        }
//        return sb.toString();
//    }


//    //反序列化二叉树
//    TreeNode Deserialize(String str) {
//
//    }


    //创建一颗树
    public BinTree(int[] datas) {
        buildTree(datas);
    }

    private void buildTree(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            Node node = new Node(datas[i]);  //创建节点
        }
    }

    //将节点插入到二叉搜索树中(均是从头结点出发、和左右节点比较，直到空节点)
    //其实均是指针的移动，每次移动，均是指针从根节点出发，小的放左边，大的放右边，直至null，而新插入的就是要替换null
    private void insertNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node tempNode = null;  //用来保存父节点信息
            //需要将头结点保存起来
            Node curNode = root;  //一般需要获取整个头结点，才能打印整棵树
            //直到左子树为null，或者右子树为null
            while (curNode != null) {
                //将父节点保存起来
                tempNode = curNode;   //将当前节点保存起来
                if (node.data < curNode.data) {
                    curNode = curNode.leftNode;  //若是插入节点小于根节点的值，自动去左边
                } else {
                    curNode = curNode.rightNode;
                }
            }
            //此时，可以知道，curNode就是当前节点，我们需要的就是在null处插入我们的数据
            if (node.data < tempNode.data) {
                tempNode.leftNode = node;
            } else {
                tempNode.rightNode = node;
            }
        }
    }

    /**
     * 二叉排序树   插入算法
     * root：我比左子树最右的字段都要大，比右子树最左的字段都要大
     * key：我比你小，我要去你左边。
     * root：我知道你们要做出修改，我不关心你到底修改了啥。但是，你把最新地址给我下吧。
     * left：好哒
     * right：好哒
     */


    //插入值
    public void insertNode(int data) {
        //插入成功
        insertNode(new Node(data));
        //调用中序遍历，查询插入状态
        System.out.println("插入：" + data + "成功！");
        LDRBinTree();
    }


    //对象的中序遍历
    public void LDRBinTree() {
        LDRBinTree(root);
    }

    private void LDRBinTree(Node root) {
        if (root == null) {
            return;
        }
        //第一次获取根对象
        LDRBinTree(root.leftNode); //访问其左节点
        System.out.println("中序遍历 ： " + root.data);
        LDRBinTree(root.rightNode); //访问其右节点
    }

    //搜索节点
    public void searchNode(int key) {
        System.out.println("搜索节点 ： " + key);
        if (searchNode(root, key)) {
            System.out.println("搜索到了...");
        } else {
            System.out.println("对不起，查无此数...");
        }

    }

    private boolean searchNode(Node root, int key) {
        if (root == null) {
            return false;   //输入root为null，直接返回未找到
        }
        //第一次访问根节点和key相等，直接返回
        if (root.data == key) {
            return true;
        } else if (key < root.data) {
            //代理对象：承诺，我会给你最终结果的，你只需直接返回就行了。
            //这个会在我的左子树中开始寻找，找到后，左子树分支就会返回
            //以遍历思想+第一次操作进行思考
            return searchNode(root.leftNode, key);
        } else {
            return searchNode(root.rightNode, key);
        }
    }

    //获取最大值
    //我们由二叉搜索树性质可知，最大节点就是最右节点
    public void getMax() {
        if (root == null) {
            System.out.println("该节点为null...");
        } else {
            Node maxNode = getMaxNode(root);
            System.out.println("最大节点数 ：" + maxNode.data);
        }
    }

    //由第一个节点，可知，右边元素是最大的
    private Node getMaxNode(Node root) {
        //循环直至最右节点
        while (root.rightNode != null) {
            root = root.rightNode;
        }
        //此时root就是最右节点
        return root;
    }

    //删除节点
    //1、无叶节点：
    //2、只有一个叶节点：
    //3、有两个孩子节点：
    public void deleteNode(int data) {
        System.out.println("删除节点 ： " + data);
        //搜索位置（确定位置）

    }

    //保存根节点信息，返回最后的子树信息
    private Node deleteTreeNode(Node root, int key) {
        Node temp = null;  //要删除的元素
        if (root == null) {
            return null;
        } else if (key < root.data) {  //key要去左子树寻找
            //契约，一定会返回最后的值。返回值传递问题？
            //可怕，返回的还是一棵树，一棵被处理的树
            root.leftNode = deleteTreeNode(root.leftNode, key);
        } else if (key > root.data) {
            root.rightNode = deleteTreeNode(root.rightNode, key);
        } else {
            //后序遍历，第三次获取根节点的时候(此时左右字段已经遍历完毕)，并且根节点信息相同
            //找到元素后，一般是返回自己的，哼，但是我是被删除的，于是我把我的孩子节点给返回的了
            if (root.leftNode == null || root.rightNode == null) {
                if (root.leftNode == null) {
                    return root.rightNode;
                } else if (root.rightNode == null) {
                    return root.leftNode;
                }
            } else {
                //含有 左右 两个元素（现在的目的是，求出左子树的最右元素（最大），或者右子树的最左元素（最小））
                //将父节点保存起来。
                Node curNode = root.leftNode;   //左子树的最大元素
                while (curNode.rightNode != null) {
                    curNode = curNode.rightNode;  //一直可以到最右元素
                }
                root.data = curNode.data;   //赋值之后，将左子树最右元素替换根节点。替换根节点之后，还是要把最右元素删除
                root.leftNode = deleteTreeNode(root.leftNode, curNode.data);
            }
        }
        return root;
    }


    /**
     * 删除节点后，返回root节点信息
     *
     * @param root
     * @param key
     */
//    private TreeNode deleteNode(Node root, int key) {
//        if (root == null) {
//            return null;
//        }
//        //分解，先找到节点信息，但是要保存root节点信息
//        if (key < root.data) {
////            root.leftNode =deleteNode(root,key);
//        }
//
//
//        //找到节点（需要保存父节点信息）
//        if (root.data == key) {
//            //叶节点
//            if (root.leftNode == null && root.rightNode == null) {
//
//            }
//        }
//    }
}



