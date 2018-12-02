package me.ci.commands.list;

import java.io.File;

import me.ci.commands.BasicCommandBase;
import me.ci.commands.CommandEvent;

public class UpdateCommand extends BasicCommandBase
{
	@Override
	public String getName()
	{
		return "!update";
	}

	@Override
	public void run(CommandEvent com)
	{
		if (com.getAttachedFileCount() == 0)
		{
			com.sendMessage("No files attached!");
			return;
		}

		if (com.getAttachedFileCount() != 1)
		{
			com.sendMessage("Too mant files attached!");
			return;
		}
		
		String dir = System.getProperty("user.dir");
		File directory = new File(dir);
		File file = new File(directory, "petbot_new.jar");
		com.downloadFile(file, 0);

		com.sendMessage("Downloaded update. Please use\n```!reload\n```\n to apply changes.");
	}

	@Override
	public String getDescription()
	{
		return "Uploads a new version of PetBot to the server update queue.";
	}
}
