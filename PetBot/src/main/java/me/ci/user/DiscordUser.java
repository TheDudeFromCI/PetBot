package me.ci.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.ci.commands.PetBotModule;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.whg.awgenshell.exec.ShellEnvironment;

public class DiscordUser implements User
{
	private ShellEnvironment shell;
	private String name;
	private MessageChannel lastChannel;
	private List<Attachment> attachments = new ArrayList<>();
	private StringBuilder messageBuf = new StringBuilder();

	public DiscordUser(net.dv8tion.jda.core.entities.User author, MessageChannel channel)
	{
		name = author.getName();
		lastChannel = channel;

		shell = new ShellEnvironment(this);
		shell.loadModule(PetBotModule.load());
	}

	@Override
	public String getName()
	{
		return name;
	}

	public void flushMessages()
	{
		if (messageBuf.length() == 0)
			return;

		lastChannel.sendMessage(messageBuf.toString()).queue();
		messageBuf.setLength(0);
	}

	@Override
	public void println(String message)
	{
		messageBuf.append(message).append('\n');

		if (messageBuf.length() > 1000)
			flushMessages();
	}

	@Override
	public void runCommand(String command)
	{
		shell.runCommandNoisy(command);
	}

	public void setChannel(MessageChannel channel)
	{
		lastChannel = channel;
	}

	public void setAttachments(List<Attachment> attachments)
	{
		this.attachments.clear();
		for (Attachment a : attachments)
			this.attachments.add(a);
	}

	@Override
	public List<Attachment> getAttachments()
	{
		return attachments;
	}

	@Override
	public void printImage(File file)
	{
		lastChannel.sendFile(file).queue();
	}

	@Override
	public void printError(String message, Throwable error)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(message).append("\n");
		sb.append("```\n");

		sb.append("Error: ").append(error.getMessage()).append("\n");

		for (StackTraceElement e : error.getStackTrace())
			sb.append("  at ").append(e.getFileName()).append(".").append(e.getMethodName()).append("(line ")
					.append(e.getLineNumber()).append(")\n");

		sb.append("```");
		println(sb.toString());
	}

	@Override
	public String getEmoji(String name)
	{
		List<Emote> emotes = lastChannel.getJDA().getEmotesByName(name, true);

		if (emotes.isEmpty())
			return null;

		return emotes.get(0).getAsMention();
	}
}
