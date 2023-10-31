package com.example.myhome.presintation.notesfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.databinding.NotesItemBinding
import com.example.myhome.domain.models.Note

class NotesViewHolder(private val binding: NotesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.tvNoteTitle.text = note.title
        binding.tvNoteDescription.text = note.description
    }

}