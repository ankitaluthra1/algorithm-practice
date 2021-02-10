package dataStructures

class MinimumRepairCost {

    lateinit var root: MutableMap<Int, Int>

    fun repairCost(n: Int, edgeCostMap: MutableMap<Pair<Int, Int>, Int>): Int {

        root = mutableMapOf()
        for (i in 1..n) {
            root[i] = i
        }

        val sortedMap = edgeCostMap.toList().sortedBy { it.second }.toMap()

        var totalCost = 0
        val selectedEdgeMap = mutableMapOf<Pair<Int, Int>, Int>()
        sortedMap.forEach {
            if (!isCycleFormed(it.key.first, it.key.second)) {
                root.forEach {
                    print("(${it.key},${it.value}), ")
                }
                println()
                selectedEdgeMap.put(it.key, it.value)
                totalCost += it.value
            }
        }

        println("Selected edges:")
        selectedEdgeMap.forEach {
            println("${it.key}, ${it.value}")
        }

        return totalCost

    }

    private fun findParent(u: Int): Int {
        var uParent = u

        while (root[uParent] != uParent) {
            uParent = root[uParent]!!
        }

        return uParent
    }

    private fun isCycleFormed(u: Int, v: Int): Boolean {

        val uParent = findParent(u)
        val vParent = findParent(v)

        if (uParent == vParent)
            return true

        root[vParent] = uParent
        return false
    }


}

fun main() {

    val vertices = readLine()!!.toInt()

    val inputEdges = readLine()!!

    val edgeCostMap = mutableMapOf<Pair<Int, Int>, Int>()

    inputEdges.substring(1, inputEdges.length - 1).split("], [").forEach {
        val pair = Pair(it.split(", ")[0].toInt(), it.split(", ")[1].toInt())
        edgeCostMap.put(pair, 0)
    }

    val edgesToRepairInput = readLine()!!
    edgesToRepairInput.substring(1, edgesToRepairInput.length - 1).split("], [").map {
        val split = it.split(", ")
        val pair = Pair(split[0].toInt(), split[1].toInt())
        edgeCostMap.put(pair, split[2].toInt())
    }

    val result = MinimumRepairCost().repairCost(vertices, edgeCostMap)
    println(result)
}