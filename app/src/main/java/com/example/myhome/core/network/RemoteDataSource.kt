package com.example.myhome.core.network

import com.example.myhome.core.base.BaseDataSource
import com.example.myhome.data.models.CamerasModel
import com.example.myhome.data.models.DoorsModel

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getCameras(): Result<CamerasModel> {
        return getResult { apiService.getCameras() }
    }

    suspend fun getDoors(): Result<DoorsModel> {
        return getResult { apiService.getDoors() }
    }
}