package com.example.myhome.domain.models

data class CamerasModel(
    var data: Data,
    var success: Boolean
) {
    data class Data(
        var cameras: List<Camera>,
        val room: List<String>
    ) {
        data class Camera(
            val favorites: Boolean,
            val id: Int,
            val name: String,
            val rec: Boolean,
            val room: String,
            val snapshot: String
        )
    }
}