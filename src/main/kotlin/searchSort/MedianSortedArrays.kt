package searchSort

class MedianSortedArrays{

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        var i = 0
        var j = 0
        var k = -1

        val temp = IntArray(nums1.size + nums2.size)
        while (i<nums1.size && j<nums2.size)
        {
            k++
            if(nums1[i] < nums2[j]) {
                temp[k] = nums1[i]
                i++
            }
            else{
                temp[k] = nums2[j]
                j++
            }
        }
        println(temp.contentToString())
        for (index in i until nums1.size ){
            k++
            temp[k] = nums1[index]
        }

        for (index in j until nums2.size ){
            k++
            temp[k] = nums2[index]
        }


        println(temp.contentToString())
        val medianIndex = (0 + temp.size-1)/2
        if ((temp.size) % 2 == 0){
            val median = (temp[medianIndex].toDouble() + temp[medianIndex +1].toDouble())/2
            return median
        }

        return (temp[medianIndex].toDouble())
    }
}

fun main() {

    val array1 = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    val array2 = readLine()!!.split(",").map { it.toInt() }.toIntArray()

    val medianSortedArrays = MedianSortedArrays()
    val res = medianSortedArrays.findMedianSortedArrays(array1, array2)

    println(res)

}