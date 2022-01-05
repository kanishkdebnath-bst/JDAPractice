package com.kanishk.SpringKotlin.Service

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import com.kanishk.SpringKotlin.Model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Qualifier("mock") val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> {
        return dataSource.getBanks()
    }

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.addBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String): String = dataSource.deleteBank(accountNumber)

}