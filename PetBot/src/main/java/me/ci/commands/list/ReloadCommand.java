package me.ci.commands.list;

import me.ci.commands.BasicCommandBase;
import me.ci.commands.CommandEvent;

public class ReloadCommand extends BasicCommandBase
{
	@Override
	public String getName()
	{
		return "!reload";
	}

	@Override
	public void run(CommandEvent com)
	{
		com.sendMessage("Reloading PetBot.");
		System.exit(0);
	}

	@Override
	public String getDescription()
	{
		return "Reloads the bot and installs any available updates.";
	}
}
