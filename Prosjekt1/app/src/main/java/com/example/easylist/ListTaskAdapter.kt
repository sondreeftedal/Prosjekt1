package com.example.easylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.easylist.databinding.ListItemTaskBinding
import kotlinx.android.synthetic.main.activity_list_item_details.view.*

public class ListTaskAdapter(private var listItemTasks: MutableList<ListItemTask>): RecyclerView.Adapter<ListTaskAdapter.ViewHolder>() {

    class ViewHolder(val binding:ListItemTaskBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listTask: ListItemTask) {
            var taskPosition = 0
            var checkedboxes = 0
            var totalProgress = 0
            binding.taskName.text = listTask.title
            binding.taskCheckBox.isChecked = listTask.check


            binding.deleteTaskBtn.setOnClickListener{
                taskPosition = adapterPosition
                if(taskPosition != RecyclerView.NO_POSITION){
                    ListTaskManager.Taskinstance.deleteItem(taskPosition)

                }


        }
    }}

    override fun getItemCount(): Int = listItemTasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listTask = listItemTasks[position]
        holder.bind(listTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    public fun updateTasks(newTasks:MutableList<ListItemTask>){
        listItemTasks = newTasks
        notifyDataSetChanged()
    }



}