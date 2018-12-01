package me.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.security.auth.login.LoginException;

public class Main
{
	public static void main(String[] args) throws LoginException, InterruptedException, IOException
	{
		CommandHandler commandHandler = new CommandHandler();
		commandHandler.registerCommand(new ShowMeCommand());
	
		String token = getToken();
		EventHandler eventHandler = new EventHandler(commandHandler);

		DiscordAPI discord;
		
		if (args.length > 0 && args[0].equals("noconnect"))
			discord = null;
		else
			discord = new DiscordBridge();
		
		discord.connect(token, eventHandler);
	}
	
	private static String getToken() throws IOException
	{
		File file = new File("C:\\Users\\TheDudeFromCI\\Documents\\PetBot\\pet_bot_token.txt");
		
		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			return in.readLine();
		}
	}
}
