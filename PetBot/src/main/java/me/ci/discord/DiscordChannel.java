package me.ci.discord;

import java.io.File;

public interface DiscordChannel
{
	public void sendMessage(String message);

	public void sendFile(File file);
}
