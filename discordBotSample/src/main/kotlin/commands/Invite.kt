package commands

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Invite : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val tokens: List<String> = event.message.contentRaw.split(" ")
        val timeString = 10
        println(tokens)
        if (tokens[0].equals(";;invite", true)) {
            if (tokens.size == 1) {
                event.channel.sendMessage("to use ;;invite, do ;;invite create").queue()
            } else if (tokens.size > 1) {
                if (tokens[1] == "create") {
                    event.channel.sendMessage("you want to invite someone! cool!").queue()
                    event.channel.sendMessage("hey "+ event.author.name + ", give them the link : " + event.channel.createInvite().setMaxAge(timeString).complete().url).queue()
                    event.channel.sendMessage("invite is going to last for $timeString seconds").queue()
                }
            }

        }
    }
}