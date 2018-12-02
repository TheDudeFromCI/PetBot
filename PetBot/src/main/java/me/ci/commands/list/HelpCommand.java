package me.ci.commands.list;

import me.ci.commands.Command;
import me.ci.commands.CommandEvent;
import me.ci.commands.CommandHandler;
import me.ci.commands.Subcommand;

public class HelpCommand implements Command, Subcommand
{
	private Subcommand[] _subcommands = {
			this
	};
	private CommandHandler _commandHandler;
	
	public HelpCommand(CommandHandler commandHandler)
	{
		_commandHandler = commandHandler;
	}

	@Override
	public String getName()
	{
		return "!help";
	}

	@Override
	public Subcommand[] getSubcommands()
	{
		return _subcommands;
	}

	@Override
	public int getArgumentCount()
	{
		return 1;
	}

	@Override
	public String getArgumentFormat(int index)
	{
		return "*";
	}

	@Override
	public String getArgumentDisplay(int index)
	{
		return "[command]";
	}

	@Override
	public String getDescription()
	{
		return "Displays help content for the requested command.";
	}

	@Override
	public void run(CommandEvent com)
	{
		Command command = null;

		String commandName = com.getArguments()[0];
		for (Command c : _commandHandler.getCommands())
		{
			if (c.getName().substring(1).equals(commandName))
			{
				command = c;
				break;
			}
		}
		
		if (command == null)
		{
			com.sendMessage("Command not found!");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Available commands:\n```\n");
		
		for (Subcommand sub : command.getSubcommands())
		{
			sb.append(command.getName()).append(' ');
			
			for (int i = 0; i < sub.getArgumentCount(); i++)
				sb.append(sub.getArgumentDisplay(i)).append(' ');
			
			sb.append("\n");
		}
		
		sb.append("```");
		com.sendMessage(sb.toString());
	}
}
