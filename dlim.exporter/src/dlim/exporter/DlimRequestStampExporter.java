package dlim.exporter;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/**
 * Base class for exporting request time-stamps.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DlimRequestStampExporter {

	public DlimRequestStampExporter() {
		super();
	}

	/**
	 * creates timestamp folder if it does not exist and returns path to it
	 * @param projectPath
	 * @return
	 */
	protected IPath perpareTimestampDir(String projectPath) {
		IPath timeStampFolderPath = new Path(projectPath).append("timeStamps");
		File timeStampFolder = timeStampFolderPath.toFile();
		if (!timeStampFolder.exists()){
			timeStampFolder.mkdir();
		}
		return timeStampFolderPath;
	}

}