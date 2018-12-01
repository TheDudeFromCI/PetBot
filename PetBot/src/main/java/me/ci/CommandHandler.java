package me.ci;

import java.util.ArrayList;
import java.util.Arrays;

import me.ci.DiscordBridge.DiscordChannelBridge;
import net.dv8tion.jda.core.entities.Message;
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
				System.out.println("  Running command.");

				c.run(com);
				return;
			}
		System.out.println("  Command not found.");
		com.sendMessage("Command not found!");
	}
	
	public void handle(MessageReceivedEvent e)
	{
		if (e.getAuthor().isBot())
			return;

		System.out.println("Received message.");

		Message message = e.getMessage();
		String content = message.getContentRaw();

		System.out.println("  Message: " + content);
		
		if (content.matches("[!][a-z]+.*"))
		{
			String[] args = content.split("[ ]");
			
			String commandName = args[0];
			
			if (args.length == 1)
				args = new String[0];
			else
				args = Arrays.copyOfRange(args, 1, args.length);

			System.out.println("  Command: " + commandName);
			
			DiscordChannelBridge channel = new DiscordChannelBridge(e.getChannel());
			SentCommand com = new SentCommand(channel, commandName, args);
			handle(com);
		}
	}
}
