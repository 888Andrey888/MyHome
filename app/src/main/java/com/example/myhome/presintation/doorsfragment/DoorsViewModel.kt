package com.example.myhome.presintation.doorsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.data.storage.models.DoorsModelDTO
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.usecase.GetDoorsUseCase

class DoorsViewModel(private val getDoors: GetDoorsUseCase) : BaseViewModel() {

    private val _doors = MutableLiveData<DoorsModel>()
    val doors: LiveData<DoorsModel> get() = _doors

    fun getDoors() = doOperation(
        operation = { getDoors.execute() },
        success = { _doors.postValue(it) }
    )
}