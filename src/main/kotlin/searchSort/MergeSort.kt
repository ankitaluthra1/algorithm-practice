package searchSort

class MergeSort {
    fun sort(start: Int, end: Int, list: List<Int>): List<Int> {

        if (end > start) {
            val mid = (start + end) / 2

            val sortedList1 = sort(start, mid, list)
            val sortedList2 = sort(mid + 1, end, list)

            return merge(sortedList1, sortedList2)
        }
        return listOf(list[start])
    }


    private fun merge(first: List<Int>, second: List<Int>): MutableList<Int> {

        var firstIndex = 0
        var secondIndex = 0
        val outputList = mutableListOf<Int>()
        while (firstIndex < first.size && secondIndex < second.size) {

            if (first[firstIndex] < second[secondIndex]) {
                outputList.add(first[firstIndex])
                firstIndex++
                continue
            }
            if (first[firstIndex] > second[secondIndex]) {
                outputList.add(second[secondIndex])
                secondIndex++
                continue
            }
            if (first[firstIndex] == second[secondIndex]) {
                outputList.add(first[firstIndex])
                outputList.add(second[secondIndex])
                firstIndex++
                secondIndex++
            }
        }

        if (firstIndex < first.size) {
            for (i in firstIndex until first.size) {
                outputList.add(first[i])
            }
        }
        if (secondIndex < second.size) {
            for (i in secondIndex until second.size) {
                outputList.add(second[i])
            }
        }

        return outputList

    }

}

fun main() {

    val mergeSort = MergeSort()

    val list = readLine()!!.split(",").map { it.toInt() }

    val sortedList: List<Int> = mergeSort.sort(0, list.size - 1, list)

    sortedList.forEach {
        print("${it} ")
    }
    println()

}