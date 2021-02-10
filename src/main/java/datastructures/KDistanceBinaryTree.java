package datastructures;

import random.DiagonalTree;

import java.util.*;

public class KDistanceBinaryTree {

    Map<random.TreeNode, random.TreeNode> edges = new HashMap<>();
    List<Integer> output;

    public List<Integer> distanceK(random.TreeNode root, random.TreeNode target, int k) {
        calculateEdges(root, edges);
        output = new ArrayList<>();
        dfs(target, k);
        return output;
    }

    private void dfs(random.TreeNode target, int k) {

        List<random.TreeNode> queue = new ArrayList<>();
        queue.add(target);
        int level = 0;
        List<random.TreeNode> visited = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                random.TreeNode curr = queue.remove(0);
                if (visited.contains(curr))
                    continue;
                visited.add(curr);
                if (level == k) {
                    output.add(curr.value);
                    continue;
                }
                queue.add(curr.left);
                queue.add(curr.right);
                queue.add(edges.get(curr));
            }
            level++;
        }

    }

    private void calculateEdges(random.TreeNode root, Map<random.TreeNode, random.TreeNode> edges) {
        if (root.left != null) {
            edges.put(root.left, root);
            calculateEdges(root.left, edges);
        }
        if (root.right != null) {
            edges.put(root.right, root);
            calculateEdges(root.right, edges);
        }
    }

    public static void main(String[] args) {
        KDistanceBinaryTree bn = new KDistanceBinaryTree();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        random.TreeNode root = DiagonalTree.constructTree(arr, 0);

        //random.TreeNode target = new random.TreeNode(5, );

       // bn.distanceK(root, target, 3);

    }

}
