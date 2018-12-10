package me.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import me.ci.commands.CommandHandler;
import me.ci.commands.list.HelpCommand;
import me.ci.commands.list.ReloadCommand;
import me.ci.commands.list.ShowMeCommand;
import me.ci.commands.list.UpdateCommand;
import me.ci.commands.list.UploadCommand;
import me.ci.user.DiscordInterface;
import me.ci.user.UserInterface;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		UserInterface ui = buildUserInterface(args);
		ui.setCommandHandler(buildCommandHandler());
		ui.init();
	}
	
	private static CommandHandler buildCommandHandler()
	{
		CommandHandler commandHandler = new CommandHandler();

		commandHandler.registerCommand(new ShowMeCommand());
		commandHandler.registerCommand(new UploadCommand());
		commandHandler.registerCommand(new ReloadCommand());
		commandHandler.registerCommand(new UpdateCommand());
		commandHandler.registerCommand(new HelpCommand(commandHandler));
		
		return commandHandler;
	}
	
	private static UserInterface buildUserInterface(String[] args) throws IOException
	{
		String token = getToken();
		return new DiscordInterface(token);
	}

	private static String getToken() throws IOException
	{
		String dir = System.getProperty("user.dir");
		File file = new File(dir, "token.txt");

		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			return in.readLine();
		}
	}
}
