package me.ci.user;

import java.io.File;
import java.util.List;
import me.ci.commands.PetBotModule;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.whg.awgenshell.exec.CommandSender;
import net.whg.awgenshell.exec.ShellEnvironment;

public class User implements CommandSender
{
	private ShellEnvironment shell;
	private String name;
	private String id;
	private String mention;
	private MessageChannel lastChannel;
	private List<Attachment> attachments;

	public User(net.dv8tion.jda.core.entities.User author, MessageChannel channel)
	{
		name = author.getName();
		id = author.getId();
		mention = author.getAsMention();
		lastChannel = channel;

		shell = new ShellEnvironment(this);
		shell.loadModule(PetBotModule.load());
	}

	@Override
	public String getName()
	{
		return name;
	}

	public String getID()
	{
		return id;
	}

	public String getMentionTag()
	{
		return mention;
	}

	public void sendMessage(String text)
	{
		lastChannel.sendMessage(text).queue();
	}

	public void sendFile(File file)
	{
		lastChannel.sendFile(file).queue();
	}

	public void sendError(String message, Throwable error)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(message).append("\n");
		sb.append("```\n");

		sb.append("Error: ").append(error.getMessage()).append("\n");

		for (StackTraceElement e : error.getStackTrace())
			sb.append("  at ").append(e.getFileName()).append(".").append(e.getMethodName()).append("(line ")
					.append(e.getLineNumber()).append(")\n");

		sb.append("```");
		sendMessage(sb.toString());
	}

	@Override
	public void println(String message)
	{
		sendMessage(message);
	}

	public void runCommand(String command)
	{
		shell.runCommand(command);
	}

	public void setChannel(MessageChannel channel)
	{
		lastChannel = channel;
	}

	public void setAttachments(List<Attachment> attachments)
	{
		this.attachments = attachments;
	}

	public List<Attachment> getAttachments()
	{
		return attachments;
	}
}
