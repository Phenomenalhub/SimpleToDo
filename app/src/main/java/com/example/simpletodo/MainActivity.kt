package com.example.simpletodo

import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils.readLines
import org.apache.commons.io.IOUtils.readLines
import java.io.File
import java.io.IOException
import java.io.InterruptedIOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskitemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongCheckListener = object : TaskitemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)

                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        loadItems()


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskitemAdapter(listOfTasks, onLongCheckListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val inputTextField = findViewById<EditText>(R.id.addToDoField)


        findViewById<Button>(R.id.button).setOnClickListener {
            val userInputtedTask = inputTextField.text.toString()
            listOfTasks.add(userInputtedTask)
            adapter.notifyItemInserted(listOfTasks.size-1)

            inputTextField.setText("")

            saveItems()
        }

    }
    fun getDataFile() : File {
        return File(filesDir, "data.txt")
    }

    fun loadItems() {
        try {
            listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException){
            ioException.printStackTrace()

        }
    }


    fun saveItems(){
        try{
            org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}