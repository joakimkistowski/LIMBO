package tools.descartes.dlim.tests;

import java.io.File;

/**
 * Utilities for testing.
 *
 * @author Jóakim v. Kistowski
 */
public final class DlimTestUtils {

	/**
	 * Delta for floating point equality comparisons.
	 */
	public static final double FP_DELTA = 0.0001;

	/**
	 * Path for test outputs. Clean with cleanTestOutputDirectory before use.
	 */
	public static final String TEST_OUTPUT_DIR = "testoutput/";

	/**
	 * Path for all input files for testing.
	 */
	public static final String TEST_INPUT_DIR = "testinput/";

	/**
	 * DLIM model instance for testing.
	 */
	public static final String TEST_MODEL = DlimTestUtils.TEST_INPUT_DIR
			+ "testmodel.dlim";

	/**
	 * It's all static anyways.
	 */
	private DlimTestUtils() {
	}

	/**
	 * Cleans the test output directory of all files.
	 */
	public static void cleanTestOutputDirectories() {
		cleanDirectory(new File(TEST_OUTPUT_DIR));
		cleanDirectory(new File("./arrivalRates"));
		cleanDirectory(new File("./timeStamps"));
		cleanDirectory(new File("./diffs"));
	}

	private static void cleanDirectory(File dir) {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					cleanDirectory(f);
				}
				f.delete();
			}
		}
	}

	/**
	 * Returns the Path of the current working directory. This should also be
	 * the path of the Eclipse Test project.
	 *
	 * @return Path of this test project.
	 */
	public static String getProjectPath() {
		return new File("").getAbsolutePath();
	}

}
