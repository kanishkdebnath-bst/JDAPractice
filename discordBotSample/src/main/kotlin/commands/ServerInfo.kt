package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color
import java.util.*

class ServerInfo : Command() {
    override fun execute(event: CommandEvent?) {
        val membersCount = event?.guild?.members?.size
        var members = arrayOfNulls<String>(membersCount!!)

        for(i in 0..membersCount!! - 1) {
            members.set(i, event.guild.members.get(i).effectiveName)
        }

        if (event != null) {
            event.reply("this is a test message")
            val embed = EmbedBuilder()
            embed.setColor(Color.BLUE)
            embed.setAuthor(event.guild.name)
            embed.setImage("https://static.wikia.nocookie.net/godofwar/images/e/e8/Norse_Kratos.png/revision/latest?cb=20181207151509")
            embed.addField("server owner", event.guild.owner?.effectiveName, true)
            embed.addField("member count", membersCount!!.toString(), true)
            embed.setDescription("**Members:** \n" + members.contentToString() + "**Invite Link:**\n" + event.jda.getInviteUrl())
            event.reply(embed.build())
        }
    }

    init {
        this.name = "serverInfo"
        this.aliases = arrayOf("server", "servobro")
        this.help = "gives information about the server"
    }
}