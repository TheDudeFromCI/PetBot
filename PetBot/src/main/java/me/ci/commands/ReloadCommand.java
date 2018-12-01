package me.ci.commands;

public class ReloadCommand implements Command
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
}
