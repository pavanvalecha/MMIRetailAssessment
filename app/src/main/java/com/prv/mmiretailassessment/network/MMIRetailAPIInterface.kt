package com.prv.mmiretailassessment.network

import com.prv.mmiretailassessment.models.LoggedInUser
import retrofit2.http.POST

interface MMIRetailAPIInterface {

    @POST("/signin")
    fun login(): LoggedInUser

}