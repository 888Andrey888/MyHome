package com.example.myhome.data.remote.network

import com.example.myhome.data.remote.storage.models.CamerasModelDTO
import com.example.myhome.data.remote.storage.models.DoorsModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cameras")
    suspend fun getCameras(): Response<CamerasModelDTO>

    @GET("doors")
    suspend fun getDoors(): Response<DoorsModelDTO>

}