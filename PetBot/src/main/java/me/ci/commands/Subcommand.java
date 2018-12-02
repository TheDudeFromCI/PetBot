package me.ci.commands;

public interface Subcommand
{
	public int getArgumentCount();
	
	public String getArgumentFormat(int index);
	
	public String getArgumentDisplay(int index);
	
	public String getDescription();

	public void run(CommandEvent com);
}
