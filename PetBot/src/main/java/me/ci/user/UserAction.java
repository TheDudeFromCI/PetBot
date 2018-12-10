package me.ci.user;

import java.util.ArrayList;
import me.ci.commands.ParsedCommand;

public class UserAction
{
	private String _commandName;
	private String[] _commandArgs;
	private User _user;
	private ArrayList<FileUpload> _attachments;
	
	public UserAction(ParsedCommand com, User user, ArrayList<FileUpload> attachments)
	{
		_commandName = com.getCommandName();
		_commandArgs = com.getCommandArgs();
		_user = user;
		_attachments = attachments;
	}

	public String getCommandName()
	{
		return _commandName;
	}
	
	public String[] getCommandArgs()
	{
		return _commandArgs;
	}
	
	public User getUser()
	{
		return _user;
	}
	
	public ArrayList<FileUpload> getAttachments()
	{
		return _attachments;
	}
}
