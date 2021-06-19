package com.prv.mmiretailassessment.repository

import com.prv.mmiretailassessment.network.MMIRetailAPIInterface

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository( private val apiService: MMIRetailAPIInterface) {

    fun logout() {
        //apiService.logout()
    }

    //suspend fun login(username: String, password: String) = apiService.login(UserLoginData(username, password))

    private fun setLoggedInUser(/*loggedInUser: LoggedInUser*/) {
        //this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}