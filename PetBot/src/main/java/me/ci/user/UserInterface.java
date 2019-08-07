package me.ci.user;

import java.io.File;

public interface UserInterface
{
	void init();

	boolean supportsTextOutput();

	boolean supportFileOutput();

	void sendMessage(String text);

	void sendFile(File file);

	void dispose();
}
