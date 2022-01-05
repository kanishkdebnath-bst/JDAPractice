package com.kanishk.SpringKotlin.DataSource.Network.dto

import com.kanishk.SpringKotlin.Model.Bank

data class BankList(
    val results: Collection<Bank>
)