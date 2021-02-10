package dataStructures

class ListNode(val `val`: Int) {
    var next: ListNode? = null
}

class ReverseLinkedList {

    fun reverseList(head: ListNode?): ListNode? {

        var previous: ListNode? = null
        var current = head

        while (current != null){
            val temp = current.next
            current.next = previous
            previous = current
            current = temp
        }

        return previous
    }

}

fun main() {

    val five = ListNode(5)
    val four = ListNode(4)
    val three = ListNode(3)
    val two = ListNode(2)
    val one = ListNode(1)

    one.next = two
    two.next = three
    three.next = four
    four.next = five

    val reversedLinkedList = ReverseLinkedList()
    val result = reversedLinkedList.reverseList(one)
    var printedValue = result

    while (printedValue != null){
        println(printedValue.`val`)
        printedValue = printedValue.next
    }
}