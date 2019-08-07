package me.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import me.ci.user.DiscordInterface;
import me.ci.user.UserInterface;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		UserInterface ui = buildUserInterface(args);
		ui.init();
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
