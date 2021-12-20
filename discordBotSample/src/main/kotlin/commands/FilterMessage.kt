package commands

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class FilterMessage : ListenerAdapter() {
    companion object{
        var allowed = false
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if(FilterOnOff.filterOn) {
            if(event.message.contentRaw.equals(";;filtermessage") and allowed) {
                event.channel.sendMessage("Filter response is disabled").queue()
                println("disabled")
                allowed = false
            } else if (event.message.contentRaw.equals(";;filtermessage")) {
                event.channel.sendMessage("Filter response is enabled").queue()
                println("enabled")
                allowed = true
            }
        } else if(event.message.contentRaw.equals(";;filtermessage")) {
            event.channel.sendMessage("you cannot to enable filter message as the filter is off, type - ;;togglefilter to enable filter message").queue()
        }
    }
}