package com.example.KotlinBot.Listener.Implementation

import com.example.KotlinBot.Listener.PingListener
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.event.message.MessageCreateEvent
import org.springframework.stereotype.Service

@Service
class PingListenerImpl : PingListener {
    override fun onMessageCreate(event: MessageCreateEvent?) {
        if (event != null) {
            if(event.messageContent == ";;ping") {
                event.channel.sendMessage("PONG!");
                event.channel.sendMessage("channel id = " + event.channel.id)
            }
        }
    }
}