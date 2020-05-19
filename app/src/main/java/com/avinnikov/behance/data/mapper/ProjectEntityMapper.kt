package com.avinnikov.behance.data.mapper

import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.network.ProjectItem
import com.avinnikov.behance.data.room.entity.ProjectEntity

class ProjectEntityMapper {
    fun toProject(item: ProjectEntity): Project =
        Project(id = item.id, name = item.name)

    fun toProject(items: List<ProjectEntity>) =
        items.map {
            toProject(it)
        }

    fun toItem(item: ProjectEntity): ProjectItem =
        ProjectItem(id = item.id, name = item.name)

    fun toItem(items: List<ProjectEntity>) =
        items.map {
            toItem(it)
        }
}