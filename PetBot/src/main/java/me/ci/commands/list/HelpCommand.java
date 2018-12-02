package me.ci.commands.list;

import me.ci.commands.Command;
import me.ci.commands.CommandEvent;
import me.ci.commands.CommandHandler;
import me.ci.commands.Subcommand;

public class HelpCommand implements Command
{
	private Subcommand[] _subcommands = {
			new HelpListSubcommand(),
			new HelpCommandSubcommand()
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

	private class HelpListSubcommand implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 1;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			return "list";
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			return "list";
		}

		@Override
		public String getDescription()
		{
			return "Returns a list of all available commands.";
		}

		@Override
		public void run(CommandEvent com)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Available commands:\n```\n");

			for (Command c : _commandHandler.getCommands())
				sb.append("!").append(c.getName());
			
			sb.append("```");
			
			com.sendMessage(sb.toString());
		}
	}

	private class HelpCommandSubcommand implements Subcommand
	{
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
			return "Returns all subcommands and a description of each.";
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
			sb.append("Available subcommands:\n```\n");
			
			for (Subcommand sub : command.getSubcommands())
			{
				sb.append(command.getName()).append(' ');
				
				for (int i = 0; i < sub.getArgumentCount(); i++)
					sb.append(sub.getArgumentDisplay(i)).append(' ');
				
				sb.append("\n");
				
				sb.append("  ").append(sub.getDescription()).append("\n");
			}
			
			sb.append("```");
			com.sendMessage(sb.toString());
		}
	}
}
