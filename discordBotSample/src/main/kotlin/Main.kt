import events.CategoryEvents
import events.HelloEvents
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

fun main() {
    val jda = JDABuilder.createDefault("OTE3NDI0NDc3MTAzNTg3MzI5.Ya4gIA.Q2TORVr4ZWIGyaKvmB_k-iTiees").build()

    jda.addEventListener(HelloEvents())
    jda.addEventListener(CategoryEvents())
}