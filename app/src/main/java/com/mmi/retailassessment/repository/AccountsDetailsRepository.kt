package com.mmi.retailassessment.repository

import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class AccountsDetailsRepository(private val apiService: MMIRetailAPIInterface) {

    /**
     * @summary Repository method for fetching account updates
     * @param string userId
     * @returns Map of String, AccountDetailsModel
     */
    suspend fun getAccountUpdates(userId: String) =
        apiService.getAccountUpdates(userId, User.UserAuthToken)

}