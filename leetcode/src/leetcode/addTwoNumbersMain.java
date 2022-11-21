package leetcode;

import java.util.List;

public class addTwoNumbersMain {
    public static void main(String[] args) {

        //ListNode alist10000 = new ListNode(6);
        //ListNode alist1000 = new ListNode(5,alist10000);
        //ListNode gongtong2 = new ListNode(4, alist1000);
//        ListNode gongtong = new ListNode(1);
//        ListNode alist100 = new ListNode(2,gongtong);
//        ListNode alist10 = new ListNode(2,alist100);
//        ListNode alist1 = new ListNode(1,alist10);
//        System.out.println((solution.isPalindrome(alist1)));
        //ListNode blist100 = new ListNode(9);
       // ListNode blist10 = new ListNode(1,gongtong);
        //ListNode blist1 = new ListNode(4,blist10);   // 123
        //solution.traverse(solution.removeElements(alist1,6));
        //solution.traverse(solution.swapPairs(alist1));
        //solution.getIntersectionNode(alist1,blist1);
        //solution.traverse(solution.deleteDuplicates(alist1));
//        ListNode n5 = new ListNode(1);
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        solution.deleteNode(n2);;
        solution.traverse(n1);
        //System.out.println((solution.isPalindrome(n1)));
    }
}
