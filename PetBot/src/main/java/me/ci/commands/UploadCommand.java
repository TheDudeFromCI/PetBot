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
			user.println("Unknown number of arguments!");
			return CommandResult.ERROR;
		}

		List<Attachment> att = user.getAttachments();

		if (att.size() == 0)
		{
			user.println("No images attached!");
			return CommandResult.ERROR;
		}

		String dir = System.getProperty("user.dir");
		File pictures = new File(dir, "pictures");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < att.size(); i++)
		{
			File file = new File(pictures, att.get(i).getFileName());
			if (file.exists())
			{
				user.println("File " + att.get(i).getFileName() + " already in database.");
				continue;
			}

			try
			{
				att.get(i).download(file);
				sb.append(file.getName()).append('\n');
			}
			catch (Exception exception)
			{
				user.printError("Failed to upload image!", exception);
			}
		}

		user.println("Uploaded attachments.");
		return new CommandResult(sb.toString(), true, true);
	}

	@Override
	public String[] getAliases()
	{
		return ALIASES;
	}
}
