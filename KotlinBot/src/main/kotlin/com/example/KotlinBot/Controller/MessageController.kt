package com.example.KotlinBot.Controller

import com.example.KotlinBot.Model.MessageModel
import com.example.KotlinBot.Service.MessagingService
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bot")
class MessageController(
    val messagingService: MessagingService,
    var message: MessageModel,
    var autoSend: Boolean = false
) : MessageCreateListener{

    @PostMapping("message")
    fun sendEmbedMessage(@RequestBody message: MessageModel): String {
        this.message = message;
        autoSend = true
        return this.message.toString()
    }

    override fun onMessageCreate(event: MessageCreateEvent?) {
        if (autoSend) {
            println("here")
            messagingService.sendEmbedMessage(this.message, event!!.channel)
            autoSend = false
        }
    }
}