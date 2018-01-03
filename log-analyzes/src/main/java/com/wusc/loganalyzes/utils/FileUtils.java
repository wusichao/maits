package com.wusc.loganalyzes.utils;

import java.io.File;

public class FileUtils {

	public static File getFile(String dir,String filename){
		
		File f = new File(new StringBuilder(dir).append(File.separatorChar).append(filename).toString());
		if(f.exists()) return f;
		return null;
	}
	
	public static boolean isDir(String dir,String filename){
		File f = getFile(dir, filename);
		return  f == null ? false : f.isDirectory();
	}
	
	public static String concat(String parentDir,String childDir){
		return new StringBuilder(parentDir).append(File.separatorChar).append(childDir).toString();
	}
	
}
