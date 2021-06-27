package com.mmi.retailassessment.repository

import com.mmi.retailassessment.models.AccountBalUpdateModel
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class DepositWithdrawRepository(private val apiService: MMIRetailAPIInterface) {

    /**
     * @summary repository method for updating user account balance
     * @param string - userId, Int - account Number, AccountBalUpdateModel - New Balance value
     * @returns updated AccountDetailsModel
     */
    suspend fun updateBalance(userId: String, accNo: Int, newBal: AccountBalUpdateModel) =
        apiService.updateBalance(userId, accNo, newBal)
    
}