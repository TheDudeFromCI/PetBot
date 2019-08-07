package me.ci.user;

import java.io.File;
import java.util.List;
import net.dv8tion.jda.core.entities.Message.Attachment;
import net.whg.awgenshell.exec.CommandSender;

public interface User extends CommandSender
{
	void printImage(File file);

	void printError(String message, Throwable error);

	void runCommand(String cmd);

	List<Attachment> getAttachments();
}
