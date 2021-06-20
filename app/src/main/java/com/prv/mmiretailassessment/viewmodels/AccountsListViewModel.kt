package com.prv.mmiretailassessment.viewmodels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.prv.mmiretailassessment.utils.Resource
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.repository.AccountsListRepository

class AccountsListViewModel(private val accountsListRepository: AccountsListRepository) :
    ViewModel() {

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