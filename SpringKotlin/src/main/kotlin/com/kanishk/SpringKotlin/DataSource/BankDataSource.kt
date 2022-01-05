package com.kanishk.SpringKotlin.DataSource

import com.kanishk.SpringKotlin.Model.Bank

interface BankDataSource {

    fun getBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun addBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(accountNumber: String): String
}