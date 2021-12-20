package com.example.KotlinBot.Model

class MessageModel(
    val title : String = "",
    val description : String = "",
    val footer : String = "",
    val thumbnail : String? = "",
) {
    override fun toString(): String {
        return "MessageModel(title='$title', description='$description', footer='$footer', thumbnail=$thumbnail)"
    }
}