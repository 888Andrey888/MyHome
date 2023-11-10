package com.example.myhome.presintation.notesfragment

import androidx.lifecycle.viewModelScope
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.usecase.DeleteNoteUseCase
import com.example.myhome.domain.usecase.GetAllNotesUseCase
import com.example.myhome.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotes: GetAllNotesUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val updateNote: UpdateNoteUseCase
) : BaseViewModel<List<Note>>() {

    suspend fun getAllNotes() = doOperation(
        operation = { getAllNotes.execute() }
    )

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNote.execute(note)
            getAllNotes()
        }
    }

    fun setDone(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNote.execute(note)
            getAllNotes()
        }
    }

}