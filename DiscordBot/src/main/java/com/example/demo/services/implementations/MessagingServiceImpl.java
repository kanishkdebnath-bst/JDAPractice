package com.example.demo.services.implementations;

import com.example.demo.Listeners.DeleteReactionListener;
import com.example.demo.services.MessagingService;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class MessagingServiceImpl implements MessagingService {

    @Autowired
    private DeleteReactionListener deleteReactionListener;

    @Override
    public CompletableFuture<Message> sendMessage(MessageAuthor author, String title, String description, String footer, String thumbnail, TextChannel channel) {

        int red = (int) Math.floor(Math.random() * 255);
        int green = (int) Math.floor(Math.random() * 255);
        int blue = (int) Math.floor(Math.random() * 255);

        return new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(author)
                .setThumbnail(thumbnail)
                .setFooter(footer)
                .setDescription(description)
                .setColor(new Color(red, blue, green))
                .setTitle(title)).send(channel);
    }

    @Override
    public void sendMessage(MessageAuthor author, String title, String description, String footer, String thumbnail, TextChannel channel, boolean withDelete) {
        this.sendMessage(author, title, description, footer, thumbnail, channel)
                .thenAccept(message -> {
                    message.addReactionAddListener(deleteReactionListener)
                            .removeAfter(30, TimeUnit.SECONDS);
                });
    }
}
