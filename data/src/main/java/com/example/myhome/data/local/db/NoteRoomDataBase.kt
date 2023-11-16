package com.example.myhome.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myhome.data.local.dao.NoteDao
import com.example.myhome.data.local.storage.models.NoteDTO

@Database(entities = [NoteDTO::class], [], 1)
abstract class NoteRoomDataBase: RoomDatabase() {

    abstract fun getDao() : NoteDao

}