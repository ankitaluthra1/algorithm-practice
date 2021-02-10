package trasnferwise

class CycleRelay(val routeMap: Map<Int, List<Route>>) {

    fun findShortestTimeRoute(start: Int, end: Int): Long {

        if (!routeMap.containsKey(start)) {
            return Long.MAX_VALUE
        }

        val list = routeMap.getValue(start)

        var minTime = Long.MAX_VALUE

        for (i in 0 until list.size) {

            minTime = if (list[i].destination == end) {
                minOf(minTime, list[i].time.toLong())
            } else {
                val nestedRoute =  findShortestTimeRoute(
                    list[i].destination,
                    end
                )

                if (nestedRoute == Long.MAX_VALUE)
                    continue

                minOf(
                    list[i].time + nestedRoute, minTime
                )
            }
            if(start == 1) {
                println("For ${start}, min value: ${minTime}")
            }
        }

       // println("For ${start}, min value: ${minTime}")
        return minTime
    }


}

class Employee(val id: Int, val speed: Int, val maxDistance: Int)

class Route(val destination: Int, val time: Int, val distance: Int)

fun main() {

    val numberOfEmployees = readLine()!!.toInt()

    val employeeMap = mutableMapOf<Int, Employee>()
    for (i in 0 until numberOfEmployees) {
        val input = readLine()!!.split(" ")
        employeeMap.put(input[0].toInt(), Employee(input[0].toInt(), input[1].toInt(), input[2].toInt()))
    }

    val routes = readLine()!!.toInt()

    val routeMap = mutableMapOf<Int, List<Route>>()

    for (i in 0 until routes) {

        val input = readLine()!!.split(" ")

        val list = routeMap.getOrDefault(input[0].toInt(), mutableListOf()).toMutableList()
        val currentEmployee = employeeMap.get(input[0].toInt())!!
        val currentDistance = input[2].toInt()
        if (currentDistance > currentEmployee.maxDistance)
            continue
        val time = currentDistance / currentEmployee.speed
        val route = Route(input[1].toInt(), time, currentDistance)

        list.add(route)
        routeMap[input[0].toInt()] = list

    }

    val fromToInput = readLine()!!.split(" ")

    val cycleRelay = CycleRelay(routeMap)
    val shortestTimeRoute = cycleRelay.findShortestTimeRoute(fromToInput[0].toInt(), fromToInput[1].toInt())

    println(shortestTimeRoute)
}