package com.example.easylist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.easylist.ListItems.ListItemTask
import com.example.easylist.ListItems.ListTaskAdapter
import com.example.easylist.ListItems.ListTaskManager
import com.example.easylist.Lists.ListAdapter
import com.example.easylist.Lists.ListItem
import com.example.easylist.Lists.ListManager
import java.lang.RuntimeException

var listName:String = ""
var listProgress:Int=0
var index = 0



class AddNewList : DialogFragment() {
    private var listNameListener : OnFragmentAddNewListListener ?= null
    lateinit var addListName: EditText
    lateinit var submitForm: Button
    lateinit var cancelButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_add_new_list, container, false)

        addListName = rootView.findViewById(R.id.addNewListEditText)
        submitForm = rootView.findViewById(R.id.addNewListEnterButton)
        cancelButton = rootView.findViewById(R.id.addNewListCancelButton)
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentAddNewListListener){
            listNameListener = context
        } else{
            throw RuntimeException(context!!.toString() + "must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listNameListener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cancelButton.setOnClickListener{
            dismiss()
        }

        submitForm.setOnClickListener {
            listName = addListName.text.toString()
            listNameListener?.getListName(listName)

            dismiss()
        }


}
    fun getListName() {

    }

    interface OnFragmentAddNewListListener {
        fun getListName(listname:String)
    }


    companion object {
        @JvmStatic
        fun newInstance() = AddNewList()
        }
    }











