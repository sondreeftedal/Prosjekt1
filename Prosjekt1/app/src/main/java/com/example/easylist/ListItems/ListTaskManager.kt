package com.example.easylist.ListItems

import android.content.Intent
import com.example.easylist.EXTRA_LIST_INFO
import com.example.easylist.ListHolder
import com.example.easylist.ListItemDetails
import com.example.easylist.Lists.ListItem
import com.example.easylist.Lists.ListManager
import com.example.easylist.MainActivity
import kotlinx.android.synthetic.main.activity_list_item_details.*

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

    fun updateList(taskItem: ListItemTask){
        onTaskUpdate?.invoke(taskItem)
    }

    fun addTask(taskItem: ListItemTask){
        ListHolder.PickedListItem?.tasks?.let { onTask?.invoke(it) }
        updateProgress()
    }
    fun updateCheck(position: Int, check:Boolean, title:String){
        var pickedList = ListHolder.PickedListItem
        pickedList?.tasks?.set(position, ListItemTask(pickedList.id,title,check))
        updateProgress()

    }


    fun updateProgress(){
        var pickedList = ListHolder.PickedListItem
        var taskList = pickedList?.tasks
        var totalTasks = taskList!!.size
        var checkedTasks = 0
        pickedList?.tasks?.forEach {
            if(it.check == true){
                checkedTasks +=1
            }
        }
        if(totalTasks != 0){
            var progress =  checkedTasks * 100 /totalTasks
            pickedList?.progress = progress

        }
        else{
            pickedList?.progress = 0

        }


    }

        companion object{
            val Taskinstance = ListTaskManager()
        }
}




