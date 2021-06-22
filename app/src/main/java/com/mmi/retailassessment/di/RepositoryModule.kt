package com.mmi.retailassessment.di

import org.koin.dsl.module
import com.mmi.retailassessment.repository.AccountsListRepository
import com.mmi.retailassessment.repository.DepositWithdrawRepository
import com.mmi.retailassessment.repository.AccountsDetailsRepository


val repositoryModule = module {
    factory { AccountsListRepository(get()) }
    factory { AccountsDetailsRepository(get()) }
    factory { DepositWithdrawRepository(get()) }
}