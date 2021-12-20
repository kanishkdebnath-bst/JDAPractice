package com.kanishk.SpringKotlin.Service

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import com.kanishk.SpringKotlin.Model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> {
        return dataSource.getBanks()
    }

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)

}