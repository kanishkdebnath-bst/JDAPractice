package com.example.KotlinBot.Listener.Implementation

import com.example.KotlinBot.Listener.DeleteReactionListener
import org.javacord.api.event.message.reaction.ReactionAddEvent
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class DeleteReactionListenerImpl : DeleteReactionListener {
    override fun onReactionAdd(event: ReactionAddEvent?) {
        if (event!!.emoji.equalsEmoji("\uD83D\uDC4E")) {
            event.deleteMessage()
        }
    }

}