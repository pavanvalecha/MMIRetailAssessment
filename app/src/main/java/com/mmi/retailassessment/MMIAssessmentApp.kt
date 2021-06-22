package com.mmi.retailassessment

import timber.log.Timber
import android.app.Application
import org.koin.core.context.startKoin
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import com.mmi.retailassessment.di.networkModule
import com.mmi.retailassessment.di.viewModelModule
import com.mmi.retailassessment.di.repositoryModule

class MMIAssessmentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        FirebaseApp.initializeApp(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MMIAssessmentApp)
            modules(networkModule, viewModelModule, repositoryModule)
        }
    }
}