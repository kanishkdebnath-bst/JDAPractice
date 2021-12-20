package com.example.KotlinBot.Service

import com.example.KotlinBot.Model.MessageModel
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.message.MessageAuthor
import java.util.concurrent.CompletableFuture

interface MessagingService {

    fun sendMessage(author : MessageAuthor, title : String, description : String, footer : String, thumbnail : String?, channel : TextChannel) : CompletableFuture<Message>

    fun sendMessage(author: MessageAuthor, title: String, description: String, footer: String, thumbnail: String?, channel: TextChannel, withDelete: Boolean)

    fun sendEmbedMessage(message: MessageModel, channel: TextChannel) : CompletableFuture<Message>
}