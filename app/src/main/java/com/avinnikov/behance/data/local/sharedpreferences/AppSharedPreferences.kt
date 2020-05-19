package com.avinnikov.behance.data.local.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit

class AppSharedPreferences(private val preferences: SharedPreferences) {
    fun save(key: String, value: String) = preferences.edit { putString(key, value) }

    fun get(key: String, defValue: String) = preferences.getString(key, defValue).toString()

    companion object {
        const val EXAMPLE_KEY = "example_key"
    }
}

fun AppSharedPreferences.saveExample(value: String) {
    save(AppSharedPreferences.EXAMPLE_KEY, value)
}

fun AppSharedPreferences.getExample(defValue: String) =
    get(AppSharedPreferences.EXAMPLE_KEY, defValue)