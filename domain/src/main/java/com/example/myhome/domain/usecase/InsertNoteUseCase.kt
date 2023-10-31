package com.example.myhome.domain.usecase

import com.example.myhome.domain.models.Note
import com.example.myhome.domain.repository.RetrofitRepository

class InsertNoteUseCase(private val retrofitRepository: RetrofitRepository) {

    suspend fun execute(note: Note){
        retrofitRepository.insertNote(note)
    }

}