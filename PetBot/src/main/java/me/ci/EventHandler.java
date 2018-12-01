package me.ci;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventHandler implements EventListener
{
	public void onEvent(Event event)
	{
        if (event instanceof ReadyEvent)
        {
        	handleOnReady();
        	return;
        }
        
        if (event instanceof GuildReadyEvent)
        {
        	handleGuildReady((GuildReadyEvent) event);
        	return;
        }
        
        if (event instanceof MessageReceivedEvent)
        {
        	handleMessageReceived((MessageReceivedEvent) event);
        	return;
        }
	}
	
	private void handleOnReady()
	{
        System.out.println("API is ready!");
	}
	
	private void handleGuildReady(GuildReadyEvent e)
	{
		e.getGuild().getDefaultChannel().sendMessage("Bot is connected.");
	}
	
	private void handleMessageReceived(MessageReceivedEvent e)
	{
		if (e.getAuthor().isBot())
			return;
		
		Message message = e.getMessage();
		String content = message.getContentRaw();
		
		if (content.equals("!test1"))
		{
			MessageChannel channel = e.getChannel();
			channel.sendMessage("Bot is listening.").queue();
		}
	}
}
