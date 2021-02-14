package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagTreeTraversal {

    public static TreeNode constructTree(Integer[] input, int index) {
        TreeNode root = null;
        if (index < input.length) {
            if (input[index] == null) {
                return null;
            }
            TreeNode left = constructTree(input, 2 * index + 1);
            TreeNode right = constructTree(input, 2 * index + 2);
            root = new TreeNode(input[index], left, right);
        }
        return root;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null)
            return Collections.emptyList();

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        List<List<Integer>> output = new ArrayList<>();

        boolean leftToRight = true;

        while (!queue.isEmpty()) {

            List<TreeNode> nextLevelNodes = new ArrayList<>();
            List<Integer> currentList = new ArrayList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.remove(0);
                currentList.add(node.val);
                if (node.left != null)
                    nextLevelNodes.add(node.left);
                if (node.right != null)
                    nextLevelNodes.add(node.right);
            }

            queue.addAll(nextLevelNodes);

            List<Integer> currentOutput = new ArrayList<>();

            if (leftToRight) {
                currentOutput.addAll(currentList);
            } else {
                for (int i = currentList.size() - 1; i >= 0; i--) {
                    currentOutput.add(currentList.get(i));
                }
            }

            output.add(currentOutput);
            leftToRight = !leftToRight;

        }

        return output;
    }

    public static void main(String[] args) {

        Integer[] input = {3};
        TreeNode treeNode = constructTree(input, 0);

        ZigZagTreeTraversal zg = new ZigZagTreeTraversal();
        List<List<Integer>> output = zg.zigzagLevelOrder(treeNode);

        for (List<Integer> o : output) {
            String print = o.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
            System.out.println(print);
        }

    }


}
