package com.prv.mmiretailassessment

import android.app.Application
import com.prv.mmiretailassessment.di.networkModule
import com.prv.mmiretailassessment.di.repositoryModule
import com.prv.mmiretailassessment.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MMIAssessmentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@MMIAssessmentApp)
            modules(networkModule, viewModelModule, repositoryModule)
        }
    }
}