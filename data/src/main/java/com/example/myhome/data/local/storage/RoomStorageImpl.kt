package com.example.myhome.data.local.storage

import com.example.myhome.data.local.dao.NoteDao
import com.example.myhome.data.local.storage.models.NoteDTO

class RoomStorageImpl(private val dao: NoteDao) : RoomStorage {
    override suspend fun getAllNotes(): List<NoteDTO> {
        return dao.getAllNotes()
    }

    override suspend fun insertNote(noteDTO: NoteDTO) {
        dao.insert(noteDTO)
    }

    override suspend fun updateNote(noteDTO: NoteDTO) {
        dao.updateNote(noteDTO)
    }

    override suspend fun deleteNote(noteDTO: NoteDTO) {
        dao.deleteNote(noteDTO)
    }

}