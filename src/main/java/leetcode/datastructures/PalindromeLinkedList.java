package leetcode.datastructures;

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp1 = head;
        ListNode temp2 = reverse(slow);

        while (temp1 != slow && temp2 != null) {

            if (temp1.val != temp2.val)
                return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }

    private ListNode reverse(ListNode slow) {
        if (slow == null)
            return null;

        ListNode temp = slow;
        ListNode previous = null;
        while (true){
            ListNode temp2 = temp.next;
            temp.next = previous;
            previous = temp;
            if (temp2 == null)
                return temp;
            temp = temp2;
        }
    }

    public static void main(String[] args) {

        ListNode root = new ListNode(1, new ListNode(2, new ListNode(1, new ListNode(1, null))));

        PalindromeLinkedList pl = new PalindromeLinkedList();
        boolean result = pl.isPalindrome(new ListNode(1));

        System.out.println(result);

    }

}
