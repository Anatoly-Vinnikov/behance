package com.avinnikov.behance.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avinnikov.behance.R
import com.avinnikov.behance.navigator.MainNavigator
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val navigator: MainNavigator = get(parameters = {
        parametersOf(this@MainActivity)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}