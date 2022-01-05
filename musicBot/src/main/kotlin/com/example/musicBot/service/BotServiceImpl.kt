package com.example.musicBot.service

import com.example.musicBot.commands.BotListener
import com.example.musicBot.model.ChannelData
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.TextChannel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BotServiceImpl(
    @Autowired val jda: JDA
) : BotService {
    override fun sendMessage(guild: String, channel: String, message: String) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String) {
        try {
            jda.awaitReady()
            val textChannels: MutableList<TextChannel> = jda.getTextChannelsByName("general", true)
            for (textChannel in textChannels) {
                textChannel.sendMessage(message).queue()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun getChannels(): MutableList<String> {
        val channels: MutableList<TextChannel> = jda.getTextChannelsByName("general", true)
        val channelIds: MutableList<String> = mutableListOf<String>()
        for (channel in channels) {
            channelIds.add(channel.id)
            channel.sendMessage(channel.id)
        }
        return channelIds
    }

    override fun getGuilds(): MutableList<String> {
        val channels: MutableList<TextChannel> = jda.getTextChannelsByName("general", true)
        val guildIds: MutableList<String> = mutableListOf<String>()
        for (channel in channels) {
            guildIds.add(channel.guild.id)
            channel.sendMessage(channel.id)
        }
        return guildIds
    }

    override fun getChannelsData(): MutableList<ChannelData> {
        val channels: MutableList<TextChannel> = jda.textChannels
        val channelData: ChannelData = ChannelData()
        val channelDataList: MutableList<ChannelData> = mutableListOf()

        for (channel in channels) {
            channelData.channelID = channel.id
            println(channel.id)
            println(channel.name)
            channelData.channelName = channel.name
            println(channelData)
            channelDataList.add(channelData)
            println(channelDataList)
        }
        return channelDataList
    }
}