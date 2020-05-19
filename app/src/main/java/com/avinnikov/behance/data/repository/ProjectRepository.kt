package com.avinnikov.behance.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.avinnikov.behance.common.paging.LoadState
import com.avinnikov.behance.common.paging.boundaryCallback
import com.avinnikov.behance.common.viewmodel.Event
import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.mapper.ProjectItemMapper
import com.avinnikov.behance.data.room.dao.ProjectDao
import com.avinnikov.behance.restapi.BehanceService

class ProjectRepository(
    private val service: BehanceService,
    private val dao: ProjectDao,
    private val projectItemMapper: ProjectItemMapper
) {
    private val _loadState = MutableLiveData<Event<LoadState>>()
    val loadState: LiveData<Event<LoadState>>
        get() = _loadState

    val data: LiveData<Event<PagedList<Project>>> = Transformations.map(
        dao.getAll()
            .toLiveData(
                pageSize = 20,
                boundaryCallback = boundaryCallback { fetch(it) })
    ) {
        Event(it)
    }

    fun getProject(projectId: Int): LiveData<Event<Project>> =
        Transformations.map(dao.getProject(projectId)) {
            Event(it)
        }

    suspend fun firstLoad() {
        val projects = service.getProjects(1).projects
        val mapped = projectItemMapper.toEntity(projects)

        dao.firstLoad(*mapped.toTypedArray())
    }

    suspend fun fetch(page: Int) {
        try {
            _loadState.postValue(Event(LoadState.Loading))

            val projects = service.getProjects(page).projects
            val mapped = projectItemMapper.toEntity(projects)

            dao.insert(*mapped.toTypedArray())

            _loadState.postValue(Event(LoadState.Done))
        } catch (e: Throwable) {
            _loadState.postValue(Event(LoadState.Error(e, page)))
        }
    }

    suspend fun fetchProject(projectId: Int) {
        val project = service.getProject(projectId).project
        val mapped = projectItemMapper.toEntity(project)

        dao.insert(mapped)
    }
}