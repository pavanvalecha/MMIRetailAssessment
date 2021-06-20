package com.prv.mmiretailassessment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.prv.mmiretailassessment.models.AccountBalUpdate
import com.prv.mmiretailassessment.repository.DepositWithdrawRepository
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class DepositWithdrawViewModel(private val depositWithdrawRepository: DepositWithdrawRepository) : ViewModel() {

    fun depositAmount(accNo:Int, depositAmount:Float, existingAmount: Float) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val newAmt = existingAmount + depositAmount
            val result = depositWithdrawRepository.updateBalance(User.UserId, accNo, AccountBalUpdate(newAmt))
            emit(Resource.success(result))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun withdrawAmount(accNo:Int, withdrawAmount:Float, existingAmount: Float) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val newAmt = existingAmount - withdrawAmount
            val result = depositWithdrawRepository.updateBalance(User.UserId, accNo, AccountBalUpdate(newAmt))
            emit(Resource.success(result))
        } catch (exception: Exception) {
            Timber.e(exception)
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}