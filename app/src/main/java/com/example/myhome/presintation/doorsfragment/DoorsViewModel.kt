package com.example.myhome.presintation.doorsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.usecase.GetDoorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(private val getDoors: GetDoorsUseCase) :
    BaseViewModel() {

    private val _doors = MutableLiveData<com.example.myhome.domain.models.DoorsModel>()
    val doors: LiveData<com.example.myhome.domain.models.DoorsModel> get() = _doors

    fun getDoors() = doOperation(
        operation = { getDoors.execute() },
        success = { _doors.postValue(it) }
    )
}