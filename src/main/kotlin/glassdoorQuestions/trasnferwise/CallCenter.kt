package glassdoorQuestions.trasnferwise

import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class Call(val subject: String) {
    fun attend() {
        println("Attending call for ${subject}...")
        Thread.sleep(5000)
    }
}

class Agent(val calls: Queue<Call>) : Runnable {
    override fun run() {
        while (true) {
            val call = calls.poll() ?: break
            call.attend()
        }
    }

}

class CallCenter(private val calls: BlockingQueue<Call>, private val agents: List<Agent>) {

    val executerService = Executors.newFixedThreadPool(5)
    fun startWork(){

        for (i in 0 until agents.size){
            executerService.submit(Thread(agents[i]))
        }

    }

}

fun main() {

    val calls = LinkedBlockingQueue<Call>()

    calls.add(Call("refund"))
    calls.add(Call("technical glitch"))
    calls.add(Call("customer support"))
    calls.add(Call("complaint"))
    calls.add(Call("complaint"))

    val agents = mutableListOf<Agent>()

    agents.add(Agent(calls))
    agents.add(Agent(calls))
    agents.add(Agent(calls))
    agents.add(Agent(calls))

    val callCenter = CallCenter(calls, agents)

    callCenter.startWork()

}