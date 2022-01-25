package com.reemsd.day.prefrence

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ThemeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val theme = ThemeProvider(this).getThemeFromPreferences()
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}