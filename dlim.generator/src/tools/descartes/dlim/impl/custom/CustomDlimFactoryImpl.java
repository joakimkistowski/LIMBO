package tools.descartes.dlim.impl.custom;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tools.descartes.dlim.ArrivalRatesFromFile;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.impl.DlimFactoryImpl;

/**
 * This custom factory implementation creates model elements using the custom implementations.
 * It also adds the adapters to model elements with nameLabels.
 * @author Jóakim G. v. Kistowski
 *
 */
public class CustomDlimFactoryImpl extends DlimFactoryImpl {

	public static CustomDlimFactoryImpl init() {
		try {
			CustomDlimFactoryImpl theDlimFactory = (CustomDlimFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(DlimPackage.eNS_URI);
			if (theDlimFactory != null) {
				return theDlimFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CustomDlimFactoryImpl();
	}
	
	public CustomDlimFactoryImpl() {
		super();
	}
	
	@Override
	public ArrivalRatesFromFile createArrivalRatesFromFile() {
		CustomArrivalRatesFromFileImpl arrivalRatesFromFile = new CustomArrivalRatesFromFileImpl();
		return arrivalRatesFromFile;
	}
}
