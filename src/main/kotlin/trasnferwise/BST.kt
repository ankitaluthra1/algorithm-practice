package trasnferwise

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    private var inorderList: MutableList<Int> = mutableListOf()

    fun getInorderList(root: TreeNode?) {

        if (root != null) {
            getInorderList(root.left)
            inorderList.add(root.value)
            getInorderList(root.right)
        }

    }

    fun isValidBST(root: TreeNode?): Boolean {

        this.getInorderList(root)

        for (i in 0 until inorderList.size - 1) {

            if (inorderList[i] > inorderList[i + 1]) {
                return false
            }

        }
        return true
    }
}