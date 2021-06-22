package com.mmi.retailassessment.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import com.mmi.retailassessment.viewModels.LoginViewModel
import com.mmi.retailassessment.viewModels.AccountsListViewModel
import com.mmi.retailassessment.viewModels.AccountDetailsViewModel
import com.mmi.retailassessment.viewModels.DepositWithdrawViewModel


val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { AccountsListViewModel(get()) }
    viewModel { AccountDetailsViewModel(get()) }
    viewModel { DepositWithdrawViewModel(get()) }
}