package com.example.musicBot.config

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JDAConfig {

    private val token : String = "OTIwNTcxMjA0NjIzMDExODkw.YbmSvg.tkGD2z6Wjrn4v8-VQthiMbaIPUk"

    @Bean
    fun jda() : JDA {
        val jda = JDABuilder.createDefault(token).build()
        return jda
    }
}