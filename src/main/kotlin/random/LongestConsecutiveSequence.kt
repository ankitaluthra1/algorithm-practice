package random

class LongestConsecutiveSequence {
    val cache = mutableMapOf<Long, Int>()
    lateinit var inputNumbers: IntArray
    fun longestConsecutive(inputNumbers: IntArray): Int {
        this.inputNumbers = inputNumbers

        var max = 0
        for (i in 0 until inputNumbers.size) {
            max = maxOf(getCountFor(inputNumbers[i].toLong()), max)
        }

        return max
    }

    private fun getCountFor(num: Long): Int {
        if (cache.contains(num))
            return cache[num]!!

        val nextNum: Long = num + 1

        if (cache.containsKey(nextNum)) {
            cache[num] = 1 + cache[nextNum]!!
        } else {
            var count = 0
            for (j in 0 until inputNumbers.size) {
                if (inputNumbers[j].toLong() == nextNum) {
                    count = getCountFor(nextNum)
                    break
                }
            }
            cache[num] = 1 + count
        }
        return cache[num]!!
    }
}

fun main() {
    val input = readLine()!!.split(",").map { it.toInt() }.toIntArray()

    val length = LongestConsecutiveSequence().longestConsecutive(input)

    println(length)

}