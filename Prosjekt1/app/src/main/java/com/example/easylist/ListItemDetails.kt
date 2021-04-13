package com.example.easylist


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easylist.ListItems.*
import com.example.easylist.Lists.ListAdapter
import com.example.easylist.databinding.ActivityListItemDetailsBinding
import com.example.easylist.Lists.ListItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception


class ListItemDetails : AppCompatActivity(), AddItemTasks.OnFragmentAddItemTasksListener{
    private lateinit var binding: ActivityListItemDetailsBinding
    private lateinit var listItem: ListItem
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTaskBtn.setOnClickListener {
            val newTaskForm = AddItemTasks()
            newTaskForm.show(supportFragmentManager, "AddItemTasks")
        }

        val recievedItem = intent.getParcelableExtra<ListItem>(EXTRA_LIST_INFO)
        if(recievedItem != null){
            listItem = recievedItem

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

        if (recievedItem != null) {
            database = Firebase.database
            reference = database.getReference(Firebase.auth.currentUser.uid).child("Lists").child(recievedItem!!.id).child("progress")
            reference.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    Log.e("cancel", error.toString())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val progress = snapshot.getValue()
                        binding.detailsProgress.progress = progress.toString().toInt()
                    }
                 }
                })

        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java).apply {

        }
        startActivity(intent)
    }







    companion object{
        val listItemDetails = ListItemDetails()
    }

    override fun getTaskName(taskname: String) {

    }


}








