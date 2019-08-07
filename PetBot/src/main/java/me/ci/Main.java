package me.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import me.ci.user.ConsoleUser;
import me.ci.user.DiscordInterface;
import me.ci.user.UserInterface;

public class Main
{
	public static final Object LOCK = new Object();

	public static void main(String[] args) throws IOException
	{
		try
		{
			UserInterface ui = buildUserInterface(args);
			ui.init();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		System.out.println("Admin Console Active:");

		ConsoleUser console = new ConsoleUser();
		try (Scanner scan = new Scanner(System.in))
		{
			while (true)
			{
				String cmd = scan.nextLine();

				synchronized (LOCK)
				{
					console.runCommand(cmd);
				}
			}
		}
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
