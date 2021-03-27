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
import com.example.easylist.ListItem

class ListAdapter(private var listItem: List<ListItem>, private val onListClicked:(ListItem) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){



    class ViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(listitem: ListItem, onListClicked: (ListItem) -> Unit){
            var itemPosition:Int = 0
            binding.ItemProgress.progress = listitem.progress
            binding.ItemText.text = listitem.text

            binding.itemCard.setOnClickListener {
                onListClicked(listitem)
            }

            binding.deleteButton.setOnClickListener{
                itemPosition = adapterPosition
                if(itemPosition != RecyclerView.NO_POSITION){
                    ListManager.instance.deleteItem(itemPosition)

                }
            }
        }

        }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listItem[position]
        holder.bind(currentItem, onListClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int = listItem.size

    public fun updateCollection(newList:MutableList<ListItem>){
        listItem = newList
        notifyDataSetChanged()
    }

    }
