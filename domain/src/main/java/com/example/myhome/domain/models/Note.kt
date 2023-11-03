package com.example.myhome.domain.models

import java.io.Serializable


data class Note(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    var isDone: Boolean? = false
) : Serializable