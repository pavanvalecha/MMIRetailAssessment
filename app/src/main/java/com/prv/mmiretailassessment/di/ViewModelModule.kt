package com.prv.mmiretailassessment.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import com.prv.mmiretailassessment.viewmodels.LoginViewModel
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import com.prv.mmiretailassessment.viewmodels.AccountDetailsViewModel
import com.prv.mmiretailassessment.viewmodels.DepositWithdrawViewModel


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { AccountsListViewModel(get()) }
    viewModel { AccountDetailsViewModel(get()) }
    viewModel { DepositWithdrawViewModel(get()) }
}