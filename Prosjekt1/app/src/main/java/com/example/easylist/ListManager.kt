package com.example.easylist

import android.content.Context
import com.example.easylist.ListItem

class ListManager {

    private lateinit var listCollection: MutableList<ListItem>

    var onList: ((MutableList<ListItem>) -> Unit)? = null
    var onListUpdate: ((ListItem) -> Unit)? = null




    fun deleteItem(index:Int){
        listCollection.removeAt(index)
        onList?.invoke(listCollection)
    }
    fun load(){
        listCollection = mutableListOf(
            ListItem(50,"Test" ,emptyList()),
            ListItem(70,"Test2" ,emptyList())
        )
        onList?.invoke(listCollection)
    }

    fun updateList(listItem: ListItem){
        onListUpdate?.invoke(listItem)
    }

    fun addList(listItem: ListItem){
        listCollection.add(listItem)
        onList?.invoke(listCollection)
    }

    companion object{
        val instance = ListManager()
    }

}