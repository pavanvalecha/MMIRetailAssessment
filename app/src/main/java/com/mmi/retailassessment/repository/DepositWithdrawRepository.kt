package com.mmi.retailassessment.repository

import com.mmi.retailassessment.models.AccountBalUpdateModel
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class DepositWithdrawRepository(private val apiService: MMIRetailAPIInterface) {

    suspend fun updateBalance(userId: String, accNo: Int, newBal: AccountBalUpdateModel) =
        apiService.updateBalance(userId, accNo, newBal)
    
}