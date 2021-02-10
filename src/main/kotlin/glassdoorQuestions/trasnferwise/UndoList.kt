package glassdoorQuestions.trasnferwise

import java.util.*

interface Undoable {
    fun undo()
}

abstract class UndoableAction{
     abstract fun execute()
}

class UndoList<T> : LinkedList<T>(), Undoable {

    val dataList = mutableListOf<T>()

    val undoableActions = mutableListOf<UndoableAction>()

    override fun add(element: T): Boolean {
        this.undoableActions.add(UndoInsertAction(element))
        return dataList.add(element)
    }

    override fun remove(element: T): Boolean {
        this.undoableActions.add(UndoRemoveAction(element))
        return dataList.remove(element)
    }

    override fun undo() {
        val lastIndex = this.undoableActions.size - 1
        this.undoableActions.removeAt(lastIndex).execute()
    }

    inner class UndoInsertAction(private val operand: T) : UndoableAction() {

        override fun execute() {
            dataList.remove(operand)
        }
    }

    inner class UndoRemoveAction(private val operand: T) : UndoableAction() {

        override fun execute() {
            dataList.add(operand)
        }
    }

}