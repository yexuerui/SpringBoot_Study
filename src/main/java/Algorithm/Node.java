package Algorithm;

public class Node {
    //数据域
    public int data;
    //指针域
    public Node next;

    public Node() {
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    //谍战风云，每个人员只保留了与上线的联系方式，单线联系;
    // 特务大A（header节点，开始创建情报网）
    //将自己联系方式写到next（通讯录上）
    public static Node createHeaderLink(int len) {
        Node header = null;
        for (int i = 0; i < len; i++) {
            //创建了一个子节点
            Node newNode = new Node();
            newNode.data = i;     //获取数据
            if (header == null) {
                newNode.next = header;
                header = newNode;
            } else {
                newNode.next = header;
                header = newNode;
            }
        }
        return header;
    }


    /**
     * 反转Node null->A->B->C->null
     * 循环
     * (化整为0)
     *
     * @return
     */
    public static Node reverseLink(Node node) {
        //如何返回头结点
        Node headNode = null;
        Node preNode = null;
        Node curNode = node;
        while (curNode != null) {
            if (curNode.next == null) {
                headNode = curNode;
            }
            Node nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return headNode;
    }

    /**
     * 递归实现反转
     * 1,2,null
     * 循环
     * (化整为0)
     *每一个方法都有一次return的机会，返回完毕，指针指向下一个元素，
     * 那么此时curNode=1
     * @return
     */
    public static Node reverseLink1(Node node) {
        //0个元素或者1个元素直接返回，递归出口
        if (node == null || node.next == null) {
            return node;
        }

        Node nextNode=node.next; //保存下一个节点
        Node headNode = reverseLink1(node.next); //此时返回的是Node=1
        nextNode.next=node;
        return headNode;
    }

}
