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
		File imageFolder = new File("C:\\Users\\TheDudeFromCI\\Documents\\PetBot\\pictures");
		File[] files = imageFolder.listFiles();
		
		int randomIndex = (int)(Math.random() * files.length);
		
		System.out.println("Uploading file: " + files[randomIndex]);
		
		com.sendMessage("Here's a random animal picture for you!");
		com.uploadFile(files[randomIndex]);
	}
}
