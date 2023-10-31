package com.example.myhome.data.remote.storage

import com.example.myhome.data.remote.storage.models.CamerasModelDTO
import com.example.myhome.data.remote.storage.models.DoorsModelDTO

interface RetrofitStorage {

    suspend fun getCameras(): CamerasModelDTO

    suspend fun getDoors(): DoorsModelDTO

}