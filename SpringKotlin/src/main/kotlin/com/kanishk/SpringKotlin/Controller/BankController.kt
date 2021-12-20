package com.kanishk.SpringKotlin.Controller

import com.kanishk.SpringKotlin.Model.Bank
import com.kanishk.SpringKotlin.Service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class BankController(private val service: BankService) {

    @GetMapping("banks")
    fun getBanks(): Collection<Bank> = service.getBanks()

    @GetMapping("banks/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)
}