package com.example.easylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easylist.databinding.ListItemBinding

class ListAdapter(private val listItem: MutableList<ListItem>, private val onListClicked:(ListItem) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){


    class ViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(listitem: ListItem){
            binding.ItemProgress.progress = listitem.progress
            binding.ItemText.text = listitem.text

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listItem[position]
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int = listItem.size

    public fun updateCollection(newList:List<ListItem>){
        listItem.clear()
        listItem.addAll(newList)
        notifyDataSetChanged()

    }
    }
