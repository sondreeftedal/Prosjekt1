package com.example.easylist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.lang.RuntimeException


class AddItemTasks : DialogFragment() {

        private var taskNameListener : OnFragmentAddItemTasksListener ?= null


        lateinit var addTaskName: EditText
        lateinit var submitTaskForm: Button
        lateinit var cancelTaskButton: Button


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView: View = inflater.inflate(R.layout.fragment_add_item_tasks, container, false)

            addTaskName = rootView.findViewById(R.id.addNewTaskEditText)
            submitTaskForm = rootView.findViewById(R.id.addNewTaskEnterButton)
            cancelTaskButton = rootView.findViewById(R.id.addNewTaskCancelButton)
            return rootView
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (context is OnFragmentAddItemTasksListener){
                taskNameListener = context
            } else{
                throw RuntimeException(context!!.toString() + "must implement OnFragmentInteractionListener")
            }
        }

        override fun onDetach() {
            super.onDetach()
            taskNameListener = null
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            cancelTaskButton.setOnClickListener{
                dismiss()
            }



            submitTaskForm.setOnClickListener{
                listName = addTaskName.text.toString()
                taskNameListener?.getTaskName(listName)

                dismiss()

            }

        }

        interface OnFragmentAddItemTasksListener {
            fun getTaskName(taskname:String)
        }

        companion object {
            @JvmStatic
            fun newInstance() = AddNewList()
        }
    }