package com.example.easylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment


class AddNewList : DialogFragment() {
    lateinit var addListName: EditText
    lateinit var submitForm: Button
    lateinit var cancelButton: Button

    var listName:String = ""
    var listProgress:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_add_new_list, container, false)

        addListName = rootView.findViewById(R.id.addNewListEditText)
        submitForm = rootView.findViewById(R.id.addNewListEnterButton)
        cancelButton = rootView.findViewById(R.id.addNewListCancelButton)

        cancelButton.setOnClickListener{
            dismiss()
        }


        submitForm.setOnClickListener{
            listName = addListName.text.toString()
            listProgress = 0
            dismiss()
        }


        return rootView
    }







}
