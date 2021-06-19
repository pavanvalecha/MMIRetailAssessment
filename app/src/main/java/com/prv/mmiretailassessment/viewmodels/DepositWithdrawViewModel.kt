package com.prv.mmiretailassessment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.prv.mmiretailassessment.repository.AccountsListRepository
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class DepositWithdrawViewModel(private val accountsListRepository: AccountsListRepository) : ViewModel() {

    fun depositAmount(amount:Float) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = accountsListRepository.userDetails(User.UserId)
            emit(Resource.success(result))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun withdrawAmount(amount:Float) = liveData(Dispatchers.IO) {
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