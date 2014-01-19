package dlim.reader;

/**
 * Provides utilities for file reading.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ReadingUtils {
	
	/**
	 * Returns the name of the file within a path.
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		String fileName = filePath;
	  	
	  	for (int i = filePath.length() - 1; i > 0; i--) {
	  		if (filePath.charAt(i) == '/') {
	  			fileName = filePath.substring(i+1);
	  			break;
	  		}
	  	}
	  	
	  	for (int i = fileName.length() - 1; i > 0; i--) {
	  		if (fileName.charAt(i) == '.') {
	  			fileName = fileName.substring(0, i);
	  			break;
	  		}
	  	}
	  	return fileName;
	}
}
