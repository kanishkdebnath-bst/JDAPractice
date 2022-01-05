package com.kanishk.SpringKotlin.Controller

import com.kanishk.SpringKotlin.Model.Bank
import com.kanishk.SpringKotlin.Service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.NoSuchElementException

@RestController
@RequestMapping("api/banks")
class BankController(private val service: BankService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

    @GetMapping("{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank : Bank): Bank = service.addBank(bank)

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateBank(@RequestBody bank: Bank) : Bank = service.updateBank(bank)

    @DeleteMapping("{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String) : ResponseEntity<String> {
        val message = service.deleteBank(accountNumber)
        return ResponseEntity(message, HttpStatus.NO_CONTENT)
    }
}