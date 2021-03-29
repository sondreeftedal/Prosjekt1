package com.example.easylist

class ListTaskManager {
    private lateinit var taskCollection: MutableList<ListItemTask>
    private lateinit var listCollection:MutableList<ListItem>

    var onTask: ((List<ListItemTask>) -> Unit)? = null
    var onTaskUpdate: ((ListItemTask) -> Unit)? = null




    fun deleteItem(index:Int){
        taskCollection.removeAt(index)
        onTask?.invoke(taskCollection)
    }
    fun load(){
        taskCollection = mutableListOf(
            ListItemTask("Test1",false),
            ListItemTask("Test2",true)
        )
        onTask?.invoke(taskCollection)
    }

    fun updateList(taskItem: ListItemTask){
        onTaskUpdate?.invoke(taskItem)
    }

    fun addTask(taskItem: ListItemTask){
        taskCollection.add(taskItem)
        onTask?.invoke(taskCollection)
    }

    companion object{
        val Taskinstance = ListTaskManager()
    }
}

