package com.example.myhome.di

import android.content.Context
import androidx.room.Room
import com.example.myhome.data.local.dao.NoteDao
import com.example.myhome.data.local.db.NoteRoomDataBase
import com.example.myhome.data.local.storage.RoomStorage
import com.example.myhome.data.local.storage.RoomStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = NoteRoomDataBase::class.java,
        name = "note"
    ).build()

    @Provides
    fun provideNoteDao(
        dataBase: NoteRoomDataBase
    ) = dataBase.getDao()

    @Provides
    fun provideRoomStorage(noteDao: NoteDao): RoomStorage {
        return RoomStorageImpl(noteDao)
    }

}