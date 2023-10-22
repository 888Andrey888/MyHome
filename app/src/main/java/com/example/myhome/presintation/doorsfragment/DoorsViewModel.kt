package com.example.myhome.presintation.doorsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.data.models.DoorsModel
import com.example.myhome.domain.repository.Repository

class DoorsViewModel(private val repository: Repository) : BaseViewModel() {

    private val _doors = MutableLiveData<DoorsModel>()
    val doors: LiveData<DoorsModel> get() = _doors

    fun getDoors() = doOperation(
        operation = { repository.getDoors() },
        success = { _doors.postValue(it) }
    )
}