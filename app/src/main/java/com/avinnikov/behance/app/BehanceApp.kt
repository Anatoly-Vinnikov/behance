package com.avinnikov.behance.app

import android.app.Application
import com.avinnikov.behance.app.configurator.Configurator
import com.avinnikov.behance.di.behanceModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.TypeQualifier

class BehanceApp : Application() {

    private val configurators: List<Configurator> by inject(TypeQualifier(Configurator::class))

    override fun onCreate() {
        super.onCreate()

        initializeInjector()

        configure()
    }

    private fun initializeInjector() {
        startKoin {
            androidContext(this@BehanceApp)
            modules(behanceModule)
        }
    }

    private fun configure() {
        configurators.forEach { it.configure(this@BehanceApp) }
    }
}