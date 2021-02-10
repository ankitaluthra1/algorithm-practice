package dataStructures

class BridgeInGraph {

    fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {

        val adjList = Array(n){ mutableListOf<Int>()}

        for (i in 0 until connections.size) {
            val uList = adjList[connections[i][0]]
            uList.add(connections[i][1])
            adjList[connections[i][0]] = uList

            val vList = adjList[connections[i][1]]
            vList.add(connections[i][0])
            adjList[connections[i][1]] = vList
        }

        val crititcalEdges = mutableListOf<List<Int>>()



        for (i in 0 until adjList.size){

        }

        return crititcalEdges
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val connectionString = readLine()!!
    val connections = connectionString.substring(2, connectionString.length - 2).split("],[").map {
        val edge = it.split(",")
        listOf(edge[0].toInt(), edge[1].toInt())
    }

    val bridgeInGraph = BridgeInGraph()
    val result = bridgeInGraph.criticalConnections(n, connections)

    result.forEach {
        println("${it[0]},${it[1]}")
    }


}