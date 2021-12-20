package com.example.KotlinBot.Service.Implementation

import com.example.KotlinBot.Listener.DeleteReactionListener
import com.example.KotlinBot.Model.MessageModel
import com.example.KotlinBot.Service.MessagingService
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.message.MessageAuthor
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Color
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlin.math.floor

@Service
class MessagingServiceImpl(
    @Autowired val deleteReactionListener : DeleteReactionListener
) : MessagingService {
    override fun sendMessage(
        author: MessageAuthor,
        title: String,
        description: String,
        footer: String,
        thumbnail: String?,
        channel: TextChannel
    ): CompletableFuture<Message> {
        val red = floor(Math.random() * 255).toInt()
        val green = floor(Math.random() * 255).toInt()
        val blue = floor(Math.random() * 255).toInt()

        return MessageBuilder().setEmbed(
            EmbedBuilder()
                .setAuthor(author)
                .setThumbnail(thumbnail)
                .setFooter(footer)
                .setDescription(description)
                .setColor(Color(red, blue, green))
                .setTitle(title)
        ).send(channel)    }

    override fun sendMessage(
        author: MessageAuthor,
        title: String,
        description: String,
        footer: String,
        thumbnail: String?,
        channel: TextChannel,
        withDelete: Boolean
    ) {
        if (thumbnail != null) {
            this.sendMessage(author, title, description, footer, thumbnail, channel)
                .thenAccept { message: Message ->
                    message.addReactionAddListener(deleteReactionListener)
                        .removeAfter(30, TimeUnit.SECONDS)
                }
        }
    }

    override fun sendEmbedMessage(message: MessageModel, channel: TextChannel): CompletableFuture<Message> {
        val red = floor(Math.random() * 255).toInt()
        val green = floor(Math.random() * 255).toInt()
        val blue = floor(Math.random() * 255).toInt()

        return MessageBuilder().setEmbed(
            EmbedBuilder()
                .setThumbnail(message.thumbnail)
                .setFooter(message.footer)
                .setDescription(message.description)
                .setColor(Color(red, blue, green))
                .setTitle(message.title)
        ).send(channel)
    }
}