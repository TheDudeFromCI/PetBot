package me.ci.commands.list;

import java.io.File;

import me.ci.commands.BasicCommandBase;
import me.ci.user.UserAction;

public class UpdateCommand extends BasicCommandBase
{
	@Override
	public String getName()
	{
		return "!update";
	}

	@Override
	public void run(UserAction com)
	{
		if (com.getAttachments().size() == 0)
		{
			com.getUser().sendMessage("No files attached!");
			return;
		}

		if (com.getAttachments().size() != 1)
		{
			com.getUser().sendMessage("Too mant files attached!");
			return;
		}
		
		String dir = System.getProperty("user.dir");
		File directory = new File(dir);
		File file = new File(directory, "petbot_new.jar");
		
		com.getAttachments().get(0).download(file);

		com.getUser().sendMessage("Downloaded update. Please use\n```!reload\n```\n to apply changes.");
	}

	@Override
	public String getDescription()
	{
		return "Uploads a new version of PetBot to the server update queue.";
	}
}
