package com.example.easylist.ListItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.easylist.databinding.ListItemTaskBinding



 class ListTaskAdapter(private var listItemTasks: MutableList<ListItemTask>): RecyclerView.Adapter<ListTaskAdapter.ViewHolder>() {

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
            val check = isChecked
            if(taskPosition != RecyclerView.NO_POSITION){
                ListTaskManager.Taskinstance.updateCheck(taskPosition, check,listTask.title,listTask.id)
                ListTaskManager.Taskinstance.updateProgress()


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun updateTasks(newTasks:MutableList<ListItemTask>){
        listItemTasks = newTasks
        notifyDataSetChanged()
    }



    companion object{
        val listTaskAdapter = ListTaskAdapter
    }







}