package com.example.simpletodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskitemAdapter(val listofitems: List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskitemAdapter.ViewHolder>() {
    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)
            itemView.setOnClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listofitems.get(position)
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listofitems.size
    }
}