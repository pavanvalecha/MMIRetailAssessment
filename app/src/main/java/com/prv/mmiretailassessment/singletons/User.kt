package com.prv.mmiretailassessment.singletons

import com.prv.mmiretailassessment.models.UserDetails

object User {
    var UserId = ""
    var UserAuthToken = ""
    lateinit var UserDetails: UserDetails
}