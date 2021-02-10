package dataStructures


class LowestCommonAncestor {
    fun lowestCommonAncestor(root: TreeNode2, p: TreeNode2?, q: TreeNode2?): TreeNode2? {

        val queue = mutableListOf<TreeNode2>()
        queue.add(root)
        val ancestorMap = mutableMapOf<TreeNode2, TreeNode2>()

        while (!queue.isEmpty()) {
            val current = queue[0]
            current.left?.let {
                queue.add(it)
                ancestorMap.put(it, current)
            }
            current.right?.let {
                queue.add(it)
                ancestorMap.put(it, current)
            }
            queue.removeAt(0)

        }

        ancestorMap.forEach{
            println("${it.key.`val`}   ${it.value.`val`}")
        }

        var pNode = ancestorMap[p]
        var qNode = ancestorMap[q]
        val pAncestorList = mutableListOf<TreeNode2>()
        val qAncestorList = mutableListOf<TreeNode2>()

        pAncestorList.add(p!!)
        qAncestorList.add(q!!)

        while (pNode != null){
            pAncestorList.add(pNode)
            pNode = ancestorMap[pNode]
        }

        while (qNode != null){
            qAncestorList.add(qNode)
            qNode = ancestorMap[qNode]
        }

        var pIndex = -1
        for (i in 0 until pAncestorList.size){
            if (qAncestorList.contains(pAncestorList[i])){
                pIndex = i
                break
            }
        }

        return pAncestorList[pIndex]

    }

}


fun main() {

    val node4 = TreeNode2(8)
    val node3 = TreeNode2(0)
    val node2 = TreeNode2(1)
    val node11 = TreeNode2(6)
    val node12 = TreeNode2(2)
    val node41 = TreeNode2(7)
    val node42 = TreeNode2(4)
    node12.left = node41
    node12.right = node42
    node2.left = node3
    node2.right = node4
    val node1 = TreeNode2(5)
    node1.left = node11
    node1.right = node12
    val node0 = TreeNode2(3)
    node0.left = node1
    node0.right = node2


    val lca = LowestCommonAncestor()

    val result = lca.lowestCommonAncestor(node0, node1, node42)

    println(result!!.`val`)
}