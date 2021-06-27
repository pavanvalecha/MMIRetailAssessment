package com.mmi.retailassessment.repository

import com.mmi.retailassessment.singletons.User
import com.mmi.retailassessment.network.MMIRetailAPIInterface

class AccountsListRepository(private val apiService: MMIRetailAPIInterface) {

    /**
     * @summary Repository method for fetching user Details
     * @param string userId
     * @returns UserDetailsModel
     */
    suspend fun userDetails(userId: String) = apiService.userDetails(userId, User.UserAuthToken)
}