package random

import kotlin.random.Random

fun main(){

    val cases = 1000

    for (i in 0..20){

        val unionFindSocialNetwork = UnionFindSocialNetwork(cases)

        var count = 0

        while (!unionFindSocialNetwork.allConnected()) {
            val random1 = Random.nextInt(0, cases)
            val random2 = Random.nextInt(0, cases)
            //println("connecting $random1 $random2")
            unionFindSocialNetwork.connect(Pair(random1, random2))
            count++
        }

        println("Answer for $i try is $count")
     //   println(Arrays.toString(unionFindSocialNetwork.rootArray))

    }



}

class UnionFindSocialNetwork(members: Int){

    val rootArray = IntArray(members)
    val connections = IntArray(members)

    init {
        for (i in 0..(members-1)){
            rootArray[i] = i
        }

        for (i in 0..(members-1)){
            connections[i] = 0
        }
    }

    fun connect(friends: Pair<Int, Int>){

        var firstRoot: Int
        var requester: Int

        if (connections[friends.second] > connections[friends.first]){
         firstRoot = root(friends.second)
         requester = friends.first
     }
        else{
         firstRoot = root(friends.first)
         requester = friends.second
     }

     while (rootArray[requester] != requester) {
         requester = rootArray[requester]
         rootArray[requester] = firstRoot
         connections[firstRoot]++
     }

        rootArray[requester] = firstRoot

        connections[firstRoot]+=connections[requester]
    }

    fun root(value: Int): Int {
        var root = value
        while (rootArray[root] != root) {
          //  println("finding root of $root-${Arrays.toString(rootArray)}")
            root = rootArray[root]
        }
        return root
    }

    fun allConnected(): Boolean {
        if (rootArray.isEmpty())
            return true
        val firstRoot = root(0)
        for (i in 1..(rootArray.size-1)){
            if(root(i) != firstRoot){
                return false
            }
        }
        return true
    }

}