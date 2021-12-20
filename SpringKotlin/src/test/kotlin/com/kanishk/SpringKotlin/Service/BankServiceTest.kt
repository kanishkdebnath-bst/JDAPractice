package com.kanishk.SpringKotlin.Service

import com.kanishk.SpringKotlin.DataSource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource : BankDataSource = mockk(relaxed = true) //whenever this is call some kind of default value must be returned

    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        //given
        //every { dataSource.getBanks() } returns emptyList() -- if relaxed is not used

        //when
        val banks = bankService.getBanks()

        //then
        verify(exactly = 1) { dataSource.getBanks() }

    }
}