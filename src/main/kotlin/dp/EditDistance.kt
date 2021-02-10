package dp

import kotlin.math.min

class EditDistance {

    var word: String = ""
    var word2: String = ""
    val cache = mutableMapOf<Pair<Int, Int>, Int>()
    fun minDistance(word1: String, word2: String): Int {
        word = word1
        this.word2 = word2

        var index1 = word1.length - 1
        var index2 = word2.length - 1

        if (word.length == 0)
            return this.word2.length

        if (this.word2.length == 0)
            return word.length

        val dp = dp(index1, index2)
        return dp
    }

    private fun dp(index1: Int, index2: Int): Int {
        val key = Pair(index1, index2)

        if (cache.containsKey(key)) {
            return cache[key]!!
        }

        if (index1 == 0 && index2 ==0){
            var currentDistance = 1
            if (word.toCharArray()[index1] == word2.toCharArray()[index2])
                currentDistance = 0
            return currentDistance
        }

        if (index1 == 0) {
            var moves = index2 - 0 + 1
            for(i in 0..index2){
                if (word2[i] == word[0]) {
                    moves--
                    break
                }
            }

            cache[key] = moves
            return moves
        }
        if (index2 == 0) {
            var moves = index1 - 0 +1
            for(i in 0..index1){
                if (word2[index2] == word[i]) {
                    moves--
                    break
                }
            }

            cache[key] = moves
            return moves
        }

        var currentDistance = 1
        if (word.toCharArray()[index1] == word2.toCharArray()[index2]) {
            currentDistance = 0
            val result = currentDistance + dp(index1 - 1, index2 - 1)
            cache[key] = result
            return result

        }

        val first = dp(index1, index2 - 1)
        val second = dp(index1 - 1, index2)
        val third = dp(index1 - 1, index2 - 1)

        val result = currentDistance + min(first, min(second, third))

        cache[key] = result
        return result

    }
}

fun main() {
    val editDistance = EditDistance()

    val word1 = readLine()!!
    val word2 = readLine()!!

    val result = editDistance.minDistance(word1, word2)
    println(result)
}