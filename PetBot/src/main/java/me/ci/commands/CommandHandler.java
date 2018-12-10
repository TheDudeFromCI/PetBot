package me.ci.commands;

import java.util.ArrayList;
import me.ci.user.UserAction;

public class CommandHandler
{
	private ArrayList<Command> _commands = new ArrayList<>();
	
	public void registerCommand(Command command)
	{
		_commands.add(command);
	}
	
	public void handle(UserAction action)
	{
		for (Command c : _commands)
			if (c.getName().equals(action.getCommandName()))
			{
				findSubcommand:
				for (Subcommand sub : c.getSubcommands())
				{
					if (action.getCommandArgs().length != sub.getArgumentCount())
						continue;
					
					for (int i = 0; i < sub.getArgumentCount(); i++)
					{
						if (sub.getArgumentFormat(i).equals("*"))
							continue;
						if (!sub.getArgumentFormat(i).equals(action.getCommandArgs()[i]))
							continue findSubcommand;
					}
					
					sub.run(action);
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
				action.getUser().sendMessage(sb.toString());
				return;
			}
		action.getUser().sendMessage("Command not found!");
	}
	
	public ArrayList<Command> getCommands()
	{
		return _commands;
	}
}
