package com.avinnikov.behance.di

import android.app.Application
import androidx.room.Room
import com.avinnikov.behance.data.room.dao.ProjectDao
import com.avinnikov.behance.data.room.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "project_database"

val databaseModule = module {
    fun createAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    fun provideProjectDao(database: AppDatabase): ProjectDao = database.projectDao

    single { createAppDatabase(androidApplication()) }
    single { provideProjectDao(get()) }
}