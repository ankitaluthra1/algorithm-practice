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

            List<Integer> currentList = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                if (leftToRight)
                    currentList.add(node.val);
                else
                    currentList.add(0, node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            output.add(currentList);
            leftToRight = !leftToRight;

        }

        return output;
    }

    public static void main(String[] args) {

        Integer[] input = {3,9,20,null,null,15,7};
        TreeNode treeNode = constructTree(input, 0);

        ZigZagTreeTraversal zg = new ZigZagTreeTraversal();
        List<List<Integer>> output = zg.zigzagLevelOrder(treeNode);

        for (List<Integer> o : output) {
            String print = o.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
            System.out.println(print);
        }

    }


}
