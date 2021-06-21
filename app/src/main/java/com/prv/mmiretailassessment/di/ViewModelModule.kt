package com.prv.mmiretailassessment.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import com.prv.mmiretailassessment.viewModels.LoginViewModel
import com.prv.mmiretailassessment.viewModels.AccountsListViewModel
import com.prv.mmiretailassessment.viewModels.AccountDetailsViewModel
import com.prv.mmiretailassessment.viewModels.DepositWithdrawViewModel


val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { AccountsListViewModel(get()) }
    viewModel { AccountDetailsViewModel(get()) }
    viewModel { DepositWithdrawViewModel(get()) }
}