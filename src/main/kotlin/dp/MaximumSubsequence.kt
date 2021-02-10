package dp

class MaximumSubsequence {
    var length1 = 0
    var length2 = 0
    var line1 = listOf<String>()
    var line2 = listOf<String>()
    lateinit var sequence1Matrix: Array<IntArray>

    fun input() {
        val readLine = readLine()!!
        length1 = readLine.split(" ")[0].toInt()
        length2 = readLine.split(" ")[1].toInt()
        line1 = readLine()!!.split(" ")
        line2 = readLine()!!.split(" ")

        sequence1Matrix = Array(length1) { IntArray(length2) }

        for (i in 0 until length1) {
            for (j in 0 until length2) {
                if (line1[i] == line2[j])
                    sequence1Matrix[i][j] = 1
                else
                    sequence1Matrix[i][j] = 0
            }
        }
//
//        println("--------------")
//        sequence1Matrix.forEach {
//            println(it.contentToString())
//        }
//        println("--------------")
    }

    fun findLongestSubsequence(){
        var max = emptyList<Int>()
        for (i in 0 until length1){
            for (j in 0 until length2){
                if (sequence1Matrix[i][j] == 1) {
                    val list = getSubsequence(i, j)
                    if (max.size < list.size)
                        max = list
                }
            }
        }

        println(max.joinToString(" "))
    }

    val cache = mutableMapOf<Pair<Int,Int>, List<Int>>()

    fun getSubsequence(row: Int, col: Int): List<Int>{

        if(cache.containsKey(Pair(row, col)))
            return cache[Pair(row, col)]!!

        if(row == length1 - 1 && col == length2 - 1){
            if(sequence1Matrix[row][col] == 1){
                val list = listOf(line1[row].toInt())
                cache.put(Pair(row, col),list)
                return list
            }else
                return emptyList()
        }

        if(row == length1 - 1){
            var list = listOf<Int>(line1[row].toInt())
            for (j in col+1 until length2){
                if(sequence1Matrix[row][j] == 1){
                    list = listOf(line1[row].toInt())
                    cache.put(Pair(row, j),list)
                    break
                }
            }
            return list
        }

        if(col == length2 - 1){
            var list = listOf(line2[col].toInt())
            for (i in row+1 until length1){
                if(sequence1Matrix[i][col] == 1){
                    list = listOf(line1[i].toInt())
                    cache.put(Pair(i, col),list)
                    break
                }
            }
            return list
        }

        var maxSubsequence = mutableListOf<Int>()

        for (i in row+1 until length1){
            for (j in col+1 until length2){
               if (sequence1Matrix[i][j] == 1){
                   val possibleSubsequence = getSubsequence(i, j)
                   if(maxSubsequence.size < possibleSubsequence.size){
                       maxSubsequence = possibleSubsequence.toMutableList()
                   }
               }
            }
        }

        maxSubsequence = (listOf(line1[row].toInt()) + maxSubsequence).toMutableList()
        cache.put(Pair(row, col), maxSubsequence)
        return  maxSubsequence
    }

}

fun main() {

    val maximumSubsequence = MaximumSubsequence()

    maximumSubsequence.input()

    maximumSubsequence.findLongestSubsequence()

}