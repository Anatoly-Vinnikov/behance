package com.avinnikov.behance.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avinnikov.behance.common.navigator.NavigationCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>>()
    val navigationCommand: LiveData<Event<NavigationCommand>>
        get() = _navigationCommand

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    fun showLoadingIndicator() {
        _isLoading.value = true
    }

    fun hideLoadingIndicator() {
        _isLoading.value = false
    }

    fun navigate(command: NavigationCommand) {
        _navigationCommand.value = Event(command)
    }

    fun postError(message: String) {
        _errorMessage.value = Event(message)
    }

    fun <T> launchInCoroutineScope(
        onRun: suspend () -> T,
        onResult: (T) -> Unit = {},
        onException: (Throwable) -> Unit = {},
        showLoading: Boolean = true
    ) = viewModelScope.launch {
        if (showLoading) showLoadingIndicator()
        try {
            val result: T = withContext(Dispatchers.IO) { onRun.invoke() }
            onResult.invoke(result)
        } catch (e: Throwable) {
            onException.invoke(e)
        } finally {
            if (showLoading) hideLoadingIndicator()
        }
    }
}