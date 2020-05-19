package com.avinnikov.behance.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.avinnikov.behance.common.viewmodel.BaseViewModel
import com.avinnikov.behance.common.viewmodel.Event
import com.avinnikov.behance.data.error.ProjectErrorHandler
import com.avinnikov.behance.data.repository.ProjectRepository
import com.avinnikov.behance.util.ConnectionLiveData

class MainViewModel(
    private val repository: ProjectRepository,
    private val errorHandler: ProjectErrorHandler,
    connectionLiveData: ConnectionLiveData
) : BaseViewModel() {

    val isConnected: LiveData<Event<Boolean>> =
        Transformations.map(connectionLiveData) {
            Event(it)
        }

    val projects = repository.data

    val loadState = repository.loadState

    init {
        fetchProjects()
    }

    private fun fetchProjects() {
        launchInCoroutineScope(
            onRun = { repository.firstLoad() },
            onException = {
                postError(errorHandler.getError(it).message.toString())
            }
        )
    }

    fun retry(page: Int) {
        launchInCoroutineScope(
            onRun = { repository.fetch(page) },
            onException = {
                postError(errorHandler.getError(it).message.toString())
            }
        )
    }
}