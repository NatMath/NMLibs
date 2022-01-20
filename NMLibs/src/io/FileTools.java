package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class FileTools {
	
	/**
	 * Returns the root of the project hierarchy, i.e. the folder with the project name.
	 * @return the root of the project
	 */
	public static String getRoot() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * Creates a path towards the file or folder at the given path from the root as given by {@link #getRoot()}. 
	 * @param path The path of the file relative to the root
	 * @return The complete absolute path of the file
	 */
	public static Path fromRoot(String path) {
		return Paths.get(getRoot() + path);
	}
	
	/**
	 * Writes the given string to the file defined by <code>path</code>.	
	 * @param path The path to which to write the text
	 * @param text The text to write to the file
	 * @throws IOException if an I/O error occurs writing to or creating the file, or the text cannot be encoded in UTF-8
	 */
	public static void writeFile(Path path, String text) throws IOException{
		Files.writeString(path, text, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	


}
