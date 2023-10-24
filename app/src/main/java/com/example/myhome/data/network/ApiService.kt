package com.example.myhome.data.network

import com.example.myhome.data.storage.models.CamerasModelDTO
import com.example.myhome.data.storage.models.DoorsModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras() : Response<CamerasModelDTO>

    @GET("doors")
    suspend fun getDoors() : Response<DoorsModelDTO>
}