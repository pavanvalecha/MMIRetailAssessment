package com.mmi.retailassessment.network

import retrofit2.http.*
import com.mmi.retailassessment.models.UserDetailsModel
import com.mmi.retailassessment.models.AccountDetailsModel
import com.mmi.retailassessment.models.AccountBalUpdateModel


interface MMIRetailAPIInterface {

    @GET("Users/{UserID}.json")
    suspend fun userDetails(
        @Path("UserID") userId: String,
        @Query("auth") authKey: String
    ): UserDetailsModel

    @GET("Users/{UserID}/accounts.json")
    suspend fun getAccountUpdates(
        @Path("UserID") userId: String,
        @Query("auth") authKey: String
    ): Map<String, AccountDetailsModel>

    @PATCH("Users/{UserID}/accounts/{AccNo}.json")
    suspend fun updateBalance(
        @Path("UserID") userId: String,
        @Path("AccNo") accNo: Int,
        @Body body: AccountBalUpdateModel
    ): AccountDetailsModel


}