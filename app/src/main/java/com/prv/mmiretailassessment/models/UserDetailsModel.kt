package com.prv.mmiretailassessment.models

data class UserDetailsModel(
    val Age: String,
    val Name: String,
    var accounts: Map<String, AccountDetailsModel>
)