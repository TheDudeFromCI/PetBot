package me.ci.keywords;

import me.ci.user.User;

public class HappyChickenKeyword implements Keyword
{
	private static String[] WORDS = {".*(\\s|^)\\<\\:happychicken\\:[0-9]+\\>(\\s|$|[^a-zA-Z]).*"};

	private static String[] REACTIONS = {":happychicken:"};

	@Override
	public String[] getKeywords()
	{
		return WORDS;
	}

	@Override
	public void run(User user, String message)
	{
		String snoopy = user.getEmoji("snoopy");
		String happychicken = user.getEmoji("happychicken");
		String love = user.getEmoji("love");
		String heart = user.getEmoji("heart");

		String reaction = REACTIONS[(int) (Math.random() * REACTIONS.length)];

		if (snoopy != null)
			reaction = reaction.replace(":snoopy:", snoopy);

		if (happychicken != null)
			reaction = reaction.replace(":happychicken:", happychicken);

		if (love != null)
			reaction = reaction.replace(":love:", love);

		if (heart != null)
			reaction = reaction.replace(":heart:", heart);

		user.println(reaction);
	}
}
