package com.example.myhome.domain.repository

import com.example.myhome.core.network.RemoteDataSource
import com.example.myhome.data.models.CamerasModel
import com.example.myhome.data.models.DoorsModel

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getCameras(): Result<CamerasModel>{
        return remoteDataSource.getCameras()
    }

    suspend fun getDoors(): Result<DoorsModel>{
        return remoteDataSource.getDoors()
    }

}