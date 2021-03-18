package leetcode.datastructures;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/symmetric-tree/

public class MirrorTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;

        List<TreeNode> leftQueue = new ArrayList<>();
        List<TreeNode> rightQueue = new ArrayList<>();

        leftQueue.add(root.left);
        rightQueue.add(root.right);

        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            if (!isListMirror(leftQueue, rightQueue))
                return false;
            addInQueue(leftQueue);
            addInQueue(rightQueue);
        }
        return true;
    }

    private void addInQueue(List<TreeNode> queue) {
        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++) {
            TreeNode current = queue.remove(0);
            if (current != null) {
                TreeNode leftChild = current.left;
                TreeNode rightChild = current.right;
                queue.add(leftChild);
                queue.add(rightChild);
            }
        }
    }

    private boolean isListMirror(List<TreeNode> leftQueue, List<TreeNode> rightQueue) {
        int total = leftQueue.size() - 1;
        for (int i = 0; i <= total; i++) {
            TreeNode leftChild = leftQueue.get(i);
            TreeNode rightChild = rightQueue.get(total - i);

            if (leftChild == null && rightChild == null)
                continue;
            if (leftChild != null && rightChild != null) {
                if(leftChild.val != rightChild.val)
                    return false;
                else
                    continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MirrorTree m = new MirrorTree();
        TreeNode secondLevelRightChild = new TreeNode(3);
        //TreeNode secondLevelLeftChild = new TreeNode(3);
        TreeNode firsLevelLeftChild = new TreeNode(2, null, secondLevelRightChild);
        TreeNode firsLevelRightChild = new TreeNode(2, secondLevelRightChild, null);
        TreeNode root = new TreeNode(1, firsLevelLeftChild, firsLevelRightChild);

        boolean result = m.isSymmetric(root);
        System.out.println(result);
    }
}
