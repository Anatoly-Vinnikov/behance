package com.avinnikov.behance.ui.project.viewmodel

import com.avinnikov.behance.common.viewmodel.BaseViewModel
import com.avinnikov.behance.data.error.ErrorData
import com.avinnikov.behance.data.error.ProjectErrorHandler
import com.avinnikov.behance.data.repository.ProjectRepository
import com.avinnikov.behance.extensions.logd

class ProjectViewModel(
    private val repository: ProjectRepository,
    private val errorHandler: ProjectErrorHandler,
    private val projectId: Int
) : BaseViewModel() {

    val project = repository.getProject(projectId)

    init {
        fetchProject()
    }

    private fun fetchProject() {
        launchInCoroutineScope(
            onRun = { repository.fetchProject(projectId) },
            onException = {
                when (val error = errorHandler.getError(it)) {
                    is ErrorData.ProjectNotFound -> {
                        logd("Project not found")
                        postError(error.message)
                    }
                    is ErrorData.NoConnection -> {
                        logd("No Connection exception")
                        postError(error.message)
                    }
                }
            }
        )
    }
}