package me.ci;

import me.ci.commands.CommandHandler;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventHandler implements EventListener
{
	private CommandHandler _commandHandler;
	
	public EventHandler(CommandHandler commandHandler)
	{
		_commandHandler = commandHandler;
	}

	public void onEvent(Event event)
	{
        if (event instanceof ReadyEvent)
        {
        	handleOnReady();
        	return;
        }
        
        if (event instanceof MessageReceivedEvent)
        {
        	_commandHandler.handle((MessageReceivedEvent) event);
        	return;
        }
	}
	
	private void handleOnReady()
	{
        System.out.println("API is ready!");
	}
}
