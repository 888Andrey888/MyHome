package com.example.myhome.presintation.setfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.usecase.InsertNoteUseCase
import com.example.myhome.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.execute(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.execute(note)
        }
    }

}