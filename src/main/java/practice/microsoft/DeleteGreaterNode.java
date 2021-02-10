package practice.microsoft;

import dataStructures.Node;

class LinkedListNode {
    int value;
    LinkedListNode next;

    LinkedListNode(int value, LinkedListNode next) {
        this.value = value;
        this.next = next;
    }
}

public class DeleteGreaterNode {

    LinkedListNode deleteGreaterNodes(LinkedListNode head, int x) {
        LinkedListNode newHead = null;
        LinkedListNode temp = head;
        while (temp != null && temp.value > x) {
            temp = temp.next;
        }
        if (temp == null) {
            return head;
        }
        newHead = temp;

        while(temp.next != null) {
            if(temp.next.value > x) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return newHead;

    }

    LinkedListNode createLinkedList(int[] arr) {
        LinkedListNode head = new LinkedListNode(arr[0], null);
        LinkedListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new LinkedListNode(arr[i], null);
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        DeleteGreaterNode dl = new DeleteGreaterNode();
        int[] arr = {1, 8, 7, 3, 7, 10};
        LinkedListNode head = dl.createLinkedList(arr);

        LinkedListNode temp = dl.deleteGreaterNodes(head, 6);
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}
