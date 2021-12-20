package com.example.KotlinBot.Listener.Implementation

import com.example.KotlinBot.Listener.RateListener
import com.example.KotlinBot.Service.MessagingService
import org.javacord.api.event.message.MessageCreateEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.floor

@Component
class RateListenerImpl(
    @Autowired private val messagingService: MessagingService
) : RateListener {

    companion object {
        val pattern : Pattern = Pattern.compile(";;rate (\\w+)")
    }

    override fun onMessageCreate(event: MessageCreateEvent?) {
        if (event!!.messageContent.startsWith(";;rate")) {
            val matcher: Matcher = RateListenerImpl.pattern.matcher(event.messageContent)
            if (matcher.matches()) {
                //do the matching thing
                val rating = floor(Math.random() * 100).toInt() + 1
                messagingService.sendMessage(
                    event.messageAuthor,
                    "Rate calculator",
                    event.messageAuthor.displayName + " is " + rating + "% " + matcher.group(1),
                    "Rate again using the same command?",
                    "https://www.nme.com/wp-content/uploads/2021/07/assassinscreed-infinity-696x442.jpg",
                    event.channel
                )
                event.channel.sendMessage(
                    event.messageAuthor.displayName
                )
            } else {
                // send the user helpful statement
                messagingService.sendMessage(
                    event.messageAuthor,
                    "Rate calculator",
                    "are you trying to use the ;;rate command? please use the correct syntax ';;rate [word]'",
                    "Rate again using the same command?",
                    null,
                    event.channel, true
                )
            }
        }
    }
}