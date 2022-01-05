package com.kanishk.SpringKotlin.DataSource.Mock

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import com.kanishk.SpringKotlin.Model.Bank
import org.springframework.stereotype.Repository

@Repository("mock")
class MockBankDataSource : BankDataSource {

    var banks = mutableListOf<Bank>(Bank("123", 123.0, 12))

    override fun getBanks(): Collection<Bank> = this.banks

    override fun retrieveBank(accountNumber: String): Bank = banks.firstOrNull() { it.accountNumber == accountNumber}
        ?: throw NoSuchElementException("Could not find any account with account number $accountNumber")

    override fun addBank(bank: Bank): Bank {
        if (banks.any {it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exist.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank : Bank = banks.firstOrNull() {it.accountNumber == bank.accountNumber}
            ?: throw NoSuchElementException("Could not find any account with account number ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String): String {
        val currentBank : Bank = banks.firstOrNull() {it.accountNumber == accountNumber}
            ?: throw NoSuchElementException("Could not find any account with account number $accountNumber")

        banks.remove(currentBank)

        return "account number $accountNumber is removed."
    }

}