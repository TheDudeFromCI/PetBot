package me.ci;

import java.io.File;

import net.dv8tion.jda.core.entities.MessageChannel;

public class SentCommand
{
	private MessageChannel _channel;
	private String _command;
	private String[] _args;
	
	public SentCommand(MessageChannel channel, String command, String[] args)
	{
		_channel = channel;
		_command = command;
		_args = args;
	}
	
	public void sendMessage(String message)
	{
		_channel.sendMessage(message).queue();;
	}
	
	public String getCommand()
	{
		return _command;
	}
	
	public String[] getArguments()
	{
		return _args;
	}
	
	public void uploadFile(File file)
	{
		_channel.sendFile(file).queue();
	}
}
