package leetcode.datastructures;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-duplicate-subtrees/

public class DuplicateSubtree {

    Map<String, TreeNode> visited;
    Set<String> duplicateNodes;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
       visited = new HashMap<>();
       duplicateNodes = new HashSet<>();
       inOrder(root);
       return duplicateNodes.stream().map(d -> visited.get(d)).collect(Collectors.toList());
    }

    private String inOrder(TreeNode root) {
        if(root == null)
            return "";
        String curr = inOrder(root.left)+"(left)-" + root.val + "-"+inOrder(root.right)+"(right)";
        if (visited.containsKey(curr))
            duplicateNodes.add(curr);
        else
            visited.put(curr, root);
        return curr;
    }

    private TreeNode constructTree(Integer[] input, int index) {
        TreeNode root = null;
        if (index < input.length) {
            TreeNode left = constructTree(input, 2 * index + 1);
            TreeNode right = constructTree(input, 2 * index + 2);
            if (input[index] == null)
                return null;
            root = new TreeNode(input[index], left, right);
        }
        return root;
    }

    public static void main(String[] args) {

        DuplicateSubtree dp = new DuplicateSubtree();

        TreeNode left1 = new TreeNode(3);
        TreeNode left11 = new TreeNode(3);
        TreeNode right1 = new TreeNode(3);
        TreeNode left = new TreeNode(2, left1, null);
        TreeNode right = new TreeNode(2, left11, null);
        TreeNode root = new TreeNode(2, left, right);

        Integer[] input = {0,0,0,0,null,null,0,null,null,null,0};
      //  TreeNode root = dp.constructTree(input, 0);

        List<TreeNode> duplicateSubtrees = dp.findDuplicateSubtrees(root);

        for (TreeNode d : duplicateSubtrees){
            System.out.println(d.val);
        }

    }

}
