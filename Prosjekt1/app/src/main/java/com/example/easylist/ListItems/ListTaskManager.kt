package com.example.easylist.ListItems

import com.example.easylist.ListHolder
import com.example.easylist.ListItemDetails
import com.example.easylist.Lists.ListItem

class ListTaskManager {

    var onTask: ((List<ListItemTask>) -> Unit)? = null
    var onTaskUpdate: ((ListItemTask) -> Unit)? = null


    fun deleteItem(index:Int){
        var pickedList = ListHolder.PickedListItem
        pickedList?.tasks?.removeAt(index)
        pickedList?.let { onTask?.invoke(it.tasks) }
    }
    fun load(){

        var pickedList = ListHolder.PickedListItem
        pickedList?.tasks?.let { onTask?.invoke(it) }
    }

    fun updateList(taskItem: ListItemTask, listItem: ListItem){
        onTaskUpdate?.invoke(taskItem)
    }

    fun addTask(taskItem: ListItemTask){
        ListHolder.PickedListItem?.tasks?.let { onTask?.invoke(it) }
    }


    companion object{
        val Taskinstance = ListTaskManager()
    }
}


