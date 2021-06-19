package com.prv.mmiretailassessment.models

data class UserDetails (
    val accounts: Map<String, AccountDetails>,
    val Name: String,
    val Age: String
)