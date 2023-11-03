package com.example.myhome.presintation.notesfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.databinding.NotesItemBinding
import com.example.myhome.domain.models.Note

class NotesAdapter(private val setDone: (note: Note) -> Unit) :
    RecyclerView.Adapter<NotesViewHolder>() {

    private var list = mutableListOf<Note>()

    fun addData(notes: List<Note>) {
        list.clear()
        list.addAll(notes)
        notifyItemRangeInserted(list.size, notes.size - list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NotesViewHolder(
        NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position], setDone)
    }
}