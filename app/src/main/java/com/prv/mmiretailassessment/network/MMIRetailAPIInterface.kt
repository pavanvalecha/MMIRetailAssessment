package com.prv.mmiretailassessment.network

import com.prv.mmiretailassessment.models.AccountBalUpdate
import com.prv.mmiretailassessment.models.AccountDetails
import com.prv.mmiretailassessment.models.UserDetails
import retrofit2.http.*

interface MMIRetailAPIInterface {

    @PATCH("Users/{UserID}/accounts/{AccNo}.json")
    suspend fun updateBalance(@Path("UserID") userId: String, @Path("AccNo") accNo: Int, @Body body: AccountBalUpdate ): AccountDetails

    @GET("Users/{UserID}.json")
    suspend fun userDetails(@Path("UserID") userId: String, @Query("auth") authKey: String ): UserDetails

    @GET("Users/{UserID}/accounts.json")
    suspend fun getAccountUpdates(@Path("UserID") userId: String, @Query("auth") authKey: String ): Map<String, AccountDetails>

}