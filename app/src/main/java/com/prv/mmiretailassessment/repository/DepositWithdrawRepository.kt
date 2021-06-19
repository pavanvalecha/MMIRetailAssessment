package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.network.MMIRetailAPIInterface
import com.prv.mmiretailassessment.singletons.User

class DepositWithdrawRepository (private val apiService: MMIRetailAPIInterface) {
    suspend fun userDetails(userId:String) = apiService.userDetails(userId, User.UserAuthToken)
}