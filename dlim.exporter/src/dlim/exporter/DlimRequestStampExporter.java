package dlim.exporter;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class DlimRequestStampExporter {

	public DlimRequestStampExporter() {
		super();
	}

	protected IPath perpareTimestampDir(String projectPath) {
		IPath timeStampFolderPath = new Path(projectPath).append("timeStamps");
		File timeStampFolder = timeStampFolderPath.toFile();
		if (!timeStampFolder.exists()){
			timeStampFolder.mkdir();
		}
		return timeStampFolderPath;
	}

}