package com.example.myhome.data.repository

import com.example.myhome.data.local.storage.RoomStorage
import com.example.myhome.data.remote.storage.RetrofitStorage
import com.example.myhome.data.utils.mapToCamerasModel
import com.example.myhome.data.utils.mapToDoorsModel
import com.example.myhome.data.utils.mapToNote
import com.example.myhome.data.utils.mapToNoteDTO
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.repository.RetrofitRepository
import com.example.myhome.domain.utils.Resource
import kotlinx.coroutines.flow.flow

open class RetrofitRepositoryImpl(
    private val retrofitStorage: RetrofitStorage,
    private val roomStorage: RoomStorage
) : RetrofitRepository {

    override suspend fun getCameras() = flow {
        emit(Resource.Loading())
        try {
            val data = retrofitStorage.getCameras().mapToCamerasModel()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }

    override suspend fun getDoors() = flow {
        emit(Resource.Loading())
        try {
            val data = retrofitStorage.getDoors().mapToDoorsModel()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }

    override suspend fun getAllNotes() = flow {
        emit(Resource.Loading())
        try {
            val data = roomStorage.getAllNotes().mapToNote()
            emit(Resource.Success(data = data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }

    override suspend fun insertNote(note: Note) {
        roomStorage.insertNote(note.mapToNoteDTO())
    }

    override suspend fun updateNote(note: Note) {
        roomStorage.updateNote(note.mapToNoteDTO())
    }

    override suspend fun deleteNote(note: Note) {
        roomStorage.deleteNote(note.mapToNoteDTO())
    }

}