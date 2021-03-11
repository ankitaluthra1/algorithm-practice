package leetcode.datastructures;

public class DiameterForTree {

    int maxDiameter = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = Integer.MIN_VALUE;
        diameterOfBinaryTree2(root);
        return maxDiameter;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null)
            return 0;

        int left = diameterOfBinaryTree2(root.left);
        int right = diameterOfBinaryTree2(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1+Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode right = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode left2 = new TreeNode(5);
        TreeNode left = new TreeNode(2, left1, left2);

        TreeNode root = new TreeNode(1, left, right);
        DiameterForTree d = new DiameterForTree();
        int result = d.diameterOfBinaryTree(root);
        System.out.println(result);
    }
}
