package leetcode.datastructures;

// https://leetcode.com/problems/recover-binary-search-tree/

public class RecoverBST {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode temp = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        swap();
    }

    private void swap() {
        int temp;
        if (first != null && second != null) {
            temp = second.val;
            second.val = first.val;
            first.val = temp;
        }
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        if (temp != null) {
            if (temp.val > root.val) {
                if (first == null) {
                    first = temp;
                }
                second = root;
            }
        }
        temp = root;
        inOrder(root.right);

    }

    private static void printInOrder(TreeNode root) {
        if (root == null)
            return;

        printInOrder(root.left);
        System.out.println(root.val);
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        RecoverBST rt = new RecoverBST();
        Integer[] input = {3, 1, 4, null, null, 2};
        TreeNode root = rt.constructTree(input, 0);
        rt.recoverTree(root);

        printInOrder(root);

        System.out.println();
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

}
