import commands.FilterMessage
import commands.FilterOnOff
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Filter : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (FilterOnOff.filterOn) {
            val badWords = arrayOf(
                "anal",
                "anus",
                "arse",
                "ass",
                "motherfucker",
                "balls",
                "bastard",
                "bitch",
                "blowjob",
                "blow job",
                "buttplug",
                "cock",
                "coon",
                "cunt",
                "dildo",
                "fag",
                "dyke",
                "fuck",
                "fucking",
                "nigger",
                "Goddamn",
                "jizz",
                "nigga"
            )

            val tokens: List<String> = event.message.contentRaw.split(" ")

            for (i in tokens.indices) {
                if (tokens[i] in badWords) {
                    event.message.delete().queue()
                    println(FilterMessage.allowed)
                    if (FilterMessage.allowed) {
                        event.channel.sendMessage("Don't use curse words.").queue()
                        println("here")
                    }
                }
            }
        }
    }
}