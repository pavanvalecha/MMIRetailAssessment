package com.prv.mmiretailassessment.viewmodels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.prv.mmiretailassessment.utils.Resource
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.models.AccountBalUpdateModel
import com.prv.mmiretailassessment.repository.DepositWithdrawRepository

class DepositWithdrawViewModel(private val depositWithdrawRepository: DepositWithdrawRepository) :
    ViewModel() {

    fun depositAmount(accNo: Int, depositAmount: Float, existingAmount: Float) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                val newAmt = existingAmount + depositAmount
                val result = depositWithdrawRepository.updateBalance(
                    User.UserId,
                    accNo,
                    AccountBalUpdateModel(newAmt)
                )
                emit(Resource.success(result))
            } catch (exception: Exception) {
                Timber.e(exception)
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }

    fun withdrawAmount(accNo: Int, withdrawAmount: Float, existingAmount: Float) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                val newAmt = existingAmount - withdrawAmount
                val result = depositWithdrawRepository.updateBalance(
                    User.UserId,
                    accNo,
                    AccountBalUpdateModel(newAmt)
                )
                emit(Resource.success(result))
            } catch (exception: Exception) {
                Timber.e(exception)
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
}