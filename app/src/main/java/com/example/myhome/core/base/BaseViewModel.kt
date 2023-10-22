package com.example.myhome.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    protected fun <T> doOperation(
        operation: suspend () -> Result<T>,
        success: (T) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = operation()
            when {
                result.isSuccess -> result.onSuccess(success)
                result.isFailure -> result.onFailure {
                    if (!it.message.isNullOrEmpty())
                        _error.postValue(it.message)
                }
            }
        }
    }

}