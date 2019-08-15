package me.ci.keywords;

import java.util.ArrayList;
import java.util.List;
import me.ci.user.User;

public class KeywordList
{
	private List<Keyword> keywords = new ArrayList<>();

	public KeywordList()
	{
		addKeyword(new SnoopyKeyword());
		addKeyword(new HappyChickenKeyword());
	}

	public void addKeyword(Keyword keyword)
	{
		if (keyword == null)
			return;

		if (keywords.contains(keyword))
			return;

		keywords.add(keyword);
	}

	public void checkForKeywords(User user, String message)
	{
		for (Keyword key : keywords)
		{
			for (String word : key.getKeywords())
			{
				if (message.matches(word))
				{
					key.run(user, message);
					return;
				}
			}
		}
	}
}
