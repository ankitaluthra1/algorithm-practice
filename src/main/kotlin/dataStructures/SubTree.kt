package dataStructures

class SubTree {
    fun isSubtree(parent: TreeNode2?, possibleSubTree2: TreeNode2): Boolean {

        val stack = Stack<TreeNode2>()
        stack.push(parent!!)
        val possibleStartNode2: MutableList<TreeNode2> = mutableListOf()
        while (!stack.isEmpty()) {
            val currentValue = stack.pop()

            if (currentValue.`val` == possibleSubTree2.`val`) {
                possibleStartNode2.add(currentValue)
            }

            currentValue.right?.let { stack.push(it) }
            currentValue.left?.let { stack.push(it) }
        }

        if (possibleStartNode2.isEmpty())
            return false

        possibleStartNode2.forEach {
            val parentList = mutableListOf<Int?>()
            val subTreeList = mutableListOf<Int?>()

            preOrder(it, parentList)
            preOrder(possibleSubTree2, subTreeList)

            if(parentList == subTreeList)
                return true
        }

        return false
    }

    class Stack<T> {
        val items = mutableListOf<T>()
        fun push(value: T) {
            items.add(0, value)
        }

        fun pop(): T {

            val item = items[0]
            items.removeAt(0)
            return item
        }

        fun isEmpty(): Boolean {
            return items.isEmpty()
        }
    }

    private fun preOrder(node2: TreeNode2?, list: MutableList<Int?>) {
        if (node2 == null) {
            list.add(null)
            return
        }

        list.add(node2.`val`)
        preOrder(node2.left, list)
        preOrder(node2.right, list)
    }
}

fun main() {
    val one = TreeNode2(1)

    val two = TreeNode2(2)
    val three = TreeNode2(3)
    val four = TreeNode2(4)
    val five = TreeNode2(5)

    three.left = four
    three.right = five

    four.left = one
    four.right = two

//    val newFour = TreeNode(4)
//    val newOne = TreeNode(1)
//    val newTwo = TreeNode(2)
//    val newZero = TreeNode(0)
//
//    newFour.left = newOne
//    newFour.right = newTwo
//    newFour.left = newZero

    val newFour = TreeNode2(4)
    val newOne = TreeNode2(1)
    val newTwo = TreeNode2(2)
    newFour.left = newOne
    newOne.right = newTwo

    val subTree = SubTree()
    val possible = subTree.isSubtree(three, newFour)

    println(possible)
}