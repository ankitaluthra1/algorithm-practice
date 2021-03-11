package leetcode.random;

import java.util.*;
import java.util.stream.Collectors;

public class DiagonalTree {

    public static TreeNode constructTree(int[] input, int index) {
        TreeNode root = null;
        if (index < input.length) {
            TreeNode left = constructTree(input, 2 * index + 1);
            TreeNode right = constructTree(input, 2 * index + 2);
            root = new TreeNode(input[index], left, right);
        }
        return root;
    }

    void printDiagonal(TreeNode root) {

        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();

        getDiagonalMap(root, 0, diagonalMap);

        for (Map.Entry<Integer, List<Integer>> e : diagonalMap.entrySet()) {
            String collect = e.getValue().stream().map(v -> v.toString()).collect(Collectors.joining(" "));
            System.out.println(collect);
        }
    }

    private void getDiagonalMap(TreeNode root, Integer parentDiagonal, Map<Integer, List<Integer>> map) {

        if (root != null) {
            List<Integer> currentList = map.getOrDefault(parentDiagonal, new ArrayList<>());
            currentList.add(root.value);
            map.put(parentDiagonal, currentList);
            getDiagonalMap(root.left, parentDiagonal, map);
            getDiagonalMap(root.right, parentDiagonal+1, map);
        }
    }

    public static void main(String[] args) {
        DiagonalTree dt = new DiagonalTree();
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = dt.constructTree(ints, 0);

        dt.printDiagonal(root);
    }
}