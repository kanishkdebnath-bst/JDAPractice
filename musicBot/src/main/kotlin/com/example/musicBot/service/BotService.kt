package com.example.musicBot.service

import com.example.musicBot.model.ChannelData

interface BotService {

    fun sendMessage(guild : String, channel : String, message : String)
    fun sendMessage(message : String)
    fun getChannels() : MutableList<String>
    fun getGuilds(): MutableList<String>
    fun getChannelsData() : MutableList<ChannelData>
}