package com.example.easylist.ListItems

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItemTask(val itemId:Int, var title:String, var check:Boolean):Parcelable
