package com.example.easylist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easylist.databinding.ActivityListItemDetailsBinding
import com.example.easylist.ListItemTask
import com.example.easylist.ListTaskAdapter
import com.google.android.material.dialog.MaterialDialogs


class ListItemDetails : AppCompatActivity(), AddItemTasks.OnFragmentAddItemTasksListener {
    private lateinit var binding: ActivityListItemDetailsBinding
    private lateinit var listItem: ListItem
    private lateinit var listTasks:MutableList<ListItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recievedItem = intent.getParcelableExtra<ListItem>(EXTRA_LIST_INFO)
        if(recievedItem != null){
            listItem = recievedItem
            binding.detailsProgress.progress = recievedItem.progress
            binding.detailsTitle.text = recievedItem.text
        }
        else{
            finish()
        }


        binding.listItemTaskListing.layoutManager = LinearLayoutManager(this)
        binding.listItemTaskListing.adapter = ListTaskAdapter(mutableListOf())

        ListTaskManager.Taskinstance.onTask = {
            (binding.listItemTaskListing.adapter as ListTaskAdapter).updateTasks(it.toMutableList())
        }
        ListTaskManager.Taskinstance.load()





        binding.addTaskBtn.setOnClickListener {
            val newTaskForm = AddItemTasks()
            newTaskForm.show(supportFragmentManager, "AddItemTasks")
        }

    }

    override fun getTaskName(taskname: String) {
        val taskItem = ListItemTask(taskname, false)
        ListTaskManager.Taskinstance.addTask(taskItem)
    }


}