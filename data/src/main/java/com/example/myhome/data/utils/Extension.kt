package com.example.myhome.data.utils

import com.example.myhome.data.local.storage.models.NoteDTO
import com.example.myhome.data.remote.storage.models.CamerasModelDTO
import com.example.myhome.data.remote.storage.models.DoorsModelDTO
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.models.Note

fun CamerasModelDTO.mapToCamerasModel(): CamerasModel {
    val camerasModelDataCameras = mutableListOf<CamerasModel.Data.Camera>()
    this.data.cameras.mapTo(camerasModelDataCameras) {
        CamerasModel.Data.Camera(it.favorites, it.id, it.name, it.rec, it.room, it.snapshot)
    }
    val camerasModelData =
        CamerasModel.Data(camerasModelDataCameras.toList(), this.data.room)
    return CamerasModel(camerasModelData, this.success)
}

fun DoorsModelDTO.mapToDoorsModel(): DoorsModel {
    val doorsModelData = mutableListOf<DoorsModel.Data>()
    this.data.mapTo(doorsModelData) {
        DoorsModel.Data(it.favorites, it.id, it.name, it.room, it.snapshot)
    }
    return DoorsModel(doorsModelData, this.success)
}

fun List<NoteDTO>.mapToNote(): List<Note> {
    return this.map {
        Note(
            id = it.id,
            title = it.title,
            description = it.description,
            isDone = it.isDone
        )
    }
}

fun Note.mapToNoteDTO(): NoteDTO {
    return NoteDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        isDone = this.isDone
    )
}