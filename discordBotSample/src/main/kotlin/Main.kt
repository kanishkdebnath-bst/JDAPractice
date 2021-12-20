import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import commands.*
import net.dv8tion.jda.api.JDABuilder

fun main() {
    val jda = JDABuilder.createDefault().build()

//    jda.addEventListener(HelloEvents())
//    jda.addEventListener(CategoryEvents())
//    jda.addEventListener(Calculate())
//    jda.addEventListener(Invite())
//    jda.addEventListener(UserInfoCommand())
//    jda.addEventListener(Filter())
//    jda.addEventListener(FilterOnOff())
//    jda.addEventListener(FilterMessage())

    val waiter : EventWaiter = EventWaiter()
    val builder : CommandClientBuilder = CommandClientBuilder()
    builder.setOwnerId("917424477103587329")
    builder.setPrefix(";;")
    builder.setHelpWord("help")
//    builder.addCommand(ServerInfo())
    builder.addCommand(Image())
//    builder.addCommand(UserInfoUtility())
    builder.addCommand(UserInfo(waiter))
    val client : CommandClient = builder.build()

    jda.addEventListener(client)
}
