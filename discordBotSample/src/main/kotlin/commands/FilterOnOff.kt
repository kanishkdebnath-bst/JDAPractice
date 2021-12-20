package commands

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class FilterOnOff : ListenerAdapter() {

    companion object{
        var filterOn = true
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if(event.message.contentRaw.equals(";;togglefilter") and filterOn){
            event.channel.sendMessage("the curse filter is disabled by " + event.author.name).queue()
            filterOn = false
        } else if(event.message.contentRaw.equals(";;togglefilter")){
            event.channel.sendMessage("the curse filter is enabled by " + event.author.name).queue()
            filterOn = true
        }
    }
}