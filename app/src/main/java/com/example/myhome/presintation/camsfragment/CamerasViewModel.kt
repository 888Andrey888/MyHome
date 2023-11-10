package com.example.myhome.presintation.camsfragment

import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.usecase.GetCamerasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val getCameras: GetCamerasUseCase) :
    BaseViewModel<CamerasModel>() {

    suspend fun getCameras() = doOperation(
        operation = { getCameras.execute() }
    )

}