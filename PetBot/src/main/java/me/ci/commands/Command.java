package me.ci.commands;

public interface Command
{
	public String getName();
	
	public void run(CommandEvent com);
}
