package com.example.easylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easylist.databinding.ActivityListItemDetailsBinding

private lateinit var binding: ActivityListItemDetailsBinding
private lateinit var listItem: ListItem

class ListItemDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recievedItem = intent.getParcelableExtra<ListItem>(EXTRA_LIST_INFO)
        if(recievedItem != null){
            listItem = recievedItem
        }
        else{
            finish()
        }
        binding.detailsProgress.progress = listItem.progress
        binding.detailsTitle.text = listItem.text
    }
}