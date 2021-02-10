package dataStructures

data class Node(val value: Int, val left: Node?=null, val right: Node?=null)

class DFS(val tree: Node){

    fun search(searchTerm: Int,node: Node? = tree): Node? {
        if(node == null){
            return null
        }
        if(node.value == searchTerm){
            return node
        }
        val leftSearchResult = search(searchTerm, node.left)
        if(leftSearchResult !== null){
            return leftSearchResult
        }
        val rightSearchResult = search(searchTerm, node.right)
        if(rightSearchResult !== null){
            return rightSearchResult
        }
        return null
    }

}

fun main() {
    val left5 = Node(3)
    val left4 = Node(3, left5)
    val left3 = Node(9, left4)
    val right1 = Node(3)
    val right2 = Node(3)
    val left2 = Node(1, left3)
    val left1 = Node(4, left2, right2)
    val root = Node(6, left1, right1)
    val dfs = DFS(root)

    val searchResult = dfs.search(3)

    println(searchResult)
}