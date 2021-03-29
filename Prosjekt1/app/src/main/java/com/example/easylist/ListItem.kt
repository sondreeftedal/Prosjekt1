package com.example.easylist

import android.os.Parcelable
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(val progress:Int, val text:String, val tasks:List<ListItemTask>):Parcelable