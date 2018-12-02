package me.ci.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.ci.DiscordBridge.DiscordChannelBridge;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHandler
{
	private ArrayList<Command> _commands = new ArrayList<>();
	
	public void registerCommand(Command command)
	{
		_commands.add(command);
	}
	
	public void handle(CommandEvent com)
	{
		for (Command c : _commands)
			if (c.getName().equals(com.getCommand()))
			{
				findSubcommand:
				for (Subcommand sub : c.getSubcommands())
				{
					if (com.getArguments().length != sub.getArgumentCount())
						continue;
					
					for (int i = 0; i < sub.getArgumentCount(); i++)
					{
						if (sub.getArgumentFormat(i).equals("*"))
							continue;
						if (!sub.getArgumentFormat(i).equals(com.getArguments()[i]))
							continue findSubcommand;
					}
					
					sub.run(com);
					return;
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append("Unable to parse input parameters.\n");
				sb.append("Available commands:\n```\n");
				
				for (Subcommand sub : c.getSubcommands())
				{
					sb.append(c.getName()).append(' ');
					
					for (int i = 0; i < sub.getArgumentCount(); i++)
						sb.append(sub.getArgumentDisplay(i)).append(' ');
					
					sb.append("\n");
				}
				
				sb.append("```");
				com.sendMessage(sb.toString());
				return;
			}
		com.sendMessage("Command not found!");
	}
	
	public ArrayList<Command> getCommands()
	{
		return _commands;
	}
	
	public void handle(MessageReceivedEvent e)
	{
		if (e.getAuthor().isBot())
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

			List<Attachment> attachments = message.getAttachments();
			DiscordChannelBridge channel = new DiscordChannelBridge(e.getChannel(), attachments);

			CommandEvent com = new CommandEvent(channel, commandName, args);
			handle(com);
		}
	}
}
