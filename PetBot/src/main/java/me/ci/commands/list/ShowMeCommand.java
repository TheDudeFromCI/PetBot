package me.ci.commands.list;

import java.io.File;

import me.ci.commands.Command;
import me.ci.commands.Subcommand;
import me.ci.user.UserAction;

public class ShowMeCommand implements Command
{
	private Subcommand[] _subcommands = {
			new ShowMeSubCommand(),
			new ShowMeList(),
			new ShowMePicture(),
			new ShowMeRemove(),
			new ShowMeRename()
	};

	@Override
	public String getName()
	{
		return "!showme";
	}

	@Override
	public Subcommand[] getSubcommands()
	{
		return _subcommands;
	}
	
	private class ShowMeSubCommand implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 0;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			return null;
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			return null;
		}

		@Override
		public String getDescription()
		{
			return "Displays a random picture from the database.";
		}

		@Override
		public void run(UserAction com)
		{
			String dir = System.getProperty("user.dir");
			File imageFolder = new File(dir, "pictures");
			File[] files = imageFolder.listFiles();
			
			int randomIndex = (int)(Math.random() * files.length);
			
			System.out.println("Uploading file: " + files[randomIndex]);
			
			com.getUser().sendMessage("Here's a random animal picture for you!");
			com.getUser().sendFile(files[randomIndex]);
		}
	}
	
	private class ShowMeList implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 1;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			return "list";
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			return "list";
		}

		@Override
		public String getDescription()
		{
			return "Shows a list of all image files in the database.";
		}

		@Override
		public void run(UserAction com)
		{
			String dir = System.getProperty("user.dir");
			File imageFolder = new File(dir, "pictures");
			File[] files = imageFolder.listFiles();
			
			StringBuilder sb = new StringBuilder();
			sb.append("Currently uploaded files:");
			sb.append(" (").append(files.length).append(")");
			sb.append("\n```");
			
			for (int i = 0; i < files.length; i++)
				sb.append(files[i].getName()).append("\n");
			
			sb.append("```");
			
			com.getUser().sendMessage(sb.toString());
		}
		
	}

	private class ShowMePicture implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 1;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			return "*";
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			return "[file name]";
		}

		@Override
		public String getDescription()
		{
			return "Shows a specific image from the database.";
		}

		@Override
		public void run(UserAction com)
		{
			String dir = System.getProperty("user.dir");
			File imageFolder = new File(dir, "pictures");
			File[] files = imageFolder.listFiles();
			String toShow = com.getCommandArgs()[0];
			
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].getName().equals(toShow))
				{
					com.getUser().sendMessage("Here you go!");
					com.getUser().sendFile(files[i]);
					return;
				}
			}

			com.getUser().sendMessage("Image not found!");
		}
		
	}
	
	private class ShowMeRemove implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 2;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			if (index == 0)
				return "remove";
			return "*";
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			if (index == 0)
				return "remove";
			return "[image file]";
		}

		@Override
		public String getDescription()
		{
			return "Removes an image from the database.";
		}

		@Override
		public void run(UserAction com)
		{
			String dir = System.getProperty("user.dir");
			File imageFolder = new File(dir, "pictures");
			File[] files = imageFolder.listFiles();
			String toDelete = com.getCommandArgs()[1];
			
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].getName().equals(toDelete))
				{
					files[i].delete();
					
					com.getUser().sendMessage("Image removed from database.");
					return;
				}
			}
			
			com.getUser().sendMessage("Image not found!");
		}
	}
	
	private class ShowMeRename implements Subcommand
	{
		@Override
		public int getArgumentCount()
		{
			return 3;
		}

		@Override
		public String getArgumentFormat(int index)
		{
			if (index == 0)
				return "rename";
			return "*";
		}

		@Override
		public String getArgumentDisplay(int index)
		{
			if (index == 0)
				return "rename";
			if (index == 1)
				return "[file name]";
			return "[new name]";
		}

		@Override
		public String getDescription()
		{
			return "Renames a file in the image database.";
		}

		@Override
		public void run(UserAction com)
		{
			String dir = System.getProperty("user.dir");
			File imageFolder = new File(dir, "pictures");
			File[] files = imageFolder.listFiles();
			String toRename = com.getCommandArgs()[1];
			String name = com.getCommandArgs()[2];
			
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].getName().equals(toRename))
				{
					File newName = new File(files[i].getParentFile(), name);
					files[i].renameTo(newName);
					
					com.getUser().sendMessage("Image has been renamed.");
					return;
				}
			}
			
			com.getUser().sendMessage("Image not found!");
		}
	}
}
