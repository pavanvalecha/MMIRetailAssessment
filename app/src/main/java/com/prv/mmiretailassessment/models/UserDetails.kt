package com.prv.mmiretailassessment.models

data class UserDetails (
    var accounts: Map<String, AccountDetails>,
    val Name: String,
    val Age: String
)