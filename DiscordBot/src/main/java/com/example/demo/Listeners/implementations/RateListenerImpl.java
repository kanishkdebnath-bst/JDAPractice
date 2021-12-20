package com.example.demo.Listeners.implementations;

import com.example.demo.Listeners.RateListener;
import com.example.demo.services.MessagingService;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RateListenerImpl implements RateListener {
    private final static Pattern pattern = Pattern.compile(";;rate (\\w+)");

    @Autowired
    private MessagingService messagingService;


    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().startsWith(";;rate")) {
            Matcher matcher = pattern.matcher(event.getMessageContent());
            if(matcher.matches()) {
                //do the matching thing
                int rating = (int) Math.floor(Math.random() * 100) + 1;

                messagingService.sendMessage(event.getMessageAuthor(),
                        "Rate calculator",
                        event.getMessageAuthor().getDisplayName()+ " is " + rating + "% " + matcher.group(1),
                        "Rate again using the same command?",
                        "https://www.nme.com/wp-content/uploads/2021/07/assassinscreed-infinity-696x442.jpg",
                        event.getChannel());


                event.getChannel().sendMessage(
                  event.getMessageAuthor().getDisplayName()
                );
            } else {
                // send the user helpful statement
                messagingService.sendMessage(event.getMessageAuthor(),
                        "Rate calculator",
                        "are you trying to use the ;;rate command? please use the correct syntax ';;rate [word]'",
                        "Rate again using the same command?",
                        null,
                        event.getChannel(), true);
            }
        }
    }
}
