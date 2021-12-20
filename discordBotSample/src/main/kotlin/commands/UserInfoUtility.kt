package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import java.awt.Color
import java.time.format.DateTimeFormatter

class UserInfoUtility : Command() {
    override fun execute(event: CommandEvent?) {
        if(event != null) {
            if (event.args.isEmpty()) {
                event.reply("provide the name like this : ;;userinfo [name]")
            } else {
                val format : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var name : Member
                try {
                    name = event.message.mentionedMembers.get(0)
                    val embed : EmbedBuilder = EmbedBuilder()
                        .setColor(Color.CYAN)
                        .setThumbnail("https://static.wikia.nocookie.net/godofwar/images/e/e8/Norse_Kratos.png/revision/latest?cb=20181207151509")
                        .setAuthor("Information on " + name.user.name, "http://www.google.com", name.user.avatarUrl)
                        .setDescription(name.user.name + " joined on " + name.user.timeCreated.format(format))
                        .addField("Nickname : ", name.nickname ?: "No nickname", true)
                        .addField("Roles : ", name.roles.toString(), true)
                    event.reply(embed.build())

                } catch (exception : Exception) {
                    println(exception.message)
                }


            }
        }
    }

    init {
        super.name = "user-info"
        super.help = "this is the user's information"
        super.aliases = arrayOf("useri", "userinfo")
        super.category = Category("Members")
        super.cooldown = 10
        super.arguments = "[name]"
    }
}