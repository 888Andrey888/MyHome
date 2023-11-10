package com.example.myhome.presintation.doorsfragment

import com.example.myhome.core.base.BaseViewModel
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.usecase.GetDoorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(private val getDoors: GetDoorsUseCase) :
    BaseViewModel<DoorsModel>() {

    suspend fun getDoors() = doOperation(
        operation = { getDoors.execute() }
    )

}