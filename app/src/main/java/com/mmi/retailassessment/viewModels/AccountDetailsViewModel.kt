package com.mmi.retailassessment.viewModels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.mmi.retailassessment.utils.Resource
import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.repository.AccountsDetailsRepository

class AccountDetailsViewModel(private val accountsDetailsRepository: AccountsDetailsRepository) :
    ViewModel() {

    /**
     * @summary Fetch updated Balance values from User Account running on Coroutines
     * @returns livedata - Map<String, AccountDetailsModel>
     */
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

    /**
     * @summary method to get balance value from account
     * @param Int - account number
     * @returns  Float - account balance
     */
    fun getBalance(accountNo: Int): Float {
        val account = User.UserDetails.accounts.get(accountNo.toString())
        if (account != null) {
            return account.Bal
        }
        return 0.0F
    }

    /**
     * @summary method to get overdraft value from account
     * @param Int - account number
     * @returns  Flaot - account balance
     */
    fun getOverdraft(accountNo: Int): Float {
        val account = User.UserDetails.accounts.get(accountNo.toString())
        if (account != null) {
            return account.Overdraft
        }
        return 0.0F
    }
}