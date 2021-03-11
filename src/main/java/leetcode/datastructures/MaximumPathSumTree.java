package leetcode.datastructures;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumPathSumTree {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSum2(root);
        return maxSum;
    }

    public int max(int... varargs) {
        int max = varargs[0];
        for (int i = 1; i < varargs.length; i++) {
            max = Math.max(max, varargs[i]);
        }
        return max;
    }

    public int maxPathSum2(TreeNode root) {

        if (root == null)
            return 0;

        int maxLeft = maxPathSum2(root.left);
        int maxRight = maxPathSum2(root.right);

        System.out.println(root.val + " left: " + maxRight + " right: " + maxRight + " maxSum: " + maxSum);

        maxSum = max(maxSum,
                root.val,
                maxLeft + root.val + maxRight,
                maxLeft + root.val,
                maxRight + root.val
        );

        return max(root.val, root.val + max(maxLeft, maxRight));
    }

    public static void main(String[] args) {

        TreeNode left = new TreeNode(-2);
        TreeNode right1 = new TreeNode(-2);
//        TreeNode right2 = new TreeNode(7);
//        TreeNode right = new TreeNode(20,right1, right2);

        TreeNode root = new TreeNode(-1, left, null);

        MaximumPathSumTree mx = new MaximumPathSumTree();
        int result = mx.maxPathSum(root);

        System.out.println(result);
    }

}
