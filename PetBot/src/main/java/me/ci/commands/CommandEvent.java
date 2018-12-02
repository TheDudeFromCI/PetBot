package me.ci.commands;

import java.io.File;

import me.ci.DiscordBridge.DiscordChannelBridge;

public class CommandEvent
{
	private DiscordChannelBridge _channel;
	private String _command;
	private String[] _args;
	
	public CommandEvent(DiscordChannelBridge channel, String command, String[] args)
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
	
	public void uploadFile(File file)
	{
		_channel.sendFile(file);
	}
	
	public int getAttachedFileCount()
	{
		return _channel.getAttachmentCount();
	}
	
	public void downloadFile(File file, int index)
	{
		_channel.downloadAttachment(file, index);
	}
	
	public String getAttachmentFileName(int index)
	{
		return _channel.getAttachmentName(index);
	}
	
	public void sendError(String message, Throwable error)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(message).append("\n");
		sb.append("```\n");
		
		sb.append("Error: ").append(error.getMessage()).append("\n");
		
		for (StackTraceElement e : error.getStackTrace())
			sb.append("  at ").append(e.getFileName()).append(".").append(e.getMethodName())
			.append("(line ").append(e.getLineNumber()).append(")\n");
		
		sb.append("```");
		sendMessage(sb.toString());
	}
}
