package com.avinnikov.behance.data.room.converter

import androidx.room.TypeConverter
import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.room.entity.ProjectEntity

class ProjectTypeConverter {
    @TypeConverter
    fun fromEntity(project: ProjectEntity): Project =
        Project(id = project.id, name = project.name)
}