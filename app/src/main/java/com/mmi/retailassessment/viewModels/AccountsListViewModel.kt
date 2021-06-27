package com.mmi.retailassessment.viewModels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.mmi.retailassessment.utils.Resource
import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.repository.AccountsListRepository

class AccountsListViewModel(private val accountsListRepository: AccountsListRepository) :
    ViewModel() {

    /**
     * @summary coroutine method to fetch User details
     * @returns livedata - UserDetailsModel
     */
    fun listUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = accountsListRepository.userDetails(User.UserId)
            emit(Resource.success(result))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}