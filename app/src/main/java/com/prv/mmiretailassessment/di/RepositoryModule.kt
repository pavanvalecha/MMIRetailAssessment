package com.prv.mmiretailassessment.di

import com.prv.mmiretailassessment.repository.AccountsListRepository
import com.prv.mmiretailassessment.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module{
    factory{ LoginRepository( get() ) }
    factory{ AccountsListRepository( get() ) }
}