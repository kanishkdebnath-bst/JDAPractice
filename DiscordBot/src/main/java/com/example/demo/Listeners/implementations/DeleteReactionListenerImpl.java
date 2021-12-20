package com.example.demo.Listeners.implementations;

import com.example.demo.Listeners.DeleteReactionListener;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.springframework.stereotype.Component;

@Component
public class DeleteReactionListenerImpl implements DeleteReactionListener {
    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        if (event.getEmoji().equalsEmoji("\uD83D\uDC4E")) {
            event.deleteMessage();
        }
    }
}
