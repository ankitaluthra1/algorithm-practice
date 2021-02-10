package searchSort

class PancakeSort {

    fun pancakeSort(input: IntArray): IntArray {
        val sortedArray = input.sortedArrayDescending()
        val flips = mutableListOf<Int>()
        for (i in (0 until sortedArray.size-1)) {
            println("array: ${input.contentToString()}")
            if (sortedArray.reversedArray().contentEquals(input))
                break

            if (sortedArray[i] == input[input.size -1 - i])
                continue

            val maxElementIndex = input.indexOf(sortedArray[i])
            if(maxElementIndex != 0) {
                flip(input, maxElementIndex)
                flips.add(maxElementIndex + 1)
            }
            if (sortedArray.reversedArray().contentEquals(input))
                break

            flip(input, input.size -1 - i)
            flips.add(input.size - i)

            println("for ${sortedArray[i]} sorted array index is $maxElementIndex --- $flips")
        }

        return flips.toIntArray()

    }

    private fun flip(input: IntArray, end: Int) {

        var left = 0
        var right = end

        while (left < right) {
            val temp = input[left]
            input[left] = input[right]
            input[right] = temp
            left++
            right--
        }

    }

}

fun main() {
    val input = readLine()!!.split(" ").map { it.toInt() }

    val pancakeSort = PancakeSort()

    val result = pancakeSort.pancakeSort(input.toIntArray())

    println(result.contentToString())

}