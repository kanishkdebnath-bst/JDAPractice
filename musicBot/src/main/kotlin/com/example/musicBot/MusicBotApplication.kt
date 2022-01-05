package com.example.musicBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicBotApplication

fun main(args: Array<String>) {
	runApplication<MusicBotApplication>(*args)
}
