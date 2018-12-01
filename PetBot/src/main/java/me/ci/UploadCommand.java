package me.ci;

import java.io.File;
import java.util.UUID;

public class UploadCommand implements Command
{
	@Override
	public String getName()
	{
		return "!upload";
	}

	@Override
	public void run(SentCommand com)
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
			File file = new File(pictures, UUID.randomUUID().toString());
			com.downloadFile(file, i);
		}
	}
}
