package com.example.easylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easylist.Authentication.authActivity
import com.example.easylist.Lists.ListItem
import com.example.easylist.Lists.ListAdapter
import com.example.easylist.Lists.ListManager
import com.example.easylist.databinding.ActivityMainBinding
import com.google.android.gms.auth.GoogleAuthUtil.getToken
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*


const val EXTRA_LIST_INFO: String = "com.example.easylist.listitem.info"
const val REQUEST_LIST_DETAILS:Int = 564587

class ListHolder{
        companion object{
            var PickedListItem: ListItem? = null
        }
    }


class MainActivity : AppCompatActivity(), AddNewList.OnFragmentAddNewListListener {


        private lateinit var binding: ActivityMainBinding
        lateinit var addButton: ImageButton

        override fun getListName(listname: String) {
            addList(listname)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.ListeRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.ListeRecyclerView.adapter = ListAdapter(emptyList<ListItem>(), this::onListClicked)
            checkCurrentUser()


            ListManager.instance.onList = {
                (binding.ListeRecyclerView.adapter as ListAdapter).updateCollection(it as MutableList<ListItem>)
            }

            ListManager.instance.load()

            addButton = findViewById(R.id.addNewListButton)

            addButton.setOnClickListener {
                var newListForm = AddNewList()
                newListForm.show(supportFragmentManager, "addNewList")

            }



            }
        fun checkCurrentUser(){
            val user = Firebase.auth.currentUser
            if (user != null){
                val userId= user.uid
            }
            else{
                val intent = Intent(this, authActivity::class.java)
                startActivity(intent)
            }
        }


        fun addList(listname: String){
            val id = UUID.randomUUID().toString()
            val listItem = ListItem(id,0, listname , mutableListOf())
            ListManager.instance.addList(listItem)


        }
        private fun onListClicked(listItem: ListItem):Unit{
            ListHolder.PickedListItem = listItem
            val intent = Intent(this, ListItemDetails::class.java).apply {
                putExtra(EXTRA_LIST_INFO, listItem)
            }
            startActivity(intent)
        }




    }
