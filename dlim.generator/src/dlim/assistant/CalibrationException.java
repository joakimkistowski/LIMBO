package dlim.assistant;

/**
 * Is thrown during errors in calibration of interpolated Functions (i.e. Trends and Bursts).
 * @author Jóakim G. v. Kistowski
 *
 */
public class CalibrationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 529028740127235542L;

	//The message as set during calibration
	private String message;
	
	/**
	 * Create an exception that results from an error during calibration
	 * @param message This message will be displayed to the user.
	 */
	public CalibrationException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * Get the description of the error.
	 */
	public String getMessage() {
		return message;
	}
}
