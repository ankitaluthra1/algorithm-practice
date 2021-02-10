package dataStructures

class BinaryTreeConstruction {

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode2? {

        if (preorder.isEmpty() && inorder.isEmpty())
            return null

        if (preorder.size == 1 && inorder.size == 1) {
            val node = TreeNode2(preorder[0])
            return node
        }

        val root = TreeNode2(preorder[0])
        val splitIndex = inorder.indexOf(preorder[0])

        val leftPreOrderArray = preorder.sliceArray(1..splitIndex)
        val leftInOrderArray = inorder.sliceArray(0 until splitIndex)

        val rightPreOrderArray = preorder.sliceArray(splitIndex+1 until preorder.size)
        val rightInOrderArray = inorder.sliceArray(splitIndex+1 until inorder.size)

        root.left = buildTree(leftPreOrderArray, leftInOrderArray)
        root.right = buildTree(rightPreOrderArray, rightInOrderArray)

        return root
    }

}

fun main() {

    val preorderArray = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    val inorderArray = readLine()!!.split(",").map { it.toInt() }.toIntArray()

    val binaryTreeConstruction = BinaryTreeConstruction()
    val result = binaryTreeConstruction.buildTree(preorderArray, inorderArray)
    println(result!!.`val`)

}