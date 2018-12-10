package me.ci.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtils
{
	public static ParsedCommand parseCommand(String cmd)
	{
		Pattern commandPattern = Pattern.compile("^\\s*!(?<command>\\w+)(?=.*)\\s*(?<args>.+?)?\\s*$");
		Matcher commandMatcher = commandPattern.matcher(cmd);
		
		if (!commandMatcher.matches())
			return null;
		
		String cmdName = commandMatcher.group("command");
		String cmdArgs = commandMatcher.group("args");
		String[] args;
		
		if (cmdArgs != null)
		{
			Pattern argPattern = Pattern.compile("\"(?<noquotes>(?:[^\"\\\\]|\\\\.)*)\"|(?:[^\" \\\\]|\\\\.)+");
			Matcher argMatcher = argPattern.matcher(cmdArgs);
			
			ArrayList<String> argList = new ArrayList<>();
			
			while (argMatcher.find())
			{
				String group = argMatcher.group("noquotes");
				
				if (group == null)
					group = argMatcher.group();
				
				argList.add(group);
			}
			
			args = new String[argList.size()];
			for (int i = 0; i < args.length; i++)
				args[i] = argList.get(i);
		}
		else
			args = new String[0];
		
		return new ParsedCommand(cmdName, args);
	}
}
