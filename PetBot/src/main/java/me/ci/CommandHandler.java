package me.ci;

import java.util.ArrayList;
import java.util.Arrays;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHandler
{
	private ArrayList<Command> _commands = new ArrayList<>();
	
	public void registerCommand(Command command)
	{
		_commands.add(command);
	}
	
	public void handle(SentCommand com)
	{
		for (Command c : _commands)
			if (c.getName().equals(com.getCommand()))
			{
				c.run(com);
				return;
			}
	}
	
	public void handle(MessageReceivedEvent e)
	{
		if (e.getAuthor().isBot())
			return;
		if (e.getChannelType() != ChannelType.TEXT)
			return;
		
		Message message = e.getMessage();
		String content = message.getContentRaw();
		
		if (content.matches("[!][a-z]+.*"))
		{
			String[] args = content.split("[ ]");
			
			String commandName = args[0];
			
			if (args.length == 1)
				args = new String[0];
			else
				args = Arrays.copyOfRange(args, 1, args.length);
			
			SentCommand com = new SentCommand((TextChannel) e.getChannel(), commandName, args);
			handle(com);
		}
	}
}
