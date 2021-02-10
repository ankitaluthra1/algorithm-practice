package datastructures;

class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head.next == null){
            return null;
        }

        ListNode slowTemp = head;
        ListNode fastTemp = head;

        for (int i = 0; i < n; i++) {
            fastTemp = fastTemp.next;
        }

        if (fastTemp == null){
            return head.next;
        }

        while (fastTemp.next != null) {
            slowTemp = slowTemp.next;
            fastTemp = fastTemp.next;
        }

        slowTemp.next = slowTemp.next.next;

        return head;
    }

    ListNode createLinkedList(int[] arr) {
        ListNode head = new ListNode(arr[0], null);
        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i], null);
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {

        RemoveNthNodeFromEnd rm = new RemoveNthNodeFromEnd();
        int[] arr = {1,3};
        ListNode head = rm.createLinkedList(arr);
        ListNode result = rm.removeNthFromEnd(head, 2);

        while (result !=null){
            System.out.println(result.val);
            result = result.next;
        }

    }
}
