package events

import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent

class CategoryEvents : ListenerAdapter() {
    override fun onCategoryCreate(event: CategoryCreateEvent) {
        val name = event.category.name
        event.guild.defaultChannel!!.sendMessage("this category of name $name is created by someone.").queue()
    }
}