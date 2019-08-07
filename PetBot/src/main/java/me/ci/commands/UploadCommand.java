package me.ci.commands;

import java.io.File;
import java.util.List;
import me.ci.user.User;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.whg.awgenshell.arg.ArgumentValue;
import net.whg.awgenshell.exec.CommandHandler;
import net.whg.awgenshell.exec.ShellEnvironment;
import net.whg.awgenshell.util.CommandResult;

public class UploadCommand implements CommandHandler
{
	private static final String[] ALIASES = {};

	@Override
	public String getName()
	{
		return "upload";
	}

	@Override
	public CommandResult execute(ShellEnvironment env, ArgumentValue[] args)
	{
		User user = (User) env.getCommandSender();

		if (args.length != 0)
		{
			user.sendMessage("Unknown number of arguments!");
			return CommandResult.ERROR;
		}

		List<Attachment> att = user.getAttachments();

		if (att.size() == 0)
		{
			user.sendMessage("No images attached!");
			return CommandResult.ERROR;
		}

		String dir = System.getProperty("user.dir");
		File pictures = new File(dir, "pictures");

		for (int i = 0; i < att.size(); i++)
		{
			File file = new File(pictures, att.get(i).getFileName());
			if (file.exists())
			{
				user.sendMessage("File " + att.get(i).getFileName() + " already in database.");
				continue;
			}

			try
			{
				att.get(i).download(file);
			}
			catch (Exception exception)
			{
				user.sendError("Failed to upload image!", exception);
			}
		}

		user.sendMessage("Uploaded attachments.");
		return CommandResult.SUCCESS;
	}

	@Override
	public String[] getAliases()
	{
		return ALIASES;
	}
}
