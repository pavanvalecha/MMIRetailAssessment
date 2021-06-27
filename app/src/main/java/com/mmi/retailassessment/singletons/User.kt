package com.mmi.retailassessment.singletons

import com.mmi.retailassessment.models.UserDetailsModel

/**
 * @summary singleton User Object holding session user Data and User details
 */
object User {

    var UserId = ""
    var UserAuthToken = ""
    lateinit var UserDetails: UserDetailsModel

}