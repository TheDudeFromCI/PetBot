package me.ci.user;

import java.io.File;

public interface FileUpload
{
	public String getFileName();
	
	public void download(File file);
}
