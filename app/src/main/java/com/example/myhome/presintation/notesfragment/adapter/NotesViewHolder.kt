package com.example.myhome.presintation.notesfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.R
import com.example.myhome.databinding.NotesItemBinding
import com.example.myhome.domain.models.Note

class NotesViewHolder(private val binding: NotesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note, setDone: (note: Note) -> Unit) {
        binding.tvNoteTitle.text = note.title
        binding.tvNoteDescription.text = note.description
        if (note.isDone!!)
            binding.imgState.setImageResource(R.drawable.ic_checked)
        else
            binding.imgState.setImageResource(R.drawable.ic_unchecked)
        binding.imgState.setOnClickListener {
            if (!note.isDone!!) {
                note.isDone = true
                setDone(note)
            }
        }
    }

}