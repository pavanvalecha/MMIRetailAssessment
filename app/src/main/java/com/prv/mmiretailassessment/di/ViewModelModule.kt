package com.prv.mmiretailassessment.di



import com.prv.mmiretailassessment.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{ LoginViewModel( get() ) }
}