package me.ci;

public class ReloadCommand implements Command
{
	@Override
	public String getName()
	{
		return "!reload";
	}

	@Override
	public void run(SentCommand com)
	{
		com.sendMessage("Reloading PetBot.");
		System.exit(0);
	}
}
