package me.ci.commands;

import me.ci.user.UserAction;

public interface Subcommand
{
	public int getArgumentCount();
	
	public String getArgumentFormat(int index);
	
	public String getArgumentDisplay(int index);
	
	public String getDescription();

	public void run(UserAction com);
}
