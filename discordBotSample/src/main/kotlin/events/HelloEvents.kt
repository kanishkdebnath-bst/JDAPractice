package events

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class HelloEvents : ListenerAdapter() {
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val message: String = event.message.contentRaw
        println(message)
        val name: String? = event.member?.user?.name

        if(message.equals("hello")) {
            event.channel.sendMessage("hey there!").queue()
        }

        val messageTokens: List<String> = event.message.contentRaw.split(" ")

        if ("kanishk" in messageTokens) {
            event.channel.sendMessage("you know kanishk? he's my creator").queue()
        }

        if(event.member?.user?.isBot == false) {
            if ("hi" in messageTokens) {
                event.channel.sendMessage("hi, $name").queue()
                println("hi")
            }
        }

        for (index in messageTokens.indices) {
            if(event.member?.user?.isBot == false) {
                event.channel.sendMessage("word #$index is " + messageTokens[index]).queue()
            }
        }
    }
}