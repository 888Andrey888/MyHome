package com.example.myhome.data.storage

import com.example.myhome.data.network.ApiService
import com.example.myhome.data.storage.models.CamerasModelDTO
import com.example.myhome.data.storage.models.DoorsModelDTO

class RetrofitStorageImpl(private val apiService: ApiService) : RetrofitStorage {
    override suspend fun getCameras(): CamerasModelDTO {
        val body = apiService.getCameras().body()
        return CamerasModelDTO(body!!.data, body!!.success)
    }

    override suspend fun getDoors(): DoorsModelDTO {
        val body = apiService.getDoors().body()
        return DoorsModelDTO(body!!.data, body!!.success)
    }
}