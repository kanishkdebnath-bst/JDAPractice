package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import com.jagrosh.jdautilities.command.CommandEvent
import java.time.format.DateTimeFormatter
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color
import java.lang.IndexOutOfBoundsException
import java.util.concurrent.TimeUnit
import java.lang.Runnable

class UserInfo(waiter: EventWaiter) : Command() {
    private val waiter: EventWaiter
    override fun execute(event: CommandEvent) {
        val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        event.reply("Ok! Now, give me the name of a user. Like this @TI-84+")
        waiter.waitForEvent(
            GuildMessageReceivedEvent::class.java,
            { e: GuildMessageReceivedEvent -> e.author == event.author && e.channel == event.channel },
            { e: GuildMessageReceivedEvent ->
                //First argument is the event we are waiting for. Second is the condition that is checked when the event is triggered. Third is the code that will be run with the event and condition is fulfilled.
                try {
                    val name =
                        e.message.mentionedMembers[0] //This time we grab the name from the waiter event because thats where we are getting the name from
                    val eb = EmbedBuilder()
                        .setColor(Color.magenta)
                        .setThumbnail("http://pixelartmaker.com/art/1f41a07add48569.png")
                        .setAuthor("Information on " + name.user.name, "http://www.google.com", name.user.avatarUrl)
                        .setDescription(name.user.name + " joined on " + name.timeCreated.format(fmt) + " :clock: ")
                        .addField("Status:", name.onlineStatus.toString(), true)
                        .addField("Nickname: ", if (name.nickname == null) "No Nickname" else name.nickname, true)
                    event.reply(eb.build())
                    event.reply(event.author.asMention + " there you go")
                } catch (ex: IndexOutOfBoundsException) {
                    println("Exception Occured")
                    event.reply("You need to provide the name as a mention.")
                }
            },
            30,
            TimeUnit.SECONDS,
         { event.reply("You did not give me a name to search. Try again.") } )//Last 3 arguments specify how long to wait for the event and what to do if they never respond
    }

    init {
        super.name = "user-info"
        super.help = "Get some information about a user"
        super.aliases = arrayOf("userinfo")
        super.category = Category("Members")
        super.cooldown = 10
        super.arguments = "[name]"
        this.waiter = waiter
    }
}