package com.kanishk.SpringKotlin.DataSource

import com.kanishk.SpringKotlin.Model.Bank

interface BankDataSource {

    fun getBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
}