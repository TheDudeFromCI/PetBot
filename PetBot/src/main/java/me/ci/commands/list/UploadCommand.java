package me.ci.commands.list;

import java.io.File;

import me.ci.commands.BasicCommandBase;
import me.ci.commands.CommandEvent;

public class UploadCommand extends BasicCommandBase
{
	@Override
	public String getName()
	{
		return "!upload";
	}

	@Override
	public void run(CommandEvent com)
	{
		if (com.getAttachedFileCount() == 0)
		{
			com.sendMessage("No images attached!");
			return;
		}
		
		String dir = System.getProperty("user.dir");
		File pictures = new File(dir, "pictures");
		for (int i = 0; i < com.getAttachedFileCount(); i++)
		{
			File file = new File(pictures, com.getAttachmentFileName(i));
			com.downloadFile(file, i);
		}

		com.sendMessage("Uploaded attachments.");
	}

	@Override
	public String getDescription()
	{
		return "Uploads new images to the picture database.";
	}
}