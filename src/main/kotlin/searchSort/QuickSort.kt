package searchSort

class QuickSort(val array: IntArray) {

    fun quickSort(start: Int, end: Int) {

        if (start >= end)
            return

        val pivot = (start + end) / 2

        val partitionIndex = partition(start, end, array[pivot])

        quickSort(start, partitionIndex-1)
        quickSort(partitionIndex, end)

    }

    fun partition(start: Int, end: Int, pivot: Int): Int {

        var left = start
        var right = end

        while (left <= right) {
            while (array[left] < pivot)
                left++

            while (array[right] > pivot)
                right--

            if (left <= right) {
                val temp = array[left]
                array[left] = array[right]
                array[right] = temp
                left++
                right--
            }
        }


        return left

    }

}

fun main() {

    val input = readLine()!!.split(" ").map { it.toInt() }
    val quickSort = QuickSort(input.toIntArray())
    quickSort.quickSort(0, input.size-1)

    println(quickSort.array.contentToString())

}