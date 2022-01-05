package com.example.musicBot.controller

import com.example.musicBot.model.ChannelData
import com.example.musicBot.model.MessageRequest
import com.example.musicBot.service.BotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(
    @Autowired val botService: BotService
) {
    @PostMapping("/sendmessage")
    fun messageDiscord(@RequestBody message: MessageRequest) {
        println(message)
        botService.sendMessage(message.guild, message.channel, message.message)
    }

    @PostMapping("/sendgeneralmessage")
    fun messageDiscord(@RequestBody message: String): String {
        println(message)
        botService.sendMessage(message)
        return message
    }

    @GetMapping("/channels")
    fun getChannels(): MutableList<String> {
        return botService.getChannels()
    }

    @GetMapping("/guilds")
    fun getGuilds(): MutableList<String> {
        return botService.getGuilds()
    }

    @GetMapping("/channellist")
    fun getChannelList(): MutableList<ChannelData> {
        return botService.getChannelsData()
    }


}