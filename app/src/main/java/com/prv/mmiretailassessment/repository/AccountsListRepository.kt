package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.network.MMIRetailAPIInterface

class AccountsListRepository(private val apiService: MMIRetailAPIInterface) {
    suspend fun userDetails(userId: String) = apiService.userDetails(userId, User.UserAuthToken)
}