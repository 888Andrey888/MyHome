package com.example.myhome.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myhome.data.local.storage.models.NoteDTO

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteDTO>

    @Query("SELECT * FROM notes WHERE title == :title ")
    suspend fun getAllNotesByTitle(title: String): List<NoteDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDTO: NoteDTO)

    @Insert
    suspend fun insertAll(noteDTOS: List<NoteDTO>)

    @Update
    suspend fun updateNote(noteDTO: NoteDTO)

    @Delete
    suspend fun deleteNote(noteDTO: NoteDTO)

}