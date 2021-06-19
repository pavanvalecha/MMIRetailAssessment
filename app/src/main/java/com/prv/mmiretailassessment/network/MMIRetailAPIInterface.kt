package com.prv.mmiretailassessment.network

import com.prv.mmiretailassessment.models.UserDetails
import retrofit2.http.*

interface MMIRetailAPIInterface {

    @GET("Users/{UserID}.json")
    suspend fun userDetails(@Path("UserID") userId: String, @Query("auth") authKey: String ): UserDetails

}