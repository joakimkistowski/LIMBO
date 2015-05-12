/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Base class for exporting request time-stamps.
 *
 * @author Joakim von Kistowski
 *
 */
public class DlimRequestStampExporter {

	/**
	 * Instantiates a new dlim request stamp exporter.
	 */
	public DlimRequestStampExporter() {
		super();
	}

	/**
	 * Creates timestamp folder if it does not exist and returns path to it.
	 *
	 * @param projectPath the project path
	 * @return the i path
	 */
	protected IPath perpareTimestampDir(String projectPath) {
		IPath timeStampFolderPath = new Path(projectPath).append("timeStamps");
		File timeStampFolder = timeStampFolderPath.toFile();
		if (!timeStampFolder.exists()) {
			timeStampFolder.mkdir();
		}
		return timeStampFolderPath;
	}

}