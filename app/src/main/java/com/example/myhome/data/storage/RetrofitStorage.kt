package com.example.myhome.data.storage

import com.example.myhome.data.storage.models.CamerasModelDTO
import com.example.myhome.data.storage.models.DoorsModelDTO

interface RetrofitStorage {

    suspend fun getCameras(): CamerasModelDTO

    suspend fun getDoors(): DoorsModelDTO

}