package com.example.notesnerd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesnerd.R
import com.example.notesnerd.model.Notes

interface OnItemClickListener {
    fun onItemClick(position: Notes)
}

class NotesAdapter(val context: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    private var mListener: OnItemClickListener? = null

    class notesViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(notes: Notes) {
            with(itemView) {
                findViewById<TextView>(R.id.tvNotesTitle).text = notes.title
                findViewById<TextView>(R.id.tvNotesSubTitle).text = notes.subtitle
                findViewById<TextView>(R.id.tvNotesDate).text = notes.date
                when (notes.priority) {
                    "1" -> {
                        findViewById<View>(R.id.viewPriority).setBackgroundResource(R.drawable.red_dot)
                    }
                    "2" -> {
                        findViewById<View>(R.id.viewPriority).setBackgroundResource(R.drawable.yellow_dot)
                    }
                    "3" -> {
                        findViewById<View>(R.id.viewPriority).setBackgroundResource(R.drawable.green_dot)
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        holder.bind(notesList[position])

        holder.itemView.setOnClickListener {
            mListener?.onItemClick(notesList[position])
        }
    }

    override fun getItemCount() = notesList.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun filtering(newNotes: ArrayList<Notes>) {
        notesList = newNotes
        notifyDataSetChanged()
    }


}