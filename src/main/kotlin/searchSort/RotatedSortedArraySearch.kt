package searchSort

class RotatedSortedArraySearch {
    var target: Int = 0
    lateinit var array: IntArray
    fun search(nums: IntArray, target: Int): Int {
        array = nums
        this.target = target
        var i = 0
        while (i<array.size-1) {
         if (array[i] > array[i + 1])
             break
            i++
        }

        val search1 = binarySearch(0, i)
        if (search1 == -1 && i< array.size-1){
            return binarySearch(i+1, array.size-1)
        }
        else
            return search1

    }

    private fun binarySearch(start: Int, end: Int): Int {
        if (array.isEmpty())
            return -1
        if (start == end || start > end){
            if (array[start] == target)
                return start
            else
                return -1
        }
        val mid = (start +end)/2

        if (array[mid] == target)
            return mid
        if(array[mid] > target)
            return binarySearch(start, mid-1)
        else
            return binarySearch(mid+1, end)
    }
}

fun main() {
    val array = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    val target = readLine()!!.toInt()

    val rotatedSortedArraySearch = RotatedSortedArraySearch()
    val search = rotatedSortedArraySearch.search(array, target)

    println(search)
}