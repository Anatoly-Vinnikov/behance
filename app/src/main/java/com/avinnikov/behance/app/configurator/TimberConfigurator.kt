package com.avinnikov.behance.app.configurator

import android.app.Application
import com.avinnikov.behance.BuildConfig
import timber.log.Timber

class TimberConfigurator : Configurator {
    override fun configure(app: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}