package com.example.myhome.domain.repository

import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.DoorsModel

interface RetrofitRepository {
    suspend fun getCameras() : Result<CamerasModel>

    suspend fun getDoors() : Result<DoorsModel>
}