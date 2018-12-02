package me.ci;

import javax.security.auth.login.LoginException;

public interface DiscordAPI
{
	public void connect(String token, EventHandler eventHandler) throws LoginException,
		InterruptedException;

	public void reconnect() throws LoginException, InterruptedException;
}
