package com.example.myhome.domain.usecase

import com.example.myhome.domain.repository.RetrofitRepository

class GetAllNotesUseCase(private val retrofitRepository: RetrofitRepository) {

    suspend fun execute() = retrofitRepository.getAllNotes()

}