package me.ci.commands;

import net.whg.awgenshell.exec.Module;

public class PetBotModule
{
	public static Module load()
	{
		Module mod = new Module();

		mod.loadCommand(new ShowMeCommand());
		mod.loadCommand(new UploadCommand());

		return mod;
	}
}
