package com.kanishk.SpringKotlin.DataSource.Mock

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import com.kanishk.SpringKotlin.Model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(Bank("123", 123.0, 12))

    override fun getBanks(): Collection<Bank> = this.banks

    override fun retrieveBank(accountNumber: String): Bank = banks.first { it.accountNumber == accountNumber}

}