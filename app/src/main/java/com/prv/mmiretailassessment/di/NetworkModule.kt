package com.prv.mmiretailassessment.di

import retrofit2.Retrofit
import org.koin.dsl.module
import okhttp3.OkHttpClient
import com.prv.mmiretailassessment.R
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import retrofit2.converter.gson.GsonConverterFactory
import com.prv.mmiretailassessment.network.MMIRetailAPIInterface


val networkModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(androidApplication().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MMIRetailAPIInterface::class.java) }
}