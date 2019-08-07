package me.ci.commands;

import net.whg.awgenshell.arg.ArgumentValue;
import net.whg.awgenshell.exec.CommandHandler;
import net.whg.awgenshell.exec.CommandSender;
import net.whg.awgenshell.exec.ShellEnvironment;
import net.whg.awgenshell.util.CommandResult;

public class RestartCommand implements CommandHandler
{
	private static final String[] ALIASES = {"reboot"};

	@Override
	public String getName()
	{
		return "restart";
	}

	@Override
	public CommandResult execute(ShellEnvironment env, ArgumentValue[] args)
	{
		CommandSender sender = env.getCommandSender();

		if (args.length != 0)
		{
			sender.println("Unknown number of arguments!");
			return CommandResult.ERROR;
		}

		System.exit(0);
		return CommandResult.SUCCESS;
	}

	@Override
	public String[] getAliases()
	{
		return ALIASES;
	}
}
