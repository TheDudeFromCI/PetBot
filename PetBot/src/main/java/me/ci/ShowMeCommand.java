package me.ci;

import java.io.File;

public class ShowMeCommand implements Command
{
	@Override
	public String getName()
	{
		return "!showme";
	}

	@Override
	public void run(SentCommand com)
	{
		String dir = System.getProperty("user.dir");
		File imageFolder = new File(dir, "pictures");

		if (com.getArguments().length == 0)
		{
			File[] files = imageFolder.listFiles();
			
			int randomIndex = (int)(Math.random() * files.length);
			
			System.out.println("Uploading file: " + files[randomIndex]);
			
			com.sendMessage("Here's a random animal picture for you!");
			com.uploadFile(files[randomIndex]);
			return;
		}
		
		if (com.getArguments().length == 1)
		{
			if (com.getArguments()[0].equals("list"))
			{
				File[] files = imageFolder.listFiles();
				
				StringBuilder sb = new StringBuilder();
				sb.append("Currently uploaded files:\n```");
				
				for (int i = 0; i < files.length; i++)
					sb.append(files[i].getName()).append("\n");
				
				sb.append("```");
				
				com.sendMessage(sb.toString());
				return;
			}
		}
		
		com.sendMessage("Unknown arguments. Please use as:\n```\n!showme\n!showme list\n```");
	}
}
