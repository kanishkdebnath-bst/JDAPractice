package com.example.demo;

import com.example.demo.Listeners.PingListener;
import com.example.demo.Listeners.RaceListener;
import com.example.demo.Listeners.RateListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DiscordBotSampleApplication {

	@Autowired
	private Environment environment;

	@Autowired
	private PingListener pingListener;

	@Autowired
	private RateListener rateListener;

	@Autowired
	private RaceListener raceListener;

	public static void main(String[] args) {
		SpringApplication.run(DiscordBotSampleApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi() {
		String token = environment.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllNonPrivilegedIntents()
				.login()
				.join();

		api.addMessageCreateListener(pingListener);
		api.addMessageCreateListener(rateListener);
		api.addMessageCreateListener(raceListener);
		return api;
	}

}
