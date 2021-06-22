package com.mmi.retailassessment.singletons

import com.mmi.retailassessment.models.UserDetailsModel

object User {

    var UserId = ""
    var UserAuthToken = ""
    lateinit var UserDetails: UserDetailsModel

}