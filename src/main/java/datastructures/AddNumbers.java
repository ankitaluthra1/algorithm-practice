package datastructures;

public class AddNumbers {
    int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = head;
        while (l1 != null && l2 != null) {

            ListNode temp = getSumResultFor(l1.val + l2.val);
            if (head == null) {
                head = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = curr.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            ListNode temp = getSumResultFor(l1.val);
            if (head == null) {
                head = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = curr.next;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode temp = getSumResultFor(l2.val);
            if (head == null) {
                head = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = curr.next;
            }
            l2 = l2.next;
        }

        if (carry == 1) {
            ListNode temp = new ListNode(1);
            curr.next = temp;
        }
        return head;
    }

    ListNode getSumResultFor(int initialValue) {

        int current = initialValue + carry;

        if (current > 9) {
            carry = 1;
            current = current % 10;
        } else {
            carry = 0;
        }

        return new ListNode(current);
    }

    public static void main(String[] args) {
        AddNumbers addNumbers = new AddNumbers();
        ListNode firstL1 = new ListNode(9);
        ListNode secondL1 = new ListNode(9, firstL1);
        ListNode thirdL1 = new ListNode(9, secondL1);
        ListNode fourthL1 = new ListNode(9, thirdL1);
        ListNode fifthL1 = new ListNode(9, fourthL1);
        ListNode l1 = new ListNode(9, fifthL1);

        ListNode firstL2 = new ListNode(9);
        ListNode secondL2 = new ListNode(9, firstL2);
        ListNode thirdL2 = new ListNode(9, secondL2);
        ListNode fourthL2 = new ListNode(9, thirdL2);
        ListNode l2 = new ListNode(9, fourthL2);

        ListNode result = addNumbers.addTwoNumbers(l1, l2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
