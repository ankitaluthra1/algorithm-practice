package leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/copy-list-with-random-pointer/

public class RandomPointerList {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        Map<Node, Integer> indexMap = new HashMap<>();
        Node temp = head;
        if (temp == null)
            return null;
        int index = 0;
        while (temp != null) {
            indexMap.put(temp, index);
            temp = temp.next;
            index++;
        }

        Node ptr = head;
        Node newHead = null;
        index = 0;
        Map<Integer, Node> nodeMap = new HashMap<>();
        Node previous = null;
        while (ptr != null) {
            Node curr = nodeMap.get(index) == null ? new Node(ptr.val) : nodeMap.get(index);
            nodeMap.put(index, curr);
            if (newHead == null)
                newHead = curr;

            if (previous != null) {
                previous.next = curr;
            }

            if (ptr.random != null) {
                int randomIndex = indexMap.get(ptr.random);
                Node random = nodeMap.get(randomIndex) == null ? new Node(ptr.random.val) : nodeMap.get(randomIndex);
                nodeMap.put(randomIndex, random);
                curr.random = random;
            } else {
                curr.random = null;
            }
            ptr = ptr.next;
            previous = curr;
            index++;
        }
        return newHead;
    }

    public static void main(String[] args) {
        RandomPointerList rm = new RandomPointerList();
        Node head = new Node(-1);
        head.random = head;
        rm.copyRandomList(head);
    }

}
