package com.mmi.retailassessment.viewModels

import timber.log.Timber
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import com.mmi.retailassessment.utils.Resource
import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.models.AccountBalUpdateModel
import com.mmi.retailassessment.repository.DepositWithdrawRepository

class DepositWithdrawViewModel(private val depositWithdrawRepository: DepositWithdrawRepository) :
    ViewModel() {

    /**
     * @summary coroutine method for deposit amount to user account
     * @param Int - account number, Float - deposit amount, Float - existing amount
     * @returns livedata - AccountDetailsModel
     */
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

    /**
     * @summary coroutine method for withdraw amount from user account
     * @param Int - account number, Float - deposit amount, Float - existing amount
     * @returns livedata - AccountDetailsModel
     */
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