package com.avinnikov.behance.app.configurator

import android.app.Application

interface Configurator {
    fun configure(app: Application)
}