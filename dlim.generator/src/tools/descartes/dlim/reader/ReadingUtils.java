/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.reader;

/**
 * Provides utilities for file reading.
 *
 * @author Joakim von Kistowski
 *
 */
public final class ReadingUtils {

	/**
	 * It's al static anyway.
	 */
	private ReadingUtils() {

	}

	/**
	 * Returns the name of the file within a path.
	 *
	 * @param filePath the path
	 * @return the name
	 */
	public static String getFileName(String filePath) {
		String fileName = filePath;

		for (int i = filePath.length() - 1; i > 0; i--) {
			if (filePath.charAt(i) == '/') {
				fileName = filePath.substring(i + 1);
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
