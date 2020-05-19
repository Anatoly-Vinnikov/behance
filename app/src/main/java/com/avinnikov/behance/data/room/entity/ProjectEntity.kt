package com.avinnikov.behance.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val key: Int = 0,
    val id: Int,
    val name: String
)