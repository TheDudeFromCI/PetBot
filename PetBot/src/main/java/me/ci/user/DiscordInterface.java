package me.ci.user;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import me.ci.Main;
import me.ci.keywords.KeywordList;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.whg.awgenshell.parse.CommandParseException;

public class DiscordInterface implements UserInterface, EventListener
{
	private HashMap<String, DiscordUser> users = new HashMap<>();

	private String _token;
	private boolean _connected;
	private MessageChannel _activeChannel;
	private JDA _jda;
	private KeywordList keywords;

	public DiscordInterface(String token)
	{
		_token = token;
		keywords = new KeywordList();
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

	@Override
	public void onEvent(Event event)
	{
		if (!_connected)
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

			if (content.startsWith("```") && content.endsWith("```"))
				content = content.substring(3, content.length() - 3);

			synchronized (Main.LOCK)
			{
				DiscordUser user;
				if (users.containsKey(authorName))
					user = users.get(authorName);
				else
				{
					user = new DiscordUser(e.getAuthor(), e.getChannel());
					users.put(authorName, user);
				}

				user.setChannel(e.getChannel());
				user.setAttachments(message.getAttachments());

				try
				{
					if (content.startsWith("\\"))
						user.runCommand(content.substring(1));
					else
						keywords.checkForKeywords(user, content);
				}
				catch (CommandParseException ex)
				{
					user.println("Failed to parse command: " + ex.getMessage());
				}
				catch (Exception ex)
				{
					user.printError("An error has occured while running this command!", ex);
				}
				finally
				{
					user.flushMessages();
					user.getAttachments().clear();
				}
			}
		}
	}

	private void connect()
	{
		JDA jda;
		try
		{
			jda = new JDABuilder(_token).addEventListener(this).setAutoReconnect(true)
					.setGame(Game.watching("your every move")).build();
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
