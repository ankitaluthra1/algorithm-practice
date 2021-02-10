package dataStructures

class CycleInGraph {

    var adjacentList = mutableMapOf<Int,MutableList<Int>>()
    lateinit var stack: MutableList<Int>
    private lateinit var visited: Array<Boolean>
    private val cache = mutableMapOf<Pair<Int, Int>, Boolean>()

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        visited = Array(numCourses) { false }

        for (i in 0 until prerequisites.size) {
            val src = prerequisites[i][0]
            val dst = prerequisites[i][1]

            val list = adjacentList.getOrDefault(src, mutableListOf())
            list.add(dst)
            adjacentList[src] = list

        }
        stack  = mutableListOf()

        adjacentList.forEach{
            if (!addInStack(it.key, mutableListOf())) {
                return false
            }
        }

        stack.forEach { println(it) }

        return true
    }

    private fun addInStack(element: Int, waitingStack: MutableList<Int>): Boolean {

        if (waitingStack.contains(element))
            return false

        if (adjacentList[element] == null || adjacentList[element]!!.size == 0) {
            stack.add(element)
            return true
        } else {
            waitingStack.add(element)
            for (i in 0 until adjacentList[element]!!.size) {
                val currentElement = adjacentList[element]!![i]
                val key = Pair(element, currentElement)
                if (cache.containsKey(key)){
                    return cache[key]!!
                }
                val waitingStack1 = mutableListOf<Int>()
                waitingStack1.addAll(waitingStack)
                val possible = addInStack(currentElement, waitingStack1)
                if (!possible)
                    return false
                cache[key] = possible
            }
            stack.add(element)
            return true
        }


    }

}

fun main() {

    val totalNodes = readLine()!!.toInt()

    val input = readLine()!!.replace(" ", "").split("],[")
        .map { it.replace("[", "").replace("]", "") }
        .map { it.split(",").map { it.toInt() }.toIntArray() }.toTypedArray()

    val cycleInGraph = CycleInGraph()

    val result = cycleInGraph.canFinish(totalNodes, input)

    println(result)
}