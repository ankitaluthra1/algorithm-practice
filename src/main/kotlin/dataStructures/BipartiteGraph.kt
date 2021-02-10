package dataStructures

class BipartiteGraph {

    lateinit var graph: Array<IntArray>
    lateinit var visited: Array<Boolean>

    fun isBipartite(graph: Array<IntArray>): Boolean {
        if (graph.size < 2)
            return true

        this.graph = graph
        this.visited = Array(graph.size) { false }

        graph.forEach {
            println(it.contentToString())
        }

        println("-------------")

        this.visited = Array(graph.size) { false }

        val group1 = mutableSetOf<Int>()
        val group2 = mutableSetOf<Int>()

        for (i in 0 until visited.size) {
            if (!visited[i])
                if (!addToGroup(i, group1, group2))
                    return false
        }
        return true
    }

    private fun addToGroup(index: Int, group1: MutableSet<Int>, group2: MutableSet<Int>): Boolean {
        if (this.visited[index]) {
            if (group2.contains(index))
                return false
            return true
        }

        group1.add(index)
        this.visited[index] = true

        val connectVertices = this.graph[index]

        for (i in 0 until connectVertices.size) {
            if (!addToGroup(this.graph[index][i], group2, group1))
                return false
        }
        return true
    }

}

fun main() {

    val numOfVertices = readLine()!!.toInt()

    val graph: Array<IntArray> = Array(numOfVertices) { IntArray(0) }

    for (i in 0 until numOfVertices) {
        val array = readLine()!!.split(",").map { it.toInt() }.toIntArray()
        graph[i] = array
    }

    val bipartiteGraph = BipartiteGraph()
    val result = bipartiteGraph.isBipartite(graph)

    println(result)

}
