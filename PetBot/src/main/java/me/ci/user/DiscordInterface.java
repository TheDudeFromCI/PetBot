package me.ci.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.ci.commands.CommandHandler;
import me.ci.commands.CommandUtils;
import me.ci.commands.ParsedCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class DiscordInterface implements UserInterface, EventListener
{
	private String _token;
	private boolean _connected;
	private CommandHandler _commandHandler;
	private MessageChannel _activeChannel;
	private JDA _jda;
	
	public DiscordInterface(String token)
	{
		_token = token;
	}

	@Override
	public boolean supportsTextOutput()
	{
		return _activeChannel != null;
	}

	@Override
	public boolean supportFileOutput()
	{
		return _activeChannel != null;
	}

	@Override
	public void sendMessage(String text)
	{
		if (!_connected || _activeChannel == null)
		{
			System.err.println("Attempted to send message, but not connected!");
			System.err.println(text);
			return;
		}
		
		_activeChannel.sendMessage(text).queue();
	}

	@Override
	public void sendFile(File file)
	{
		if (!_connected || _activeChannel == null)
		{
			System.err.println("Attempted to upload image, but not connected!");
			System.err.println(file.getName());
			return;
		}
		
		_activeChannel.sendFile(file).queue();
	}

	@Override
	public void init()
	{
		connect();
	}

	@Override
	public void dispose()
	{
		_activeChannel = null;
		_connected = false;
		
		if (_jda != null)
		{
			_jda.shutdown();
			_jda = null;
		}
	}
	
	public void sendError(String message, Throwable error)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(message).append("\n");
		sb.append("```\n");
		
		sb.append("Error: ").append(error.getMessage()).append("\n");
		
		for (StackTraceElement e : error.getStackTrace())
			sb.append("  at ").append(e.getFileName()).append(".").append(e.getMethodName())
			.append("(line ").append(e.getLineNumber()).append(")\n");
		
		sb.append("```");
		sendMessage(sb.toString());
	}

	@Override
	public void setCommandHandler(CommandHandler commandHandler)
	{
		_commandHandler = commandHandler;
	}

	@Override
	public void onEvent(Event event)
	{
		if (!_connected)
			return;

		if (_commandHandler == null)
			return;

		if (event instanceof MessageReceivedEvent)
		{
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			
			if (e.getAuthor().isBot())
				return;

			Message message = e.getMessage();
			String content = message.getContentRaw();
			String authorName = e.getAuthor().getName();
			
			System.out.println(authorName + ": " + content);
			
			ParsedCommand command = CommandUtils.parseCommand(content);
			if (command == null)
				return;

			User user = new User(e.getAuthor(), e.getChannel());
			ArrayList<FileUpload> attachments = new ArrayList<>();

			UserAction action = new UserAction(command, user, attachments);
			_commandHandler.handle(action);
		}
	}

	private void connect()
	{
		JDA jda;
		try
		{
			jda = new JDABuilder(_token)
					.addEventListener(this)
					.setAutoReconnect(true)
					.setGame(Game.watching("your every move"))
					.build();
			jda.awaitReady();
			_connected = true;
			
			List<TextChannel> channels = jda.getTextChannelsByName("general", true);
			_activeChannel = channels.isEmpty() ? null : channels.get(0);
			
			if (_activeChannel == null)
				System.err.println("Failed to find main channel!");
		}
		catch (Exception e)
		{
			System.err.println("Failed to connect to Discord!");
			e.printStackTrace();
			_connected = false;
			_jda = null;
		}
	}
}
