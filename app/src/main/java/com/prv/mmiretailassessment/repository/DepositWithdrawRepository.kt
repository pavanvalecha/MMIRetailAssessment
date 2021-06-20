package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.models.AccountBalUpdate
import com.prv.mmiretailassessment.models.AccountDetails
import com.prv.mmiretailassessment.network.MMIRetailAPIInterface
import com.prv.mmiretailassessment.singletons.User

class DepositWithdrawRepository (private val apiService: MMIRetailAPIInterface) {
    suspend fun updateBalance(userId:String, accNo:Int, newBal: AccountBalUpdate) = apiService.updateBalance(userId, accNo, newBal)
}