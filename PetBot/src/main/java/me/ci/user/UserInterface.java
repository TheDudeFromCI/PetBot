package me.ci.user;

import java.io.File;
import me.ci.commands.CommandHandler;

public interface UserInterface
{
	public void init();
	
	public boolean supportsTextOutput();
	
	public boolean supportFileOutput();
	
	public void sendMessage(String text);
	
	public void sendFile(File file);
	
	public void dispose();
	
	public void setCommandHandler(CommandHandler commandHandler);
}
