package com.mmi.retailassessment.repository

import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class AccountsListRepository(private val apiService: MMIRetailAPIInterface) {
    suspend fun userDetails(userId: String) = apiService.userDetails(userId, User.UserAuthToken)
}