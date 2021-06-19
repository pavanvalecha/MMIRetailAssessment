package com.prv.mmiretailassessment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.prv.mmiretailassessment.repository.AccountsListRepository
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class AccountDetailsViewModel() : ViewModel() {

    fun getBalance(accountNo: String): Int {
        val account = User.UserDetails.accounts.get(accountNo)
        if (account != null) {
            return account.Bal
        }
        return 0
    }

    fun getOverdraft(accountNo: String) : Int {
        val account = User.UserDetails.accounts.get(accountNo)
        if (account != null) {
            return account.Overdraft
        }
        return 0
    }
}