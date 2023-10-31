package com.example.myhome.domain.usecase

import com.example.myhome.domain.models.Note
import com.example.myhome.domain.repository.RetrofitRepository

class GetAllNotesUseCase(private val retrofitRepository: RetrofitRepository) {

    suspend fun execute(): Result<List<Note>>{
        return retrofitRepository.getAllNotes()
    }

}