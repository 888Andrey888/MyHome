package com.example.myhome.presintation.camsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.data.models.CamerasModel
import com.example.myhome.domain.repository.Repository

class CamerasViewModel(private val repository: Repository) : BaseViewModel() {

    private val _cameras = MutableLiveData<CamerasModel>()
    val cameras: LiveData<CamerasModel> get() = _cameras

    fun getCameras() = doOperation(
        operation = { repository.getCameras() },
        success = { _cameras.postValue(it) }
    )
}