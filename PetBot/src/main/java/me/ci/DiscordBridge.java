package me.ci;

import java.io.File;
import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Message.Attachment;

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
		private List<Attachment> _attachments;
		
		public DiscordChannelBridge(MessageChannel channel, List<Attachment> attachments)
		{
			_channel = channel;
			_attachments = attachments;
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
		
		public int getAttachmentCount()
		{
			return _attachments.size();
		}
		
		public void downloadAttachment(File file, int index)
		{
			_attachments.get(index).download(file);
		}
	}
}