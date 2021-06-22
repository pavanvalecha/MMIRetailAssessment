package com.mmi.retailassessment.repository

import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class AccountsDetailsRepository(private val apiService: MMIRetailAPIInterface) {

    suspend fun getAccountUpdates(userId: String) =
        apiService.getAccountUpdates(userId, User.UserAuthToken)

}