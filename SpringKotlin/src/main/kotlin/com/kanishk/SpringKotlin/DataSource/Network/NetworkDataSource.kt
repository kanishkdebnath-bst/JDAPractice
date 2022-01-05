package com.kanishk.SpringKotlin.DataSource.Network

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import com.kanishk.SpringKotlin.DataSource.Network.dto.BankList
import com.kanishk.SpringKotlin.Model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
) : BankDataSource {
    override fun getBanks(): Collection<Bank> {
        val response : ResponseEntity<BankList> = restTemplate.getForEntity("http://54.193.31.159/banks")

        return response.body?.results
            ?: throw IOException("Could not fetch banks from the network.")
    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String): String {
        TODO("Not yet implemented")
    }
}