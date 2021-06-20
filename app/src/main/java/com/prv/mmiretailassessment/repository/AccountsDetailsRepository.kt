package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.network.MMIRetailAPIInterface
import com.prv.mmiretailassessment.singletons.User

class AccountsDetailsRepository (private val apiService: MMIRetailAPIInterface) {
    suspend fun getAccountUpdates(userId:String) = apiService.getAccountUpdates(userId, User.UserAuthToken)
}