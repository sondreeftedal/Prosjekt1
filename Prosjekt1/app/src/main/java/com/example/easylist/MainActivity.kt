package com.example.easylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easylist.databinding.ActivityMainBinding



    class MainActivity : AppCompatActivity() {
        lateinit var addButton: ImageButton
        private lateinit var binding: ActivityMainBinding
        private lateinit var listAdapter: ListAdapter

        private var listItem:MutableList<ListItem> = mutableListOf(
            ListItem(30,"Handleliste"),
            ListItem(20, "Huskeliste"),
            ListItem(0, "BucketList"),
            ListItem(90, "Obliger"),
            ListItem(70, "Sparemål"),
            ListItem(10, "Jobbing"),
            ListItem(25, "Lol")

        )



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.ListeRecyclerView.layoutManager = LinearLayoutManager(this)

            binding.ListeRecyclerView.adapter = ListAdapter(listItem, this::onListClicked)


            addButton = findViewById(R.id.addNewListButton)

            addButton.setOnClickListener {
                var newListForm = AddNewList()

                newListForm.show(supportFragmentManager, "addNewList")
            }

        }
        private fun onListClicked(listItem: ListItem):Unit{
            print("ClickClick")
        }

    }
