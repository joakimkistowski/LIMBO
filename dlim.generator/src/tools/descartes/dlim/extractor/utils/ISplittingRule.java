/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

/**
 * Filter Interface to be passed to the Binary Splitter.
 * Decides whether seasonal patterns deviate enough to warrant a split.
 * 
 * @author Joakim von Kistowski
 *
 */
public interface ISplittingRule {

	/**
	 * Decides whether the two extraction data containers, including their arrival rate lists,
	 * differ enough to warrant a seasonal split.
	 * @param cleft The left hand side container, including arrival rate list.
	 * @param cright The right hand side container, including arrival rate list.
	 * @return true, if a split is warranted.
	 */
	public boolean warrantSplit(ExtractionDataContainer cleft, ExtractionDataContainer cright);
	
}
