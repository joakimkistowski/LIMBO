/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.impl.custom;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tools.descartes.dlim.ArrivalRatesFromFile;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.impl.DlimFactoryImpl;

/**
 * This custom factory implementation creates model elements using the custom
 * implementations. It also adds the adapters to model elements with nameLabels.
 *
 * @author Joakim von Kistowski
 *
 */
public class CustomDlimFactoryImpl extends DlimFactoryImpl {

	/**
	 * Inits the.
	 *
	 * @return the custom dlim factory impl
	 */
	public static CustomDlimFactoryImpl init() {
		try {
			CustomDlimFactoryImpl theDlimFactory = (CustomDlimFactoryImpl) EPackage.Registry.INSTANCE
					.getEFactory(DlimPackage.eNS_URI);
			if (theDlimFactory != null) {
				return theDlimFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CustomDlimFactoryImpl();
	}

	/**
	 * Instantiates a new custom dlim factory impl.
	 */
	public CustomDlimFactoryImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see tools.descartes.dlim.impl.DlimFactoryImpl#createArrivalRatesFromFile()
	 */
	@Override
	public ArrivalRatesFromFile createArrivalRatesFromFile() {
		CustomArrivalRatesFromFileImpl arrivalRatesFromFile = new CustomArrivalRatesFromFileImpl();
		return arrivalRatesFromFile;
	}
}
