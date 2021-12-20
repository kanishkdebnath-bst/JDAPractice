package commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class UserInfoCommand : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val embedBuilder = EmbedBuilder()
        val tokens: List<String> = event.message.contentRaw.split(" ")

        embedBuilder.setTitle("Kenny ki embbedded bhasha")
        embedBuilder.setColor(Color.ORANGE)
        embedBuilder.setImage("https://files.worldwildlife.org/wwfcmsprod/images/Panda_in_Tree/hero_small/99i33zyc0l_Large_WW170579.jpg")
        embedBuilder.addField("Name", "Kenny", true)
        embedBuilder.addField("Class", "123", true)
        embedBuilder.addField("Roll number", "345", true)
        if (tokens[0] == ";;embed") {
            event.channel.sendMessage(embedBuilder.build()).queue()
        }
    }
}