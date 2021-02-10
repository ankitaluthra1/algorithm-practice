package dataStructures

class SimilarGroupsUnionFind {
    fun numSimilarGroups(list: Array<String>): Int {
        val connections = Array(list.size) { mutableListOf<Int>() }
        val root = IntArray(connections.size)
        for (i in 0 until root.size) {
            root[i] = i
        }

        for (i in 0 until list.size) {
            for (j in i + 1 until list.size) {
                if (isSimilar(list[i], list[j])) {
                    val connection = connections[i]
                    connection.add(j)
                }
            }
        }


        for (i in 0 until connections.size) {
            val currentConnections = connections[i]
            for (j in 0 until currentConnections.size) {
                val currentRoot = find(i, root)
                union(root, currentRoot, currentConnections[j])
            }
        }

        val groups = mutableSetOf<Int>()

        for (i in 0 until root.size) {
            val currentRoot = find(root[i], root)
            root[i] = currentRoot
        }

        for (i in 0 until root.size) {
            println("for $i, root is ${root[i]}")
            groups.add(root[i])
        }

        for (i in 0 until connections.size) {
            val currentList = connections[i]
            println("for ${i}")
            currentList.forEach {
                print("${it}, ")
            }
            println()
        }

        return groups.size
    }

    private fun find(i: Int, root: IntArray): Int {
        var curr = i
        while (root[curr] != curr) {
            curr = root[curr]
        }
        return curr
    }

    private fun union(root: IntArray, rootElement: Int, currentElement: Int) {

        var temp = currentElement
        var next: Int

        while (root[temp] != temp) {
            next = root[temp]
            root[temp] = rootElement
            temp = next
        }

        root[temp] = rootElement
    }

    private fun isSimilar(string1: String, string2: String): Boolean {
        if (string1 == string2)
            return true

        val count = mutableListOf<Int>()

        for (i in 0 until string1.length) {
            if (string1[i] != string2[i]) {
                count.add(i)
            }
            if (count.size > 2)
                return false
        }

        return true

    }
}

fun main() {

    val input = readLine()!!.split(",")

    val result = SimilarGroupsUnionFind().numSimilarGroups(input.toTypedArray())

    println(result)

}