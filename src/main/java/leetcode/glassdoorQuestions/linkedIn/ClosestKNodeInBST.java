package leetcode.glassdoorQuestions.linkedIn;

import leetcode.random.DiagonalTree;
import leetcode.random.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ClosestKNodeInBST {

    List<Integer> queue;
    int k;
    int target;

    public List<Integer> closestKNode(TreeNode root, int target, int k) {
        queue = new ArrayList<>();
        this.k = k;
        this.target = target;
        inOrder(root);

        return queue;
    }

    private void inOrder(TreeNode root) {

        if (root == null)
            return;

        inOrder(root.left);

        if (queue.size() < k) {
            add(root.value);
        } else {
            if (root.value <= target)
                add(root.value);
            else {
                if (queue.get(0) > target) {
                    return;
                } else {
                    if (target - queue.get(0) > root.value - target) {
                        add(root.value);
                    } else
                        return;
                }
            }
        }

        inOrder(root.right);

    }


    private void add(int value) {

        if (queue.size() == k) {
            queue.remove(0);
        }
        queue.add(value);
    }

    public static void main(String[] args) {
        ClosestKNodeInBST cl = new ClosestKNodeInBST();
        int[] arr = {6,4,8,2,5,7,9};
        TreeNode root = DiagonalTree.constructTree(arr, 0);
        List<Integer> result = cl.closestKNode(root, 1, 3);

        for (int r : result) {
            System.out.println(r);
        }
    }

}
