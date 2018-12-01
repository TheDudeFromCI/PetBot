package me.ci;

public interface Command
{
	public String getName();
	
	public void run(SentCommand com);
}
