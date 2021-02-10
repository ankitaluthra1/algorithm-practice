package dataStructures

import java.io.File

class AlienDictionaryGraph {

    val adjList = mutableMapOf<Char, MutableSet<Char>>()
    val cache = mutableMapOf<Char, MutableSet<Char>>()

    fun findSequenceOfLetters(words: List<String>): String {

        val vertices = mutableSetOf<Char>()

        for (i in 0 until words.size - 1) {

            var checkingIndex = 0
            while (words[i].toCharArray()[checkingIndex] == words[i + 1].toCharArray()[checkingIndex]) {
                checkingIndex++
                if (checkingIndex >= words[i].length || checkingIndex >= words[i + 1].length) {
                    checkingIndex = -1
                    break
                }
            }

            if (checkingIndex != -1) {
                val u = words[i].toCharArray()[checkingIndex]
                val v = words[i + 1].toCharArray()[checkingIndex]
                val edges = adjList.getOrDefault(u, mutableSetOf())
                edges.add(v)
                adjList[u] = edges
                vertices.add(u)
                vertices.add(v)
            }

        }

        val startVertex = mutableSetOf<Char>()
        val endVertex = mutableSetOf<Char>()

        val keys = adjList.keys
        val values = adjList.values.flatten().toSet()

        for (i in 0 until vertices.size) {
            val element = vertices.elementAt(i)
            if (!keys.contains(element))
                endVertex.add(element)
            if (!values.contains(element))
                startVertex.add(element)
        }

        if (startVertex.size > 1 || endVertex.size > 1 || startVertex.size == 0)
            return "INVALID"

        val sortedList = topologicalSort(startVertex.elementAt(0), mutableSetOf())
        if (sortedList.isEmpty())
            return "INVALID"

        return sortedList.reversed().joinToString(",")

    }

    private fun topologicalSort(currentElement: Char, preElements: MutableSet<Char>): MutableSet<Char> {

        if (preElements.contains(currentElement)){
            return emptySet<Char>().toMutableSet()
        }

        if (cache.containsKey(currentElement)){
            return cache[currentElement]!!
        }

        if (!adjList.containsKey(currentElement)) {
            return mutableSetOf(currentElement)
        }

        val adjVertices = adjList[currentElement]!!
        val connectedVertices = mutableSetOf<Char>()
        val currentPreElments = mutableSetOf<Char>()
        currentPreElments.addAll(preElements)
        currentPreElments.add(currentElement)
        for (i in 0 until adjVertices.size) {
            val elements = topologicalSort(adjVertices.elementAt(i), currentPreElments)
            if (elements.isEmpty())
                return emptySet<Char>().toMutableSet()
            connectedVertices.addAll(elements)
        }

        connectedVertices.add(currentElement)
        cache[currentElement] = connectedVertices
        return connectedVertices
    }

}

fun main() {

    val file = File("/Users/ankital/Projects/sample-projects/algos/src/main/resources/nobita-and-alien-dictionary-testcases/input/input00.txt").readLines()
    val testCases = file[0].toInt()

    var inputSize = 1
    for (i in 0 until testCases) {
        val wordSize = file[inputSize].toInt()
        val words = mutableListOf<String>()
        for (j in 0 until wordSize) {
            inputSize++
            words.add(file[inputSize])
        }
        inputSize++
        val sequence = AlienDictionaryGraph().findSequenceOfLetters(words)
        println(sequence)
    }

}