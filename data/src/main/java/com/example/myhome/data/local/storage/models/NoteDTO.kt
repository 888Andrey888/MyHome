package com.example.myhome.data.local.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDTO (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val isDone: Boolean? = false
)