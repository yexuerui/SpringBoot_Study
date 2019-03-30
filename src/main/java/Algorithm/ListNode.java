package Algorithm;

import java.util.ArrayList;

//定义节点
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    //数组原地去重
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //新数组的下标count
        //count里面不能含有重复元素，都不能和count相同
        //因为有序，所以只需考虑最右边的元素和原数组的关系
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            //元素相等，直接跳过，直至第一个不等的元素，
            //赋值为新数组。
            if (nums[i] != nums[count]) {
                count++;
                nums[count] = nums[i];
            }
        }
        return count + 1;  //输出新数组的长度
    }


    /////////////////////////////////////////////START

    //如何对链表进行排序——归并排序

    //（1）快慢指针找中点，链表断开
    //（2）链表节点递归分解
    //（3）有序合并排序
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        return resolveList(head);
    }

    //分解链表
    private ListNode resolveList(ListNode head) {
        //只有一个元素，或是null,返回自己
        if (head == null || head.next == null) {
            return head;
        }
        //快慢指针找中点
        ListNode fast = head;   //快指针
        ListNode slow = head;   //慢指针
        ListNode breakNode = head;  //断开的位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  //快指针走两步
            breakNode = slow;     //慢指针前一个元素
            slow = slow.next;     //慢指针走异步
        }
        //快指针到终点，慢指针到中点
        breakNode.next = null;  //链表断开
        //继续分解，直至一个元素
        ListNode list1 = resolveList(head); //前链表
        ListNode list2 = resolveList(slow); //后链表
        return mergeList(list1, list2);
    }

    private ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val < list2.val) {
            list1.next = mergeList(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeList(list1, list2.next);
            return list2;
        }
    }

//////////////////////////////////////////////END

    //////////////////////////////////////////////START
    //给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
    //双指针法-
//    public static ListNode deleteDuplicates(ListNode head) {
//        //空元素和一个元素直接返回
//        if (head == null || head.next == null) {
//            return head;
//        }
//        //声明指针
//        ListNode fast = head;
//        ListNode slow = head;
//        while (fast != null) {
//            //指针是否相同
//            if (slow.val != fast.val) {
//                slow.next = fast;
//                slow = fast;  //慢指针前进一步
//            }
//            //1.    slow.next=null; //操作的是一个链表，若是slow设置为null，那么链表结构遭到破坏
//            fast = fast.next;
//        }
//        //此处判断
//        if (slow.next != null && slow.next.val == slow.val) {
//            slow.next = null;
//        }
//        return head;
//    }

    //方法2


    public static ListNode deleteDuplicates(ListNode head) {
        //空元素和一个元素直接返回
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curNode = head;
        //若是a=a.next，那么原指针前进一位
        while (curNode != null && curNode.next != null) {
            if (curNode.val == curNode.next.val) {
                curNode.next = curNode.next.next;
            } else
                curNode = curNode.next;
        }
        return head;
    }


    //单指针指向法
//    public static ListNode deleteDuplicates(ListNode head) {
//        //空元素和一个元素直接返回
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode curNode = head;
//        while (curNode != null && curNode.next != null) {
//            //等于的时候操作
//            if (curNode.val == curNode.next.val) {
//                //当A指针的下一个指针和A的值保持一致时，A指针就不会移动
//                curNode.next = curNode.next.next;
//            } else
//                curNode = curNode.next;
//        }
//        return head;
//    }


//////////////////////////////////////////////END
    //////////////////////////////////////////////START

//    //1、有序链表；2、节点A出发，若是等于本身那么就不保留
//    //给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode newNode = null;
//        ListNode yNode = null;
//        ListNode curNode = head;
//        while (curNode != null) {
//            boolean flag = true;
//            //元素A和元素A.next相同，自旋
//            while (curNode.next != null && curNode.val == curNode.next.val) {
//                curNode = curNode.next;
//                flag = false;
//            }
//            //出来时候，肯定是A不等于A.next，我们要看的是是否自旋？
//            if (flag) {
//                if (newNode == null) {
//                    newNode = yNode = curNode;  //头节点
//                } else {
//                    newNode.next = curNode;  //头结点新加节点
//                    newNode = curNode;
//                }
//            }
//            //是到达末尾是空，并且还是false的标志位，直接设置为null
//            if (curNode.next == null && flag == false) {  //没有想到针对最后一个元素应该如何处理。
//                if (newNode != null) {  //没有加判断
//                    newNode.next = null;
//                }
//            }
//            curNode = curNode.next;  //指针前进一位
//            //赋予值
//        }
//        return yNode;
//    }

    //////////////////////////////////////////////END


