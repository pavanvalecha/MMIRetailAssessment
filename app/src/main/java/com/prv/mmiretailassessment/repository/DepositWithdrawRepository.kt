package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.models.AccountBalUpdateModel
import com.prv.mmiretailassessment.network.MMIRetailAPIInterface

class DepositWithdrawRepository(private val apiService: MMIRetailAPIInterface) {

    suspend fun updateBalance(userId: String, accNo: Int, newBal: AccountBalUpdateModel) =
        apiService.updateBalance(userId, accNo, newBal)
    
}