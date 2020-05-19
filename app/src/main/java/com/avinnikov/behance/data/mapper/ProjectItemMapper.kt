package com.avinnikov.behance.data.mapper

import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.network.ProjectItem
import com.avinnikov.behance.data.room.entity.ProjectEntity

class ProjectItemMapper {
    fun toEntity(item: ProjectItem): ProjectEntity =
        ProjectEntity(id = item.id, name = item.name)

    fun toEntity(items: List<ProjectItem>) =
        items.map {
            toEntity(it)
        }

    fun toProject(item: ProjectItem): Project =
        Project(id = item.id, name = item.name)

    fun toProject(items: List<ProjectItem>) =
        items.map {
            toProject(it)
        }
}