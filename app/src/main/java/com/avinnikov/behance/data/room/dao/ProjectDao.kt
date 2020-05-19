package com.avinnikov.behance.data.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.data.room.entity.ProjectEntity

@Dao
interface ProjectDao {
    @Query("SELECT * from projects")
    fun getAll(): DataSource.Factory<Int, Project>

    @Query("SELECT * FROM projects WHERE id = :projectId")
    fun getProject(projectId: Int): LiveData<Project>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg projects: ProjectEntity)

    @Delete
    suspend fun delete(vararg projects: ProjectEntity)

    @Query("DELETE FROM projects")
    suspend fun deleteAll()

    @Transaction
    suspend fun firstLoad(vararg projects: ProjectEntity) {
        deleteAll()
        insert(*projects)
    }
}