package me.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main
{
	public static void main(String[] args) throws LoginException, InterruptedException, IOException
	{
		JDA jda = new JDABuilder(getToken())
	            .addEventListener(new EventHandler())
	            .build();

        // optionally block until JDA is ready
        jda.awaitReady();
	}
	
	private static String getToken() throws IOException
	{
		File file = new File("C:\\Users\\TheDudeFromCI\\Documents\\pet_bot_token.txt");
		
		try (BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			return in.readLine();
		}
	}
}
