package com.example.musicBot.config

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class JDAConfig(
    final val environment: Environment
) {

    private val token : String? = environment.getProperty("token")

    @Bean
    fun jda() : JDA {
        println(token)
        val jda = JDABuilder.createDefault(token).build()
        return jda
    }
}