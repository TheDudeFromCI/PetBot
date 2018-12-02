package me.ci.commands;

public abstract class BasicCommandBase implements Command, Subcommand
{
	private Subcommand[] _subcommands = {
			this
	};
	
	@Override
	public int getArgumentCount()
	{
		return 0;
	}

	@Override
	public String getArgumentFormat(int index)
	{
		return null;
	}

	@Override
	public String getArgumentDisplay(int index)
	{
		return null;
	}

	@Override
	public Subcommand[] getSubcommands()
	{
		return _subcommands;
	}
}
