package dataStructures

class MergeKSortedLists {
    fun mergeKLists(inputLists: Array<ListNode?>): ListNode? {

        val lists = inputLists.filter { it != null }

        if (lists.isEmpty())
            return null
        if (lists.size == 1)
            return lists[0]
        if (lists.size == 2)
            return mergeSort(lists[0]!!, lists[1]!!)

        val remainingList = lists.toMutableList()
        val mergedList = mergeSort(remainingList.removeAt(0)!!, remainingList.removeAt(1)!!)
        remainingList.add(mergedList)

        return mergeKLists(remainingList.toTypedArray())
    }

    private fun mergeSort(list1: ListNode, list2: ListNode): ListNode? {
        var head: ListNode? = null
        var firstList: ListNode? = list1
        var secondList: ListNode? = list2
        var current = head
        while (firstList != null && secondList != null){
            val sortedElement = when{
                (firstList.`val` <= secondList.`val`) -> {
                    val element = firstList.`val`
                    firstList = firstList.next
                    ListNode(element)
                }
                else -> {
                    val element = secondList.`val`
                    secondList = secondList.next
                    ListNode(element)
                }
            }

            if (current == null){
                head = sortedElement
                current = head
            }else {
                current.next = sortedElement
                current = current.next
            }

        }

        if (firstList != null){
            current!!.next = firstList
        }

        if (secondList != null){
            current!!.next = secondList
        }
        return head
    }
}

fun main() {

    val numberOfLists = readLine()!!.toInt()

    val lists = mutableListOf<ListNode>()

    for (i in 0 until numberOfLists) {
        val input = readLine()!!.split(",").map { it.toInt() }
        val head = ListNode(input[0])
        var current = head
        for (j in 1 until input.size) {
            current.next = ListNode(input[j])
            current = current.next!!
        }
        lists.add(head)
    }

    val mergeKSortedLists = MergeKSortedLists()
    val result = mergeKSortedLists.mergeKLists(lists.toTypedArray())

    var curr: ListNode? = result
    while (curr != null) {
        println("${curr.`val`}")
        curr = curr.next
    }
}