package com.example.easylist.ListItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easylist.AddNewList
import com.example.easylist.ListHolder
import com.example.easylist.ListItemDetails
import com.example.easylist.Lists.ListItem
import com.example.easylist.Lists.ListManager
import com.example.easylist.databinding.ActivityListItemDetailsBinding
import com.example.easylist.databinding.ListItemTaskBinding
import kotlinx.android.synthetic.main.activity_list_item_details.*

@Suppress("DEPRECATION")
public class ListTaskAdapter(private var listItemTasks: MutableList<ListItemTask>): RecyclerView.Adapter<ListTaskAdapter.ViewHolder>() {

    class ViewHolder(val binding:ListItemTaskBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listTask: ListItemTask) {
            var taskPosition = 0

            binding.taskName.text = listTask.title
            binding.taskCheckBox.isChecked = listTask.check

        }
    }


    override fun getItemCount(): Int = listItemTasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listTask = listItemTasks[position]
        holder.bind(listTask)


        holder.binding.deleteTaskBtn.setOnClickListener{
            val taskPosition = position
            if(taskPosition != RecyclerView.NO_POSITION){
                ListTaskManager.Taskinstance.deleteItem(taskPosition)

            }}



        holder.binding.taskCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            val taskPosition = position
            var check = isChecked
            if(taskPosition != RecyclerView.NO_POSITION){
                ListTaskManager.Taskinstance.updateCheck(taskPosition, check,listTask.title)
                ListTaskManager.Taskinstance.updateProgress()


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    public fun updateTasks(newTasks:MutableList<ListItemTask>){
        listItemTasks = newTasks
        notifyDataSetChanged()
    }



    companion object{
        val listTaskAdapter = ListTaskAdapter
    }







}