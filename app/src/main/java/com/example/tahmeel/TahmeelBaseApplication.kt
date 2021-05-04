package com.example.tahmeel

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TahmeelBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}