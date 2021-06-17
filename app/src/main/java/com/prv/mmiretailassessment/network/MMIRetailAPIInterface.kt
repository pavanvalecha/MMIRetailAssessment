package com.prv.mmiretailassessment.network

import com.prv.mmiretailassessment.models.LoggedInUser
import com.prv.mmiretailassessment.models.User
import com.prv.mmiretailassessment.models.UserLoginData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MMIRetailAPIInterface {

    @POST("signin")
    suspend fun login(@Body userLogin: UserLoginData): LoggedInUser

}