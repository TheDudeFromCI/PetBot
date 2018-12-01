package me.ci;

import net.dv8tion.jda.core.entities.TextChannel;

public class SentCommand
{
	private TextChannel _channel;
	private String _command;
	private String[] _args;
	
	public SentCommand(TextChannel channel, String command, String[] args)
	{
		_channel = channel;
		_command = command;
		_args = args;
	}
	
	public void sendMessage(String message)
	{
		_channel.sendMessage(message);
	}
	
	public String getCommand()
	{
		return _command;
	}
	
	public String[] getArguments()
	{
		return _args;
	}
}
