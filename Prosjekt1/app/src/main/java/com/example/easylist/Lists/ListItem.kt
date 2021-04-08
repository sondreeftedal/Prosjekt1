package com.example.easylist.Lists

import android.os.Parcelable
import com.example.easylist.ListItems.ListItemTask
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(val id: Int,val progress:Int, val text:String, var tasks:MutableList<ListItemTask>):Parcelable

