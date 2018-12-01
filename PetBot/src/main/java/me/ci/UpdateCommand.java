package me.ci;

import java.io.File;

public class UpdateCommand implements Command
{
	@Override
	public String getName()
	{
		return "!update";
	}

	@Override
	public void run(SentCommand com)
	{
		if (com.getArguments().length == 0)
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
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Unable to parse input parameters.\n");
		sb.append("Available commands:\n```\n");
		
		sb.append("!update\n (with file attachment)");
		
		sb.append("```");
		
		com.sendMessage(sb.toString());
	}
}
