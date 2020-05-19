package com.avinnikov.behance.data.mapper

import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.network.ProjectItem
import com.avinnikov.behance.data.room.entity.ProjectEntity

class ProjectMapper {
    fun toEntity(item: Project): ProjectEntity =
        ProjectEntity(id = item.id, name = item.name)

    fun toEntity(items: List<Project>) =
        items.map {
            toEntity(it)
        }

    fun toItem(item: Project): ProjectItem =
        ProjectItem(id = item.id, name = item.name)

    fun toItem(items: List<Project>) =
        items.map {
            toItem(it)
        }
}