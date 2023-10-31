package com.example.myhome.presintation.camsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.domain.usecase.GetCamerasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val getCameras: GetCamerasUseCase) :
    BaseViewModel() {

    private val _cameras = MutableLiveData<CamerasModel>()
    val cameras: LiveData<CamerasModel> get() = _cameras

    fun getCameras() = doOperation(
        operation = { getCameras.execute() },
        success = { _cameras.postValue(it) }
    )
}