package com.example.myhome.core.network

import com.example.myhome.data.models.CamerasModel
import com.example.myhome.data.models.DoorsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras() : Response<CamerasModel>

    @GET("doors")
    suspend fun getDoors() : Response<DoorsModel>
}