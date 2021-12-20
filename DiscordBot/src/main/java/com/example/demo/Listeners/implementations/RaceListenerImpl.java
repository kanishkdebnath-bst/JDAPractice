package com.example.demo.Listeners.implementations;

import com.example.demo.Listeners.RaceListener;
import com.example.demo.services.MessagingService;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceListenerImpl implements RaceListener {

    private static boolean active = false;

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equals(";;race")) {
            System.out.println("here");
            if (!active) {
                active = true;
                System.out.println("here");
                messagingService.sendMessage(
                                event.getMessageAuthor(),
                                "The race begins",
                                "Be the first to react to the message to win",
                                null,
                                "https://images.unsplash.com/photo-1457969414820-5fdd86fc0b84?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1048&q=80",
                                event.getChannel())
                        .thenAccept(message -> {
                            message.addReactionAddListener(listener -> {
                                if (active) {
                                    message.edit(new EmbedBuilder()
                                            .setTitle("The race ends!")
                                            .setDescription("Congratulations! **" + listener.getUser().get().getMentionTag()+ "** was the first! \nThe race is now over.")
                                            .setFooter("Race again?"));
                                    active = false;
                                }
                            });
                        });
            }
        }
    }
}
