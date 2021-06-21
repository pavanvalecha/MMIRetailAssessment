package com.prv.mmiretailassessment.di

import org.koin.dsl.module
import com.prv.mmiretailassessment.repository.AccountsListRepository
import com.prv.mmiretailassessment.repository.DepositWithdrawRepository
import com.prv.mmiretailassessment.repository.AccountsDetailsRepository


val repositoryModule = module {
    factory { AccountsListRepository(get()) }
    factory { AccountsDetailsRepository(get()) }
    factory { DepositWithdrawRepository(get()) }
}