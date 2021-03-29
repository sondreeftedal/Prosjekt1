package com.example.easylist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItemTask(val title:String, val check:Boolean):Parcelable
