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
        deleteListFromDb(listToRemove)
        listCollection.removeAt(index)
        onList?.invoke(listCollection)
    }
    fun load(){
        //getListsFromDb()
        onList?.invoke(listCollection)
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
        ListTaskManager.Taskinstance.updateProgress()
    }
    /*fun getListsFromDb(){
        val db = Firebase.database
        val ref = db.getReference()
        ref.child(Firebase.auth.currentUser.uid).child("Lists").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    listCollection.clear()
                    for(l in snapshot.children){
                        val list = l.getValue(ListItem::class.java)
                        if (list != null) {
                            val listToAdd = ListItem(list.id,list.progress,list.text, mutableListOf())
                            listCollection.add(listToAdd)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }*/



    companion object{
        val instance = ListManager()
    }

}