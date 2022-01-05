package com.example.musicBot.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class BotListener : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if(event.author.isBot) {
            return
        }
        println("here")
        val message : Message = event.message
        val content : String = message.contentRaw

        if(content == ";;ping") {
            val channel: MessageChannel = event.channel
            channel.sendMessage("PONG!").queue()
            channel.sendMessage("channel id = " + event.channel.id).queue()
            channel.sendMessage("guild id = " + event.guild.id).queue()
        }
    }
}