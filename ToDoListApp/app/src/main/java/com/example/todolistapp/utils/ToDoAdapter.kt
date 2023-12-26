package com.example.todolistapp.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.EachTodoItemBinding
import android.graphics.Paint
import com.google.firebase.database.DatabaseReference

class ToDoAdapter(private val list: MutableList<ToDoData>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var listener: ToDoAdapterClicksInterface? = null

    fun setListener(listener: ToDoAdapterClicksInterface) {
        this.listener = listener
    }
    private lateinit var databaseRef: DatabaseReference

    inner class ToDoViewHolder(val binding: EachTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.todoTask.text = this.task

                binding.deleteTask.setOnClickListener {
                    listener?.onDeleteItemClicked(this)

                }

                binding.editTask.setOnClickListener {
                    listener?.onEditItemClicked(this)

                }

                val todoTask = binding.todoTask

                var isCheckboxTextRed = false

                binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (!isCheckboxTextRed) {
                            todoTask.setTextColor(Color.GRAY)
                            todoTask.paintFlags = todoTask.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                            isCheckboxTextRed = true
                        }
                    } else {
                        binding.todoTask.setTextColor(Color.BLACK)
                        todoTask.paintFlags = todoTask.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
                        isCheckboxTextRed = false
                    }
                }


            }
        }
    }
}

  interface ToDoAdapterClicksInterface {
    fun onDeleteItemClicked(toDoData: ToDoData)
    fun onEditItemClicked(toDoData: ToDoData)
}
