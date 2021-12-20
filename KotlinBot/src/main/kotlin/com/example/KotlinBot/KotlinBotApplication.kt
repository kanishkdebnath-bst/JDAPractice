package com.example.KotlinBot

import com.example.KotlinBot.Controller.MessageController
import com.example.KotlinBot.Listener.DeleteReactionListener
import com.example.KotlinBot.Listener.Implementation.DeleteReactionListenerImpl
import com.example.KotlinBot.Listener.Implementation.PingListenerImpl
import com.example.KotlinBot.Listener.Implementation.RateListenerImpl
import com.example.KotlinBot.Listener.PingListener
import com.example.KotlinBot.Listener.RateListener
import com.example.KotlinBot.Model.MessageModel
import com.example.KotlinBot.Service.Implementation.MessagingServiceImpl
import com.example.KotlinBot.Service.MessagingService
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@SpringBootApplication
class KotlinBotApplication(
	@Autowired private val environment: Environment,
){

	@Bean
	fun pingListener() : PingListener = PingListenerImpl()

	@Bean
	fun rateListener() : RateListener = RateListenerImpl(messagingService())

	@Bean
	fun deleteListener() : DeleteReactionListener = DeleteReactionListenerImpl()

	@Bean
	fun messagingService(): MessagingService = MessagingServiceImpl(deleteListener())

	@Bean
	fun messageController() : MessageController = MessageController(messagingService(), MessageModel())

	@Bean
	fun discordApi() : DiscordApi {

		val token : String? = environment.getProperty("TOKEN")
		println(token)
		val api = DiscordApiBuilder().setToken(token)
			.setAllNonPrivilegedIntents()
			.login()
			.join()

		api.addMessageCreateListener(pingListener())
		api.addMessageCreateListener(rateListener())
		api.addMessageCreateListener(messageController())
		return api
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinBotApplication>(*args)
}


