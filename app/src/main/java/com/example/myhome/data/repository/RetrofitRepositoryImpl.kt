package com.example.myhome.data.repository

import com.example.myhome.data.storage.RetrofitStorage
import com.example.myhome.data.storage.models.CamerasModelDTO
import com.example.myhome.data.storage.models.DoorsModelDTO
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.models.CamerasModel.Data.Camera
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.repository.RetrofitRepository

open class RetrofitRepositoryImpl(private val retrofitStorage: RetrofitStorage) :
    RetrofitRepository {

    override suspend fun getCameras(): Result<CamerasModel> {
        return mapToCamerasModel(retrofitStorage.getCameras())
    }

    override suspend fun getDoors(): Result<DoorsModel> {
        return mapToDoorsModel(retrofitStorage.getDoors())
    }

    private fun mapToCamerasModel(camerasModel: CamerasModelDTO): Result<CamerasModel> {
        val camerasModelDataCameras = mutableListOf<Camera>()
        camerasModel.data.cameras.mapTo(camerasModelDataCameras) {
            Camera(it.favorites, it.id, it.name, it.rec, it.room ?: "", it.snapshot)
        }
        val camerasModelData =
            CamerasModel.Data(camerasModelDataCameras.toList(), camerasModel.data.room)
        return Result.success(CamerasModel(camerasModelData, camerasModel.success))
    }

    private fun mapToDoorsModel(doorsModel: DoorsModelDTO): Result<DoorsModel> {
        val doorsModelData = mutableListOf<DoorsModel.Data>()
        doorsModel.data.mapTo(doorsModelData) {
            DoorsModel.Data(it.favorites, it.id, it.name, it.room ?: "", it.snapshot)
        }
        return Result.success(DoorsModel(doorsModelData, doorsModel.success))
    }

}