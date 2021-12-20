package com.kanishk.SpringKotlin.Model

data class Bank(
    val accountNumber: String,
    val amount: Double,
    val transactionFee: Int
)