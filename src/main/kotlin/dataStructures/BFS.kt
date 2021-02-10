package dataStructures

fun main() {

    val node4 = TreeNode2(7)
    val node3 = TreeNode2(15)
    val node2 = TreeNode2(20)
    node2.left = node3
    node2.right = node4
    val node1 = TreeNode2(9)
    val node0 = TreeNode2(3)
    node0.left = node1
    node0.right = node2

    val bfs = BFS()

    val result =  bfs.levelOrder(node0)
    result.forEach {
        it.forEach{
            print("$it  ")
        }
        println()
    }
}

class TreeNode2(var `val`: Int) {
    var left: TreeNode2? = null
    var right: TreeNode2? = null
}

class BFS {
    class Queue<T> {
        private val items: MutableList<T> = mutableListOf()
        var size = 0
        fun add(item: T) {
            items.add(item)
            size++
        }

        fun remove(): T {
            if (items.isEmpty())
                throw IllegalAccessException("Trying to get an element from empty queue")

            val firstItem = items[0]
            items.removeAt(0)
            size--
            return firstItem
        }

        fun isEmpty(): Boolean {
            return this.items.isEmpty()
        }
    }

    fun levelOrder(root: TreeNode2?): List<List<Int>> {

        if (root == null)
            return emptyList()
        val printList = mutableListOf<List<Int>>()

        var queue = Queue<TreeNode2>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val nextQueue = Queue<TreeNode2>()
            val currentLevelElements = mutableListOf<Int>()
            for (i in 0 until queue.size) {
                val element = queue.remove()
                if (element != null) {
                    currentLevelElements.add(element.`val`)
                    element.left?.let { nextQueue.add(it) }
                    element.right?.let { nextQueue.add(it) }
                }
            }
            printList.add(currentLevelElements)
            queue = nextQueue
        }
        return printList
    }
}