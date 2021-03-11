package leetcode.random;

import java.util.Objects;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value && left.equals(treeNode.left) && right.equals(treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }
}
