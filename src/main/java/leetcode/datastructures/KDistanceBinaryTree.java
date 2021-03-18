package leetcode.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

public class KDistanceBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    Map<TreeNode, TreeNode> edges = new HashMap<>();
    List<Integer> output;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        calculateEdges(root, edges);
        output = new ArrayList<>();
        dfs(target, k);
        return output;
    }

    private void dfs(TreeNode target, int k) {

        List<TreeNode> queue = new ArrayList<>();
        queue.add(target);
        int level = 0;
        List<TreeNode> visited = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove(0);
                if (visited.contains(curr) || curr == null)
                    continue;
                visited.add(curr);
                if (level == k) {
                    output.add(curr.val);
                    continue;
                }
                queue.add(curr.left);
                queue.add(curr.right);
                queue.add(edges.get(curr));
            }
            level++;
        }

    }

    private void calculateEdges(TreeNode root, Map<TreeNode, TreeNode> edges) {
        if (root.left != null) {
            edges.put(root.left, root);
            calculateEdges(root.left, edges);
        }
        if (root.right != null) {
            edges.put(root.right, root);
            calculateEdges(root.right, edges);
        }
    }

}
