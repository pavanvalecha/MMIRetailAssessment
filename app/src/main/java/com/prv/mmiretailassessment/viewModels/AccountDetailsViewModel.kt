package com.prv.mmiretailassessment.viewModels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.prv.mmiretailassessment.utils.Resource
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.repository.AccountsDetailsRepository

class AccountDetailsViewModel(private val accountsDetailsRepository: AccountsDetailsRepository) :
    ViewModel() {

    fun getAccountUpdates() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = accountsDetailsRepository.getAccountUpdates(User.UserId)
            emit(Resource.success(result))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getBalance(accountNo: Int): Float {
        val account = User.UserDetails.accounts.get(accountNo.toString())
        if (account != null) {
            return account.Bal
        }
        return 0.0F
    }

    fun getOverdraft(accountNo: Int): Float {
        val account = User.UserDetails.accounts.get(accountNo.toString())
        if (account != null) {
            return account.Overdraft
        }
        return 0.0F
    }
}