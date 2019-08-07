package me.ci.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.ci.commands.PetBotModule;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.whg.awgenshell.exec.ShellEnvironment;

public class ConsoleUser implements User
{
	private ShellEnvironment shell = new ShellEnvironment(this);
	private ArrayList<Attachment> attachments = new ArrayList<>();

	public ConsoleUser()
	{
		shell.loadModule(PetBotModule.load());
	}

	@Override
	public String getName()
	{
		return "console";
	}

	@Override
	public void println(String message)
	{
		System.out.println(message);
	}

	@Override
	public void printImage(File file)
	{
		System.out.println("Recieved: " + file.getName());
	}

	@Override
	public void printError(String message, Throwable error)
	{
		System.err.println(message);
		error.printStackTrace();
	}

	@Override
	public void runCommand(String cmd)
	{
		shell.runCommand(cmd);
	}

	@Override
	public List<Attachment> getAttachments()
	{
		return attachments;
	}
}