//    //在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
//
//    //归并排序
//    public ListNode sortList(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        return resolveList(head);
//    }
//
//    //分解，将链表分解为一个个小链表
//    private ListNode resolveList(ListNode head) {
//        if (head.next == null) {
//            return head;
//        }
////        ListNode modNode = head;
////        while (head.next != null && head.next.next != null) {
////            modNode = head;  //找到中间元素
////            head = head.next;
////        }
////        ListNode l = resolveList(head);
////        ListNode r = resolveList(modNode);
////        return mergeList(l,r);
//        //快慢指针
//        ListNode fast = head;
//        ListNode slow = head;
//        ListNode mod = head;
//        //只需判断快指针是不是null
//        while (fast != null && fast.next != null) {
//            fast = fast.next.next;  //快指针每次移动两个位置
//            mod = slow;   //慢指针的值赋予给快指针
//            slow = slow.next; //慢指针走一个值
//        }
//        mod.next = null;  //链表中点断开mod是head链表的尾至指针，而slow是新链表的头指针
//        ListNode lNdoe = resolveList(head);
//        ListNode rNode = resolveList(slow);
//        return mergeList(lNdoe,rNode);
//    }
//
//
//    //合并，两个有序链表的合并
//    private ListNode mergeList(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//        //逻辑操作
//        if (list1.val < list2.val) {
//             list1.next = mergeList(list1.next, list2);
//             return list1;
//        } else {
//             list2.next = mergeList(list1, list2.next);
//             return list2;
//        }
//    }


    public ListNode ReverseList(ListNode head) {
        //递归出口
        if (head == null || head.next == null) {
            return head;
        }
        //完成递归，返回头结点
        ListNode newHead = ReverseList(head.next);
        //b.Node=aNode;
        head.next.next = head;
        //a.Node.next=null
        head.next = null;
        return newHead;
    }

    public ListNode ReverseList1(ListNode head) {
        //若上送节点是null或者只有一个元素
        if (head == null || head.next == null) {
            return head;
        }
        //开始处理
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode newHead = null;
        while (curNode != null) {
            //若是当前节点的下一个节点是null，则是反转后的头结点
            if (curNode.next == null) {
                newHead = curNode;
            }
            //当前节点下一节点保存起来（断开连接）
            ListNode nextNode = curNode.next;
            //当前节点指向下一节点
            curNode.next = preNode;
            //节点自动前进一步
            preNode = curNode;
            curNode = nextNode;
        }
        return newHead;
    }

    //将链表从尾到头输出到ArrayList中
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //递归实现
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> arrayList = xx(listNode, list);
        return arrayList;

    }

    private ArrayList<Integer> xx(ListNode listNode, ArrayList<Integer> list) {
        if (listNode != null) {
            xx(listNode.next, list);
            list.add(listNode.val);
        }
        return list;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减(单调递增)规则。
     *
     * @param list1
     * @param list2
     * @return
     */
    //归并排序
//    public static ListNode Merge(ListNode list1, ListNode list2) {
//        //新建一个链表，我们默认是root
//        ListNode head = new ListNode(-1);
//
//        //人造头结点，将头结点保存起来
//        ListNode root=head;
//
//        while (list1 != null && list2 != null) {
//            if (list1.val < list2.val) {
//                root.next = list1;
//                root=list1;    //当前指针前进一位
//                list1 = list1.next; //下一个元素
//            } else {
//                root.next=list2;
//                root=list2;
//                list2=list2.next;
//            }
//        }
//        //list1链表未结束
//        if (list1 != null) {
//            root.next = list1;
//        }
//        if (list2 != null) {
//            root.next = list2;
//        }
//        return head.next;
//    }
    //  1  3  5   ||  2  4  6
    //递归法实现
//    public static ListNode Merge(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//        if (list1.val < list2.val) {
////            list1.next=list2;
//            list1.next = Merge(list1.next, list2);
//            return list1;
//
//        } else {
//            list2.next = Merge(list1,list2.next);
//            return list2;
//        }
//    }

//    //新建链表节点
//    public static ListNode Merge(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//        //新建节点
//        ListNode mergeNode = null; //头结点
//        ListNode curNode = null; //当前节点
//        while (list1 != null && list2 != null) {
//            //链表1小于链表2时
//            if (list1.val < list2.val) {
//                if (mergeNode == null) {
//                    mergeNode = curNode = list1;  //链表1存储到头结点
//                } else {
//                    curNode.next = list1;  //指向链表1
//                    curNode = curNode.next;  //当前指针挪动一位
//                }
//                list1 = list1.next;  //list指针挪动一位
//            } else {
//                if (mergeNode == null) {
//                    mergeNode = curNode = list2;  //链表1存储到头结点
//                } else {
//                    curNode.next = list2;  //指向链表1
//                    curNode = curNode.next;  //当前指针挪动一位
//                }
//                list2 = list2.next;  //list指针挪动一位
//            }
//        }
//        if (list1 == null) {
//            curNode.next = list2;
//        }
//        if (list2 == null) {
//            curNode.next = list1;
//        }
//        return mergeNode;
//    }


    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        //极限条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        //返回值，是一个新的链表，我调用这个方法，会给我一个单调递增的链表。我第一次调用调用这个方法时
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            //最开始的元素是谁？list1？
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

//    //合并有序链表（递归法）
//    public static ListNode Merge(ListNode list1, ListNode list2) {
//        //极限条件
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//        //创建链表(此节点需要保存，用于输出)
//        ListNode node = null;
//        //root节点负责移动
//        ListNode root = node;
//        //开始相互比较
//        while (list1 != null && list2 != null) {
//            if (list1.val < list2.val) {
//                //判断root是否为null
//                if (root == null) {
//                    root = list1;   //list1作为头结点
//                    node=root;
//                } else {
//                    //root指向list1（下一个位置）
//                    root.next = list1;
//                    root = list1;   //root指针全局一位
//                }
//                //list1前进一位
//                list1 = list1.next;
//            } else {
//                if (root == null) {
//                    root = list2;
//                    node=root;
//                } else {
//                    root.next = list2;
//                    root = list2;
//                }
//                list2 = list2.next;
//            }
//        }
//        if(list1==null){
//            root.next=list2;
//        }
//        if(list2==null){
//            root.next=list1;
//        }
//        return node;
//    }


}
