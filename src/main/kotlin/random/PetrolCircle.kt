package random

import java.util.*

data class PetrolPump(val petrol: Int, val distance: Int)

class PetrolCircle(val petrolPumps: Queue<PetrolPump>){
    fun findStart(): PetrolPump? {

        var start: PetrolPump?
        var tempList = LinkedList<PetrolPump>(petrolPumps)
        var totalIndex = 0
        do{

            var currentIndex = 0
            var leftOverPetrol = 0
            start = tempList.element()
            println("Main values: $currentIndex $leftOverPetrol $start")
            while(currentIndex < petrolPumps.size){
                var temp = tempList.poll()
                tempList.add(temp)
                if((leftOverPetrol + temp.petrol) < temp.distance){
                    println("Start: $start")
                    start = null
                    break
                }
                currentIndex ++
                leftOverPetrol += (temp.petrol - temp.distance)
                println("Inner values: $currentIndex $leftOverPetrol $start")
            }
            totalIndex++
        }while(start == null && totalIndex < petrolPumps.size)

        return start
    }

    /*
    * 3,2
    * 1,3
    * 5,1
    * */

}

fun main() {
    val petrolPumps = LinkedList<PetrolPump>()
    petrolPumps.add(PetrolPump(3, 2))
    petrolPumps.add(PetrolPump(2, 3))
    petrolPumps.add(PetrolPump(2, 1))
    petrolPumps.add(PetrolPump(1, 1))
    petrolPumps.add(PetrolPump(8, 10))
    petrolPumps.add(PetrolPump(5, 1))
    val petrolCircle = PetrolCircle(petrolPumps)

    println(petrolCircle.findStart())
}