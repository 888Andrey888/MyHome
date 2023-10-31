package com.example.myhome.presintation.notesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.usecase.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val getAllNotes: GetAllNotesUseCase) :
    BaseViewModel() {

    private var _nodes = MutableLiveData<List<Note>>()
    val nodes: LiveData<List<Note>> get() = _nodes

    fun getAllNotes() = doOperation(
        operation = { getAllNotes.execute() },
        success = { _nodes.postValue(it) }
    )

}