package me.ci.user;

import java.io.File;
import net.dv8tion.jda.core.entities.MessageChannel;

public class User
{
	private String _name;
	private String _id;
	private String _mention;
	private MessageChannel _lastChannel;
	
	public User(net.dv8tion.jda.core.entities.User author, MessageChannel channel)
	{
		_name = author.getName();
		_id = author.getId();
		_mention = author.getAsMention();
		_lastChannel = channel;
	}
	public String getName()
	{
		return _name;
	}
	
	public String getID()
	{
		return _id;
	}
	
	public String getMentionTag()
	{
		return _mention;
	}
	
	public void sendMessage(String text)
	{
		_lastChannel.sendMessage(text).queue();
	}
	
	public void sendFile(File file)
	{
		_lastChannel.sendFile(file).queue();
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
