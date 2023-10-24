package com.example.fleeapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FleeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FleeApplication.appContext = applicationContext
    }
    companion object {
        lateinit var appContext: Context
    }
}