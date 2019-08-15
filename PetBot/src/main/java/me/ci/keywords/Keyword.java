package me.ci.keywords;

import me.ci.user.User;

public interface Keyword
{
	String[] getKeywords();

	void run(User user, String message);
}
