package me.ci;

import java.io.File;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

public class DiscordBridge implements DiscordAPI
{
	@Override
	public void connect(String token, EventHandler eventHandler) throws LoginException,
		InterruptedException
	{
		JDA jda = new JDABuilder(token)
	            .addEventListener(eventHandler)
	            .build();

        jda.awaitReady();
	}
	
	public static class DiscordChannelBridge implements DiscordChannel
	{
		private MessageChannel _channel;
		
		public DiscordChannelBridge(MessageChannel channel)
		{
			_channel = channel;
		}

		@Override
		public void sendMessage(String message)
		{
			_channel.sendMessage(message).queue();
		}

		@Override
		public void sendFile(File file)
		{
			_channel.sendFile(file).queue();
		}
	}
}
