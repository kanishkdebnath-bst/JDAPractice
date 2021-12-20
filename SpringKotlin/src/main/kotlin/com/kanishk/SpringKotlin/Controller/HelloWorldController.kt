package com.kanishk.SpringKotlin.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class HelloWorldController {

    @GetMapping("hello")
    fun sayHello() : String = "Hello World! This is a REST endpoint."
}