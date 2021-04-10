package com.example.easylist.Lists

import com.example.easylist.ListItems.ListItemTask
var listCollection = mutableListOf<ListItem>()
class ListManager {


    val listCollection = mutableListOf<ListItem>()
    var onList: ((MutableList<ListItem>) -> Unit)? = null
    var onListUpdate: ((ListItem) -> Unit)? = null




    fun deleteItem(index:Int){
        listCollection.removeAt(index)
        onList?.invoke(listCollection)
    }
    fun load(){

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