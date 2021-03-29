package com.example.easylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easylist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_LIST_INFO: String = "com.example.easylist.listitem.info"
const val REQUEST_LIST_DETAILS:Int = 564587

class ListHolder{
        companion object{
            var PickedListItem:ListItem? = null
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



        fun addList(listname: String){
            val listItem = ListItem(0, listname ,emptyList())
            ListManager.instance.addList(listItem)

        }
        private fun onListClicked(listItem: ListItem):Unit{
            ListHolder.PickedListItem = listItem
            val intent = Intent(this, ListItemDetails::class.java).apply {
                putExtra(EXTRA_LIST_INFO, listItem)
            }
            startActivity(intent)

        }
       fun removeItem(index:Int){
           val index = index
           ListManager.instance.deleteItem(index)
       }

    }
