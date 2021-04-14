package com.example.easylist.ListItems

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItemTask(val id:String,val listId:String, var title:String, var check:Boolean):Parcelable{
    constructor() : this("","","",false)
}
