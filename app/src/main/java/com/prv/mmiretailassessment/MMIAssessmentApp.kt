package com.prv.mmiretailassessment

import timber.log.Timber
import android.app.Application
import org.koin.core.context.startKoin
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import com.prv.mmiretailassessment.di.networkModule
import com.prv.mmiretailassessment.di.viewModelModule
import com.prv.mmiretailassessment.di.repositoryModule

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