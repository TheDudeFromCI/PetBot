package me.ci.commands.list;

import java.io.File;

import me.ci.commands.Command;
import me.ci.commands.CommandEvent;

public class ShowMeCommand implements Command
{
	@Override
	public String getName()
	{
		return "!showme";
	}

	@Override
	public void run(CommandEvent com)
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
			
			if (com.getArguments()[0].equals("help"))
			{
				StringBuilder sb = new StringBuilder();
				sb.append("Available commands:\n```\n");
				
				sb.append("!showme\n");
				sb.append("!showme list\n");
				sb.append("!showme help\n");
				sb.append("!showme [image]\n");
				sb.append("!showme remove [image]\n");
				sb.append("!showme rename [image] [new name]\n");
				
				sb.append("```");
				
				com.sendMessage(sb.toString());
				return;
			}

			File[] files = imageFolder.listFiles();
			String toShow = com.getArguments()[0];
			
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].getName().equals(toShow))
				{
					com.sendMessage("Here you go!");
					com.uploadFile(files[i]);
					return;
				}
			}

			com.sendMessage("Image not found!");
			return;
		}
		
		if (com.getArguments().length == 2)
		{
			if (com.getArguments()[0].equals("remove"))
			{
				File[] files = imageFolder.listFiles();
				String toDelete = com.getArguments()[1];
				
				for (int i = 0; i < files.length; i++)
				{
					if (files[i].getName().equals(toDelete))
					{
						files[i].delete();
						
						com.sendMessage("Image removed from database.");
						return;
					}
				}
				
				com.sendMessage("Image not found!");
				return;
			}
		}
		
		if (com.getArguments().length == 3)
		{
			if (com.getArguments()[0].equals("rename"))
			{
				File[] files = imageFolder.listFiles();
				String toRename = com.getArguments()[1];
				String name = com.getArguments()[2];
				
				for (int i = 0; i < files.length; i++)
				{
					if (files[i].getName().equals(toRename))
					{
						File newName = new File(files[i].getParentFile(), name);
						files[i].renameTo(newName);
						
						com.sendMessage("Image has been renamed.");
						return;
					}
				}
				
				com.sendMessage("Image not found!");
				return;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Unable to parse input parameters.\n");
		sb.append("Available commands:\n```\n");
		
		sb.append("!showme\n");
		sb.append("!showme list\n");
		sb.append("!showme help\n");
		sb.append("!showme [image]\n");
		sb.append("!showme remove [image]\n");
		sb.append("!showme rename [image] [new name]\n");
		
		sb.append("```");
		
		com.sendMessage(sb.toString());
	}
}
