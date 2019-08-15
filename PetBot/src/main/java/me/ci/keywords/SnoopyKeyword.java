package me.ci.keywords;

import me.ci.user.User;

public class SnoopyKeyword implements Keyword
{
	private static String[] WORDS = {".*(\\s|^)[sS]+[nN]+[oO]+([pP]|[wW])+([yY]|[aA][iI])*(\\s|$|[^a-zA-Z]).*"};

	private static String[] REACTIONS = { //
			"DID SOMEONE SAY SNOOPY?! :snoopy: :snoopy: :snoopy:", //
			"SNOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPYYYYYYYYYYYYYYYYY!!!!!!!!!!!!!!!!", //
			":heart: :snoopy: :heart: :snoopy: :heart: :snoopy:", //
			"Yay! Snoopy!!!",//
			"I LOVE SNOOPY!",//
			"BOOP DAT SNOOT FOR INFINITE HAPPINESS.",//
			"THE MOST QUALIFIED STUFFO IN THE WORLD!",//
			"HEY DID YOU KNOW THAT SNOOPY LOVES YOU?",//
			"GIVE THAT COLD PLASTIC A SMOOCHIE!",//
			"SNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPYSNOOPY",//
			"Snoopy, the white knight in shining fur!",//
			"YEWURIHSDKJHSAILUFYSBVDKLSAHFWYFDSKCHJSDGFWGFHDGCKJSDGJHGUYDCGJHGFUSDGFSHJGFSDKJFGWRYGFUDFGUKSDYGEWRHJGSUDCGWEBJSKYGSUDYGFUWGJSHCUYDSGFSHDBCKSFDGHJSGSDAKGFSHGD SNOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY",//
			"HEYDIDYOUKNOWTHATSNOOPYISAWESOME!?",//
			"SNOOPY POOPY DOOOOOOPY!!!!!",//
			"YAYAYAYAYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",//
			"SNOOPAY cX",//
			":happychicken: :heart: :snoopy: :happychicken: :heart: :snoopy: :happychicken: :heart: :snoopy: :happychicken: :heart: :snoopy: :happychicken: :heart: :snoopy:"//
	};

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
