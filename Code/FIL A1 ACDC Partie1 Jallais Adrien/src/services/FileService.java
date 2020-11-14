package services;

public final class FileService {

	/**
	 * check if the given string match with a path
	 * 
	 * @deprecated
	 * @param filePath of the file to be evaluated
	 * @return
	 */
	public static boolean isPathValid(String filePath) {
		return true;
		// return filePath.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
	}

}
