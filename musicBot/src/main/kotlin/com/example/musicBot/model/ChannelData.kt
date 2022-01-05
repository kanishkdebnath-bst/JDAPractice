package com.example.musicBot.model

data class ChannelData(
    var channelName: String = "",
    var channelID: String = "",
) {
    override fun toString(): String {
        return "ChannelData(channelName='$channelName', channelID='$channelID')"
    }
}