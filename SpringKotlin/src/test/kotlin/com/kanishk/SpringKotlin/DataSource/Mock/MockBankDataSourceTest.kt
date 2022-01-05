package com.kanishk.SpringKotlin.DataSource.Mock

import com.kanishk.SpringKotlin.Model.Bank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.function.Consumer

internal class MockBankDataSourceTest {

    private val mockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks` () {
        //given


        //when
        val banks = mockBankDataSource.getBanks()

        //then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide a mock data`() {
        //given


        //when
        val banks = mockBankDataSource.getBanks()

        //then
        assertThat(banks).allMatch {it.accountNumber.isNotBlank()}
        assertThat(banks).anyMatch {it.transactionFee != 0}
        assertThat(banks).anyMatch {it.trust != 0.0}

    }
}