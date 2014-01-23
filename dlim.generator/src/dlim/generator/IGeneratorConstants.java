package dlim.generator;

/**
 * Provides constants for the different sampling modes.
 * @author Jóakim G. v. Kistowski
 *
 */
public interface IGeneratorConstants {

	/**
	 * The time-stamp generator is not to be executed. No time stamps are to be produced.
	 */
	public static final String NOTIMESTAMPS = "dlim:noTimeStamps";
	
	/**
	 * Time-stamps are to be equally distributed within each sampled arrival rate interval.
	 */
	public static final String EQUALDISTANCESAMPLING = "dlim:equal";
	
	/**
	 * Time-stamps are to be randomly generated within each sampled arrival rate interval.
	 */
	public static final String UNIFORMDISTRIBUTIONSAMPLING = "dlim:uniform";
	
	/**
	 * Noises are to return 0.
	 */
	public static final String CALIBRATION = "dlim:calibration";
	
	/**
	 * Noises are to be evaluated.
	 */
	public static final String EVALUATION = "dlim:evaluation";
	
	public static final int DEFAULTRANDOMSEED = 5;
	
}
