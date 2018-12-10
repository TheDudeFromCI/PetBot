package me.ci.commands.list;

import java.io.File;
import me.ci.commands.BasicCommandBase;
import me.ci.user.UserAction;

public class UploadCommand extends BasicCommandBase
{
	@Override
	public String getName()
	{
		return "!upload";
	}

	@Override
	public void run(UserAction com)
	{
		if (com.getAttachments().size() == 0)
		{
			com.getUser().sendMessage("No images attached!");
			return;
		}
		
		String dir = System.getProperty("user.dir");
		File pictures = new File(dir, "pictures");
		for (int i = 0; i < com.getAttachments().size(); i++)
		{
			File file = new File(pictures, com.getAttachments().get(i).getFileName());
			
			if (file.exists())
			{
				com.getUser().sendMessage("File " + com.getAttachments().get(i).getFileName() + " already in database.");
				continue;
			}

			try
			{
				com.getAttachments().get(i).download(file);
			}
			catch(Exception exception)
			{
				com.getUser().sendError("Failed to upload image!", exception);
			}
		}

		com.getUser().sendMessage("Uploaded attachments.");
	}

	@Override
	public String getDescription()
	{
		return "Uploads new images to the picture database.";
	}
}
