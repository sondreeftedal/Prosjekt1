package com.example.easylist.Lists


import android.util.Log
import android.widget.Toast
import com.example.easylist.ListItems.ListItemTask
import com.example.easylist.ListItems.ListTaskManager
import com.example.easylist.MainActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ListManager {


    var listCollection = mutableListOf<ListItem>()
    var onList: ((MutableList<ListItem>) -> Unit)? = null
    var onListUpdate: ((ListItem) -> Unit)? = null




    fun deleteItem(index:Int){
        val listToRemove = listCollection[index]
        listToRemove.tasks = mutableListOf()
        deleteListFromDb(listToRemove)
        listCollection.removeAt(index)


        onList?.invoke(listCollection)
    }
    fun load(){
        getListsFromDb()

    }

    fun updateList(listItem: ListItem){
        onListUpdate?.invoke(listItem)
    }

    fun addList(listItem: ListItem){
        listCollection.add(listItem)
        onList?.invoke(listCollection)
        writeDataBase(listItem.id,listItem.progress,listItem.text,listItem.tasks)

    }
    fun writeDataBase(id:String, progress:Int, text:String,tasks:MutableList<ListItemTask>){
        val database = Firebase.database
        val myRef = database.getReferenceFromUrl("https://easylist-e6a21-default-rtdb.firebaseio.com/")

        var list = ListItem(id, progress,text,tasks)
        val user = Firebase.auth.currentUser

        myRef.child(user.uid).child("Lists").child(id).setValue(list)
    }
    fun deleteListFromDb(list:ListItem){
        val db = Firebase.database
        val ref = db.getReference()
        val userid= Firebase.auth.currentUser.uid
        ref.child(userid).child("Lists").child(list.id).removeValue()
        //ListTaskManager.Taskinstance.updateProgress()
    }
    fun getListsFromDb(){
       val db = Firebase.database
        val ref = db.getReference()
        ref.child(Firebase.auth.currentUser.uid).child("Lists").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    listCollection.clear()
                    val list = snapshot.children.forEach {
                        val id = it.child("id").value.toString()
                        val text = it.child("text").value.toString()
                        val progress = it.child("progress").value.toString().toInt()
                        val tasks = mutableListOf<ListItemTask>()

                        it.child("tasks").children.forEach { c->
                            val id = c.child("id").value.toString()
                            val listId = c.child("listId").value.toString()
                            val title = c.child("title").value.toString()
                            val check = c.child("check").value.toString().toBoolean()
                            tasks.add(ListItemTask(id,listId,title,check))
                        }
                        listCollection.add(ListItem(id,progress,text,tasks))
                        onList?.invoke(listCollection)
                        }  }

                }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



    companion object{
        val instance = ListManager()
    }

}