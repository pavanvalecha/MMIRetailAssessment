package com.mmi.retailassessment.models

data class UserDetailsModel(
    val Age: String,
    val Name: String,
    val LastName: String,
    var accounts: Map<String, AccountDetailsModel>
)