package com.example.demo.Listeners.implementations;

import com.example.demo.Listeners.PingListener;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class PingListenerImpl implements PingListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().equalsIgnoreCase(";;ping")) {
            event.getChannel().sendMessage("PONG!");
        }
    }
}
