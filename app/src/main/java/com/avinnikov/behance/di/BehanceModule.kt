package com.avinnikov.behance.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.avinnikov.behance.app.configurator.Configurator
import com.avinnikov.behance.app.configurator.TimberConfigurator
import com.avinnikov.behance.data.error.ProjectErrorHandler
import com.avinnikov.behance.data.error.UserErrorHandler
import com.avinnikov.behance.data.local.sharedpreferences.AppSharedPreferences
import com.avinnikov.behance.data.mapper.ProjectEntityMapper
import com.avinnikov.behance.data.mapper.ProjectItemMapper
import com.avinnikov.behance.data.mapper.ProjectMapper
import com.avinnikov.behance.data.repository.ProjectRepository
import com.avinnikov.behance.navigator.MainNavigator
import com.avinnikov.behance.ui.main.viewmodel.MainViewModel
import com.avinnikov.behance.ui.project.viewmodel.ProjectViewModel
import com.avinnikov.behance.util.ConnectionLiveData
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

val appModule = module {
    single { (activity: AppCompatActivity) -> MainNavigator(activity) }
    single(qualifier = TypeQualifier(Configurator::class)) {
        listOf(TimberConfigurator())
    }
    single { ConnectionLiveData(androidContext()) }
    single { androidContext().getSharedPreferences("behance", Context.MODE_PRIVATE) }
    single { AppSharedPreferences(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { (projectId: Int) -> ProjectViewModel(get(), get(), projectId) }
    // TODO: injected by androidx. Need a factory if have constructor parameters
    //viewModel { NavigatorViewModel() }
}

val repositoryModule = module {
    single { ProjectRepository(get(), get(), get()) }
}

val mapperModule = module {
    single { ProjectMapper() }
    single { ProjectItemMapper() }
    single { ProjectEntityMapper() }
}

val errorHandlerModule = module {
    single { ProjectErrorHandler(androidContext()) }
    single { UserErrorHandler(androidContext()) }
}

val behanceModule =
    listOf(
        appModule,
        viewModelModule,
        repositoryModule,
        mapperModule,
        retrofitModule,
        databaseModule,
        errorHandlerModule
    )