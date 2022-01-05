package com.example.musicBot.model

data class MessageRequest(
    val message: String,
    val channel: String,
    val guild: String
) {
    override fun toString(): String {
        return "MessageRequest(message='$message', channel='$channel', guild='$guild')"
    }
}