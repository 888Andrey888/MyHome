package com.example.myhome.domain.repository

import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.models.Note

interface RetrofitRepository {

    suspend fun getCameras() : Result<CamerasModel>

    suspend fun getDoors() : Result<DoorsModel>

    suspend fun getAllNotes(): Result<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

}