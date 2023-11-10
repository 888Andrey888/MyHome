package com.example.myhome.domain.repository

import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RetrofitRepository {

    suspend fun getCameras(): Flow<Resource<CamerasModel>>

    suspend fun getDoors(): Flow<Resource<DoorsModel>>

    suspend fun getAllNotes(): Flow<Resource<List<Note>>>

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}