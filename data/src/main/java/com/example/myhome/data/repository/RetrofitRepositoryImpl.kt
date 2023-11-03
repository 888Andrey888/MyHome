package com.example.myhome.data.repository

import com.example.myhome.data.local.storage.RoomStorage
import com.example.myhome.data.local.storage.models.NoteDTO
import com.example.myhome.data.remote.storage.RetrofitStorage
import com.example.myhome.data.remote.storage.models.CamerasModelDTO
import com.example.myhome.data.remote.storage.models.DoorsModelDTO
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.CamerasModel.Data.Camera
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.models.Note
import com.example.myhome.domain.repository.RetrofitRepository

open class RetrofitRepositoryImpl(
    private val retrofitStorage: RetrofitStorage,
    private val roomStorage: RoomStorage
) :
    RetrofitRepository {

    override suspend fun getCameras(): Result<CamerasModel> {
        return mapToCamerasModel(retrofitStorage.getCameras())
    }

    override suspend fun getDoors(): Result<DoorsModel> {
        return mapToDoorsModel(retrofitStorage.getDoors())
    }

    override suspend fun getAllNotes(): Result<List<Note>> {
        return mapToNotes(roomStorage.getAllNotes())
    }

    override suspend fun insertNote(note: Note) {
        roomStorage.insertNote(mapToNoteDTO(note))
    }

    override suspend fun updateNote(note: Note) {
        roomStorage.updateNote(mapToNoteDTO(note))
    }

    override suspend fun deleteNote(note: Note) {
        roomStorage.deleteNote(mapToNoteDTO(note))
    }

    private fun mapToNoteDTO(note: Note): NoteDTO {
        return NoteDTO(
            id = note.id,
            title = note.title,
            description = note.description,
            isDone = note.isDone
        )
    }

    private fun mapToNotes(allNotes: List<NoteDTO>): Result<List<Note>> {
        val notes = mutableListOf<Note>()
        allNotes.mapTo(notes) {
            Note(
                id = it.id,
                title = it.title,
                description = it.description,
                isDone = it.isDone
            )
        }
        return Result.success(notes)
    }

    private fun mapToCamerasModel(camerasModel: CamerasModelDTO): Result<CamerasModel> {
        val camerasModelDataCameras = mutableListOf<Camera>()
        camerasModel.data.cameras.mapTo(camerasModelDataCameras) {
            Camera(it.favorites, it.id, it.name, it.rec, it.room ?: "", it.snapshot)
        }
        val camerasModelData =
            CamerasModel.Data(camerasModelDataCameras.toList(), camerasModel.data.room)
        return Result.success(CamerasModel(camerasModelData, camerasModel.success))
    }

    private fun mapToDoorsModel(doorsModel: DoorsModelDTO): Result<DoorsModel> {
        val doorsModelData = mutableListOf<DoorsModel.Data>()
        doorsModel.data.mapTo(doorsModelData) {
            DoorsModel.Data(it.favorites, it.id, it.name, it.room ?: "", it.snapshot)
        }
        return Result.success(DoorsModel(doorsModelData, doorsModel.success))
    }

}