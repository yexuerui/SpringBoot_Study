package Algorithm.Test;
import Algorithm.ListNode;
import Algorithm.Node;

//public 所有
//protected 同包+ 不同包子类
//default 同包
//private 私有
public class TestMyLink {
    public static void main(String[] args) {
        //



//        MyLink head = MyLink.createHeaderLink(10);
//        for (int i = 0; i < 10; i++) {
//            System.out.print(head.data + " ");            //把当前对象的下一个对象地址传给当前对象            head=head.next;        }
//            head = head.next;
//        }
//        System.out.println("----------------------------");
//        Node link = Node.createHeaderLink(3);
//        for (int i = 0; i < 10; i++) {
//            System.out.print(link.data + " ");            //把当前对象的下一个对象地址传给当前对象            head=head.next;        }
//            link = link.next;
//        }
//        Node reverseLink = Node.reverseLink(link);
//
//        for (int i = 0; i < 3; i++) {
//            System.out.print(reverseLink.data + " ");            //把当前对象的下一个对象地址传给当前对象            head=head.next;        }
//            reverseLink = reverseLink.next;
//        }
        ListNode listNode=new ListNode(1);
        listNode.next=new ListNode(1);
        listNode.next.next=new ListNode(2);
        listNode.next.next.next=new ListNode(3);
        listNode.next.next.next.next=new ListNode(3);

        ListNode node = ListNode.deleteDuplicates(listNode);
        System.out.println(node);


//
//        ListNode listNode1=new ListNode(2);
//        listNode1.next=new ListNode(4);
//        listNode1.next.next=new ListNode(6);
//
//        ListNode merge = ListNode.Merge(listNode, listNode1);
//        for (int i = 0; i < 6; i++) {
//            System.out.print(merge.val + " ");            //把当前对象的下一个对象地址传给当前对象            head=head.next;        }
//            merge = merge.next;
//        }
    }
}
