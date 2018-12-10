package me.ci.commands;

public class ParsedCommand
{
	private String _command;
	private String[] _args;
	
	public ParsedCommand(String command, String[] args)
	{
		_command = command;
		_args = args;
	}
	
	public String getCommandName()
	{
		return _command;
	}
	
	public String[] getCommandArgs()
	{
		return _args;
	}
}
