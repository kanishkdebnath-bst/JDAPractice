package commands

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Calculate : ListenerAdapter(){

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {

        val tokens : List<String> = event.message.contentRaw.split(" ")

        if (tokens[0].equals(";;calculate", true)) {
            event.channel.sendMessage("this is calculate command").queue()
            if (tokens[1].equals("add", true)) {
                event.channel.sendMessage("this is add command").queue()
                val result = tokens[2].toInt() + tokens[3].toInt()
                event.channel.sendMessage("result is $result").queue()
            } else if (tokens[1].equals("sub", true)) {
                event.channel.sendMessage("this is subtraction command").queue()
                val result = tokens[2].toInt() - tokens[3].toInt()
                event.channel.sendMessage("result is $result").queue()
            }
        }


    }

}