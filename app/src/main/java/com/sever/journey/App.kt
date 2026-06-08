package com.sever.journey

import android.app.Application
import com.google.firebase.FirebaseApp
import com.sever.journey.analytics.AnalyticsService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        AnalyticsService.init(this)
    }
}

