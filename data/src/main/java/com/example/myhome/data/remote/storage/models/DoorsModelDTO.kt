package com.example.myhome.data.remote.storage.models

data class DoorsModelDTO(
    val data: List<Data>,
    val success: Boolean
) {
    data class Data(
        val favorites: Boolean,
        val id: Int,
        val name: String,
        val room: String,
        val snapshot: String? = null
    )
}