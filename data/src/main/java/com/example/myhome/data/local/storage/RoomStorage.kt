package com.example.myhome.data.local.storage

import com.example.myhome.data.local.storage.models.NoteDTO

interface RoomStorage {

    suspend fun getAllNotes(): List<NoteDTO>

    suspend fun insertNote(noteDTO: NoteDTO)

    suspend fun updateNote(noteDTO: NoteDTO)

    suspend fun deleteNote(noteDTO: NoteDTO)

}