package com.example.myhome.domain.usecase

import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.repository.RetrofitRepository

class GetCamerasUseCase(private val retrofitRepository: RetrofitRepository) {

    suspend fun execute(): Result<CamerasModel> {
        return retrofitRepository.getCameras()
    }

}