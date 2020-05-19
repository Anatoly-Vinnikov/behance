package com.avinnikov.behance.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avinnikov.behance.data.room.converter.ProjectTypeConverter
import com.avinnikov.behance.data.room.dao.ProjectDao
import com.avinnikov.behance.data.room.entity.ProjectEntity

@Database(entities = [ProjectEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [ProjectTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract val projectDao: ProjectDao
}