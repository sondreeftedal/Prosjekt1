package com.example.easylist.ListItems

import android.content.Intent
import com.example.easylist.EXTRA_LIST_INFO
import com.example.easylist.ListHolder
import com.example.easylist.ListItemDetails
import com.example.easylist.Lists.ListItem
import com.example.easylist.Lists.ListManager
import com.example.easylist.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list_item_details.*

class ListTaskManager {

    var onTask: ((List<ListItemTask>) -> Unit)? = null
    var onTaskUpdate: ((ListItemTask) -> Unit)? = null


    fun deleteItem(index:Int){

        var pickedList = ListHolder.PickedListItem

        val itemToRemove = ListHolder.PickedListItem?.tasks?.get(index)
        if (itemToRemove != null) {
            removeTaskFromDb(itemToRemove)
        }
        pickedList?.tasks?.removeAt(index)
        pickedList?.let { onTask?.invoke(it.tasks) }
        updateProgress()
    }
    fun load(){
        var pickedList = ListHolder.PickedListItem
        if(pickedList != null) {
            pickedList?.tasks?.let { onTask?.invoke(it) }
        }

    }

    fun updateList(taskItem: ListItemTask){
        onTaskUpdate?.invoke(taskItem)
    }

    fun addTask(taskItem: ListItemTask){
        ListHolder.PickedListItem?.tasks?.let { onTask?.invoke(it) }
        updateProgress()
        addTaskToDb(taskItem)
    }
    fun updateCheck(position: Int, check:Boolean, title:String,id:String){
        var pickedList = ListHolder.PickedListItem
        pickedList?.tasks?.set(position, ListItemTask(id,pickedList.id,title,check))
        updateProgress()

    }
    fun addTaskToDb(taskItem: ListItemTask){
        val database = Firebase.database
        val ref = database.getReference()
        var taskid = taskItem.id
        ref.child(Firebase.auth.currentUser.uid).child("Lists").child(taskItem.listId).child("tasks").child(taskid.toString()).setValue(taskItem)

    }
    fun removeTaskFromDb(task:ListItemTask){
        val db = Firebase.database
        val ref = db.getReference()
        val userid = Firebase.auth.currentUser.uid
        ref.child(userid).child("Lists").child("tasks").child(task.id).removeValue()
    }


    fun updateProgress(){
        var pickedList = ListHolder.PickedListItem
        if(pickedList != null){
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
        updateProgressDb(pickedList!!.progress)


    }}
    fun updateProgressDb(progress:Int){
        val db = Firebase.database
        val ref = db.getReference()
        ref.child(Firebase.auth.currentUser.uid).child("Lists").child(ListHolder.PickedListItem!!.id).child("progress").setValue(progress)

    }

        companion object{
            val Taskinstance = ListTaskManager()
        }
}




