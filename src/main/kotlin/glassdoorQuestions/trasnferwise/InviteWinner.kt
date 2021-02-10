package glassdoorQuestions.trasnferwise

fun main() {

    val referralCount = readLine()!!.toInt()

    val employeeReferrals = mutableMapOf<Int, MutableList<Int>>()
    for (i in 0 until referralCount) {
        val input = readLine()!!.split(" ")
        val currentList = employeeReferrals.getOrDefault(input[1].toInt(), mutableListOf())
        currentList.add(input[0].toInt())
        employeeReferrals.put(input[1].toInt(), currentList)
    }

    val employeeReferralProgram = EmployeeReferralProgram(employeeReferrals)
    employeeReferralProgram.printHighestReferrals()

}

class EmployeeReferralProgram(val referralMap: Map<Int, List<Int>>) {

    fun printHighestReferrals() {
        var highestReferralEmployee = -1
        var highestReferralsCount = 0
        for (i in 0 until referralMap.keys.size) {
            val currentEmployee = referralMap.keys.elementAt(i)
            val currentEmployeeReferralCount = getCountFor(currentEmployee)
            if (currentEmployeeReferralCount > highestReferralsCount){
                highestReferralEmployee = currentEmployee
                highestReferralsCount = currentEmployeeReferralCount
            }
        }

        println(highestReferralEmployee)
        println(highestReferralsCount)

    }

    private fun getCountFor(key: Int): Int {
        if (!referralMap.containsKey(key)) {
            return 0
        }
        val directReferrals = referralMap[key]!!
        var count = 0
        for (i in 0 until directReferrals.size) {
            count += 1 + getCountFor(directReferrals[i])
        }
        return count
    }
}
